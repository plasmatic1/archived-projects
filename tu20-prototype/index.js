/*

{
	"debug": true,
	"sql": {
		"host": "localhost",
		"user": "root",
		"password": "admin",
		"db": "door_db",
		"tables": {
			"main": "id INT PRIMARY KEY, date DATE, time TIME, open BIT, front_back BIT", 
			"setup": "id INT PRIMARY KEY, type VARCHAR(255), location VARCHAR(255), last DATE, next DATE, maintenance_interval INT, count INT"
		}
	},
	"host": "localhost",
	"port": 8080,
	"suburl": "/door-manager"
}

POST Testing

Creating doors and 

curl -url http://localhost:8080/door-manager -method POST -body ...
curl -url http://localhost:8080/door-manager -method UPDATE -body ...

*/

//VARS

var express = require('express');
var mysql = require('mysql');
var http = require('http');
var util = require('util');
var socketio = require('socket.io');
var readline = require('readline');

var config = require('./config.json');
var events = require('./events.js');

var app = express();
var server = http.Server(app);
var io = socketio(server);
var connection = mysql.createConnection({
	host: config.sql.host,
	user: config.sql.user,
	password: config.sql.password
});

//CONSTANTS AND INIT
const TABLES = Object.keys(config.sql.tables).map(tb => config.sql.db + '.' + tb);
const TBOBJ = config.sql.tables;
const PORT = config.port;
var DEFAULT_SQL_CALLBACK = function(err, _){
	if(err)
		throw err;
};

console.log(util.inspect(events));

var rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

app.use(express.static(__dirname));

app.get(config.suburl, function(req, res){
	// console.log('sent to this url');
	res.sendFile(__dirname + '/index.html');
});

app.post(config.suburl, function(req, res){
	console.log(util.inspect(req.body));
});

server.listen(PORT, function(){
	console.log('Started Server on port', PORT);
});

connection.connect(async function(error){
  	var exists = TABLES.map(async tb => await tableExists(tb)).some(ex => !ex);

  	if(!exists){
  		console.log('Tables do not exist! Creating tables...');
  		attemptCreateTables();
  	}
});

//SOCKETIO
io.on('connection', function(socket){
	debug('Connection established with id ', socket.id);

	socket.on('disconnect', function(){
		debug('Connection lost with id ', socket.id);
	});

	socket.on(events.GET, function(data){
		console.log('Socket', socket.id, 'requests data');
		sendData();
	});

	socket.on(events.RESET, function(data){
		var obj = JSON.parse(data);
	});
});

//CONSOLE IO
rl.on('line', async (message) => {
	if(message == 'clear'){
		connection.query('DROP DATABASE ' + config.sql.db + ';', function(err, _){
			if(err){
				console.log('ERROR:', err.code);
			}
			else{
				console.log('Success!');
			}
		});
	}
	else if(message == 'check'){
		TABLES.forEach(async tb => {
			var exists = await tableExists(tb)/*.err(console.log);*/
			console.log('Table', tb, exists ? 'exists!' : 'does not exist!');
		});
	}
	else if(message == 'create'){
		attemptCreateTables();
	}
	else{
		console.log('Unknown command, available commands are:');
		console.log('clear : deletes database');
		console.log('check : checks if database exists');
		console.log('create : creates tables');
	}
});

//FUNCTIONS
function debug(message){
	if(config.debug){
		var join = Array.from(arguments).join('');
		console.log('[DEBUG]:', join);
	}
}

async function tableExists(table){
	return new Promise((resolve, reject) => {
    	connection.query('SELECT * FROM ' + table + ' LIMIT 1;', function(err, _){
    		if(err){
    			if(err.code == 'ER_NO_SUCH_TABLE'){
    				resolve(false);
    			}
    			else if(err.code = 'PROTOCOL_ENQUEUE_AFTER_FATAL_ERROR'){
    				reject('ERROR: Could not connect to the local MySQL server, is the MySQL service offline?');
    			}
    		}
    		else{
    			resolve(true);
    		}
    	})
 	});
}

async function asyncQuery(query){
	return new Promise((resolve, err) => {
		connection.query(query, function(errr, res){
			if(errr){
				err(errr);
			}
			else{
				resolve(res);
			}
		})
	})
}

async function attemptCreateTable(table, data){
	var exists = await tableExists(table);

	if(!exists){
		connection.query('CREATE DATABASE ' + config.sql.db + ';', function(err, _){
    		if(err){
    			if(err.code === 'ER_DB_CREATE_EXISTS'){
    				debug('Warning : Database already exists!');
    			}
    		}
    	});
    	// console.log('CREATE TABLE ' + table + ' (' + data + ');');
    	connection.query('CREATE TABLE ' + table + ' (' + data + ');',
    		DEFAULT_SQL_CALLBACK);
	}
	else{
		debug('Warning : Table ', table, ' already exists');
	}
}

async function attemptCreateTables(){
	for(var table in TBOBJ){
		await attemptCreateTable(config.sql.db + '.' + table, TBOBJ[table]);
	}
}

async function sendData(){
	var main = await asyncQuery('SELECT * FROM ' + TABLES[0] + ';');
	var setup = await asyncQuery('SELECT * FROM ' + TABLES[1] + ' ORDER BY id ASC;');

	var obj = {
		main: main,
		setup: setup
	};

	io.emit(events.RECV, JSON.stringify(obj));
}