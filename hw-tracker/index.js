var express = require('express');
var mysql = require('mysql');
var http = require('http');
var util = require('util');
var socketio = require('socket.io');
var readline = require('readline');

var config = require('./config.json');

var app = express();
var server = http.Server(app);
var io = socketio(server);
var connection = mysql.createConnection({
	host: 'localhost',
	user: 'root',
	password: 'admin'
});

const PORT = 21212;
var DEFAULT_SQL_CALLBACK = function(err, _){
	if(err)
		throw err;
};

var rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

app.use(express.static(__dirname));
app.get('/', function(req, res){
	res.sendFile('index.html');
});

server.listen(PORT, function(){
	console.log('Started Server on port', PORT);
});

connection.connect(async function(error){
	// if(error)
	// 	console.log(util.inspect(error));

  	var exists = await tableExists();

  	if(!exists){
  		attemptCreateTable();
  	}
});

io.on('connection', function(socket){
	debug('Connection established with id ', socket.id);
	// sendHomework();
	// io.emit('test', 'damn');

	socket.on('disconnect', function(){
		debug('Connection lost with id ', socket.id);
	});

	socket.on('homework-request', function(data){
		debug('Homework was requested...');
		sendHomework();
	});

	socket.on('homework-update', function(data){
		updateHomework();
		sendHomework();
	});

	socket.on('homework-submit', function(data){
		var obj = JSON.parse(data);
		debug('Received homework with ', util.inspect(obj));

		var q = connection.query('INSERT INTO hw_db.main SET ?;', obj, function(err, result){
			if(err){
				io.emit('homework-error', 'Error while inserting homework data: ' + err.code);
				debug('Erroneous query: ', q.sql);
				debug(err);
			}
		});

		sendHomework();
	});

	socket.on('homework-remove', function(data){
		var obj = JSON.parse(data);

		debug('Removing homework with id ', obj.id);
		connection.query('DELETE FROM hw_db.main WHERE id = ' + connection.escape(obj.id) + ';', function(err, result){
			if(err){
				if(err.code === 'ER_PARSE_ERROR'){
					io.emit('homework-error', 'Error while sending homework data: Malformed SQL statement while removing homework');
					console.log(err);
				}
				else{
					io.emit('homework-error', 'Error while sending homework data: Unknown error detected');
				}
			}
			else{
				debug('Removed assignment with id : ', obj.id);
			}
		});

		sendHomework();
	});
});

rl.on('line', async (message) => {
	if(message === 'help'){
		console.log('clear : Deletes table\n\
			create : Creates table\n\
			');
	}
	else if(message === 'clear'){
		var table = await tableExists();
		if(!table){
			console.log('Error : Homework table does not exist!');
			return;
		}

		connection.query('DROP TABLE hw_db.main', DEFAULT_SQL_CALLBACK);
		connection.query('DROP DATABASE hw_db', DEFAULT_SQL_CALLBACK);
		console.log('Deleted homework table');
	}
	else if(message === 'create'){
		attemptCreateTable();
		console.log('Created homework table');
	}
});

function debug(message){
	if(config.debug){
		var join = Array.from(arguments).join('');
		console.log('[DEBUG]:', join);
	}
}

async function sendHomework(){
	var output = {};
	const CALLBACK = function(err, result){
		if(err){
			debug('Error while sending homework : ', err.code);
			if(err.code === 'ER_PARSE_ERROR'){
				io.emit('homework-error', 'Error while sending homework data: Malformed SQL statement while requesting homework');
			}
			else{
				io.emit('homework-error', 'Error while sending homework data: Unknown error detected');
			}
		}
		else{
			console.log(result);
		}
	};

	async function queryForType(type){
		return asyncQuery('SELECT * FROM hw_db.main WHERE type = \'' + type + '\' ORDER BY optional, indeterminate, due_date ASC;');
	}

	output.assignments = await queryForType('assignment');
	output.projects = await queryForType('project');
	output.practice = await queryForType('daily');
	output.registration = await queryForType('registration');

	io.emit('homework-received', JSON.stringify(output));
}

function updateHomework(){
	connection.query('DELETE FROM hw_db.main WHERE due_date <= CURDATE() AND indeterminate = 0;', function(err, result){
		if(err){
			io.emit('homework-error', 'Error while updating homework database: ' + err.code);
			console.log(err);
		}
	});
}

async function tableExists(){
	return new Promise((resolve, reject) => {
    	connection.query('SELECT * FROM hw_db.main LIMIT 1;', function(err, _){
    		if(err){
    			if(err.code == 'ER_NO_SUCH_TABLE'){
    				resolve(false);
    			}
    			else if(err.code = 'PROTOCOL_ENQUEUE_AFTER_FATAL_ERROR'){
    				// console.log('ERROR: Could not connect to the local MySQL server, is the MySQL service offline?');
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

async function attemptCreateTable(){
	var exists = await tableExists();

	if(!exists){
		connection.query('CREATE DATABASE hw_db;', function(err, _){
    		if(err){
    			if(err.code === 'ER_DB_CREATE_EXISTS'){
    				debug('Warning : Database already exists!');
    			}
    		}
    	});
    	connection.query('CREATE TABLE hw_db.main ' +
    		'(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), subject VARCHAR(255), due_date DATE, type VARCHAR(255), indeterminate BIT, optional BIT);',
    		DEFAULT_SQL_CALLBACK);
	}
	else{
		debug('Warning : Table already exists');
	}
}