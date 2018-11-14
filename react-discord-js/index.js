String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) { 
      return typeof args[number] != 'undefined'
        ? args[number]
        : match
      ;
  });
};

var discordjs = require('discord.js');
var clipboardy = require('clipboardy');
var util = require('util');

var config = require('./config.json');
var reactions = require('./reactions.js');
var menus = require('./menus.js');
var symbols = require('./symbols.js');

var client = new discordjs.Client();
var reactionHandler = new reactions.HandlerManager(client);
client.login(config.token);

var LOGIN_URL = undefined;
const LS = reactions.createLetterList();

client.on('ready', function(e){
	console.log('Bot Initialized!');
	LOGIN_URL = 'https://discordapp.com/oauth2/authorize?&client_id=' + client.user.id + '&scope=bot&permissions=0';

	if(config.showUrl){
		console.log('URL to add bot to server is', LOGIN_URL);
		clipboardy.writeSync(LOGIN_URL);
	}
	// console.log(util.inspect(LS));
});

client.on('message', function(message, user){
	if(message.author.bot){
		return;
	}

	var content = message.content;
	var channel = message.channel;

	if(content.startsWith(config.prefix)){
		console.log(content);
		clipboardy.writeSync(String.fromCharCode(55356));
		var text = content.slice(1);
		var cmd = text.split(' ');

		if(cmd[0] === 'poll'){
			var voteA = 0, voteB = 0;
			var question = cmd.slice(1).join(' '), template = '**Poll**\n_Q: {0}_\n_Yay: {1}\nNay: {2}_';

			debug('Creating poll...');
			message.delete();
			channel.send(template.format(question, voteA, voteB))
			.then(messagee => {
				reactions.reactDefault(messagee, LS.y, LS.n);
				reactionHandler.registerHandler('add', function(reaction, user){
					var letter = reactions.getLetter(reaction);
					if(letter){
						if(letter === 'y'){
							voteA++;
						}
						else if(letter === 'n'){
							voteB++;
						}

						messagee.edit(template.format(question, voteA, voteB));
					}
				}, messagee);
				reactionHandler.registerHandler('remove', function(reaction, user){
					var letter = reactions.getLetter(reaction);
					if(letter){
						if(letter === 'y'){
							voteA--;
						}
						else if(letter === 'n'){
							voteB--;
						}

						messagee.edit(template.format(question, voteA, voteB));
					}
				}, messagee);
			}).catch(console.error);
		}
		else if(cmd[0] === 'rules'){
			var current = 0;
			var _message = undefined;
			var menu = undefined;
			channel.send(config.rules[0])
			.then(messagee => {
				function update(){
					var arr = config.rules[current].slice();
					// console.log(current);
					// console.log(config.rules[current]);
					arr.push("");
					arr.push("*Page " + (current + 1) + "*");
					_message.edit(arr);
				}

				_message = messagee;
				menu = new menus.Menu(client, _message);
				menu.on(symbols.arrowBackward, function(reaction, user){
					// console.log('test');
					if(current > 0){
						current--;
						update();
					}
				});
				menu.on(symbols.arrowForward, function(reaction, user){
					if(current < config.rules.length - 1){
						current++;
						update();
					}
				});

				menu.init();
			});

			message.delete();
		}
	}
	//message
});

function debug(message){
	if(config.debug){
		var join = Array.from(arguments).join('');
		console.log('[DEBUG]:', join);
	}
}