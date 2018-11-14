var http = require('http');
var socketio = require('socket.io');
var express = require('express');
var util = require('util');
var readline = require('readline');
// var mysql = require('mysql');

var config = require('./config.json');
var ranks = require('./ranks.js');

var app = express();
var server = http.createServer(app);
var io = socketio(server);
var rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});
// var connection = mysql.createConnection({
// 	host: 'localhost',
// 	user: 'root',
// 	password: 'admin'
// });

// connection.connect(async function(err){
// 	var exists = await tableExists('chat_db.messages');

// 	if(!exists){
// 		connection.query('CREATE DATABASE chat_db;');
// 		connection.query('CREATE TABLE chat_db.messages (id INT AUTO INCREMENT PRIMARY KEY, sender VARCHAR(255), sender_id VARCHAR(255), message VARCHAR(2048), timestamp DATETIME)');
// 		console.log('Creating table...');
// 	}
// });

let PORT = 21212;

var users = {};

// console.log(__dirname);
// console.log(ranks);

app.use(express.static(__dirname));
app.get('/', function(request, response){
	response.sendFile(__dirname + "/index.html");
});

server.listen(PORT, function(){
	console.log('Started server on port', PORT);
});

io.on('connection', function(socket){
	debug('Connection received from ', socket.id);

	socket.on('message', function(str){
		var pack = JSON.parse(str), data = pack.data, type = pack.type;
		debug('Received packet: ', util.inspect(pack));

		if(!type || !data){
			debug('Invalid packet!');
			return;
		}

		if(type === 'login'){
			console.log('User with id', socket.id, 'and a username of', data, 'has logged in!');
			io.send(JSON.stringify({
				type: 'join',
				data: data
			}));

			users[socket.id] = {
				name: data,
				rank: 1,
				id: socket.id
			};

			sendUserInfo();
		}
		else if(type === 'chat'){
			var time = new Date();
			var message_ = '[' + time.toString().split(' ')[4] + '] ' + data.user + ' : ' + data.message;
			io.send(JSON.stringify({
				type: 'chat',
				data: {
					message: message_,
					user: users[socket.id]
				}
			}));
			console.log(message_);
		}
		else if(type === 'command'){
			//
		}
	});

	socket.on('disconnect', function(){
		var user = users[socket.id];

		if(!user){
			user = {name: 'An Unknown User'};
		}

		io.send(JSON.stringify({
			type: 'leave',
			data: user.name
		}));
		console.log('User', user.name, '(', socket.id, ') has disconnected!');

		users[socket.id] = undefined;
		delete users[socket.id];

		sendUserInfo();
	});
});

function debug(message){
	if(config.debug){
		var join = Array.from(arguments).join('');
		console.log('[DEBUG]:', join);
	}
}

function sendUserInfo(){
	io.send(JSON.stringify({
		type: 'users',
		data: users
	}));
}

rl.on('line', async (message) => {
	var cmd = message.split(' ');

	if(cmd[0] === 'help'){
		console.log('help : Displays this message');
		console.log('ranks : Displays the ranks of all online users');
		console.log('cmdidlist : Displays a list of users with the corresponding command ids');
		console.log('*A command id is the id you use to reference a user when using a command');
		console.log('rank <id> <rank number> : Sets the rank of a user');
	}
	else if(cmd[0] === 'ranks'){
		Object.keys(users).forEach(id => {
			var user = users[id];
			console.log(user.name, '(', user.id, ') :', ranks.rank(user.rank));
		});
	}
	else if(cmd[0] === 'cmdidlist'){
		Object.keys(users).forEach((id, index) => {
			var user = users[id];
			console.log(index, '=>', user.name, '(', user.id, ')');
		});
	}
	else if(cmd[0] === 'rank'){
		var userObjects = Object.values(users);
		var id = parseInt(cmd[1]), rank = parseInt(cmd[2]);
		if(userObjects.length <= id){
			console.log('ID is too large!');
			return;
		}
		userObjects[id].rank = rank;
	}
});

// async function tableExists(table){
// 	return new Promise((resolve, reject) => {
//     	connection.query('SELECT * FROM ' + table + ' LIMIT 1;', function(err, _){
//     		if(err){
//     			if(err.code == 'ER_NO_SUCH_TABLE'){
//     				resolve(false);
//     			}
//     			// else if(err.code = 'PROTOCOL_ENQUEUE_AFTER_FATAL_ERROR'){
//     			// 	// console.log('ERROR: Could not connect to the local MySQL server, is the MySQL service offline?');
//     			// 	// reject('ERROR: Could not connect to the local MySQL server, is the MySQL service offline?');
//     			// 	reject(err);
//     			// }
//     			else{
//     				reject(err);
//     			}
//     		}
//     		else{
//     			resolve(true);
//     		}
//     	})
//  	});
// }