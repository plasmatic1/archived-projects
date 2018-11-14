String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) { 
      return typeof args[number] != 'undefined'
        ? args[number]
        : match
      ;
    });
  };

var discord = require('discord.js');
var util = require('util');
var clip = require('clipboardy');

var config = require('./config.json');

var bot = new discord.Client();

const PREFIX = ';';

var farmXPInterval = null;
var lastEval = undefined;
var lastMessage_ = undefined;
//Event listeners

function arrayEndToString(startIndex, array){
	return array.slice(startIndex, array.length).join(' ');
}

bot.on('ready', () => {
	console.log('Initialized bot!');
	usr = bot.user;
});

bot.on('message', message => {
	if(message.author !== bot.user) return;

	var text = message.content;
	var prefix = text.substr(0, PREFIX.length);
	var command = text.substr(PREFIX.length, text.length - 1).split(' ');
	var name = command[0];

	lastMessage_ = name === 'last' ? lastMessage_ : text;

	if(prefix === PREFIX){
		var channel = message.channel;
		message.delete();

		if(name === 'info'){
			if(command.length < 2){
				channel.send('```The command info <target> prints the JSON structure of the target\n\nThe target is an object starting from the bot client itself (which does ' +
					'not need to be included in the <target> parameter).  The parts of the path will be dot separated, so if you want to access the property ' + 
					'\'id\' of \'user\' of the bot, you would type "info user.id"```');
				return;
			}

			if(command[1] === '*'){
				var original = "[Object client]\n{0}".format(util.inspect(bot));
			}
			else{
				var path = command[1].split('.');
				var item = bot;
				path.forEach(s => {
					if(item[s] == undefined){
						channel.send('```"{0}" is undefined!```'.format(command[1]));
						return;
					}

					item = item[s];
				});

				var original = "[{0} {1}]\n{2}".format(typeof item, command[1], util.inspect(item));
			}

			var length = original.length;
			if(length > 2000){
				channel.send('```Message is too long! Sending in chunks...```');
				const CHUNK_LENGTH = 1994;
				const EXTRA = length % CHUNK_LENGTH;

				for(var i = 0; i < length; i += CHUNK_LENGTH){
					console.log(original.substr(i, CHUNK_LENGTH));
					channel.send('```{0}```'.format(original.substr(i, CHUNK_LENGTH)));
				}

				if(EXTRA > 0){
					channel.send('```{0}```'.format(original.substr(length / CHUNK_LENGTH * length, EXTRA)));
				}
			}
			else{
				channel.send("```" + original + "```");
			}
			channel.send("```Process Complete```")
		}
		else if(name === 'spam'){
			if(command.length < 3 && !(command.length == 2 && command[1] === 'stop')){
				console.log("{0} {1}", command.length, command[1] !== 'stop');

				channel.send('```JS\nInvalid Command Arguments\n\nArgument syntax for this command should be "?<command> <interval> <message>"\nOR "?<command> stop"```');
				return;
			}

			if(command[1] === 'stop'){
				if(farmXPInterval == null){
					channel.send('```There is no session to stop!```');
					return;
				}
				channel.send('```Session stopped!```');
				clearInterval(farmXPInterval);
				farmXPInterval = null;
			}
			else{
				var interval = parseInt(command[1], 10);
				var msg = arrayEndToString(2, command);

				console.log(interval);

				if(isNaN(interval)){
					channel.send('```Interval must be a number!```');
					return;
				}

				channel.send('```Initialized to send "{0}" at an interval of {1} ms```'.format(msg, interval))
					.then(message => setTimeout(() => message.delete(), 3000))
					.catch(console.error);

				farmXPInterval = setInterval(() => channel.send(msg), interval);
			}
		}
		else if(name === 'purge'){
			if(command.length < 2){
				channel.send('```The command purge <count> deletes messages YOU sent up to the count specified```');
				return;
			}

			var amount = parseInt(command[1]);
			if(isNaN(amount) || amount < 1){
				channel.send('```Count must be a positive integer! Setting count to 1```');
				amount = 1;
			}
			if(amount > 1000){
				channel.send('```Amount is over the maximum limit of 1000! Setting amount to 1000```');
				amount = 1000;
			}

			var messages = channel.messages;
			var id = bot.user.id;

			messages.array().filter(v => v.author.id == id).slice(0, amount).forEach(async function(m){
				console.log(m.id);
				// m.delete().catch(err => {
				// 	if(err.code === 10008){
				// 		console.log('Message', m.id, 'not found!');
				// 	}
				// });
			});
		}
		else if(name === 'last'){
			if(command.length > 1 && command[1] === 'help'){
				channel.send('```The command "last" copies the last message sent (before this one) to the clipboard```');
				return;
			}

			if(lastMessage_ === undefined){
				channel.send("```There is no last message! (This is the first one)```");
				return;
			}

			clip.writeSync(lastMessage_);
		}
		else if(name === 'format'){
			if(command.length < 3){
				channel.send("```The command 'format <format type> [arguments] <text>' formats the given text into the specified format type and then sends it as a message"
					+ ".  Supported types include:\n- altercase: Alternates cases for each letter (English)\n- leet: Turns text into leetspeak format\n- whitespace"
					+ ": adds random amounts of whitespaces between 0 and max - 1 (Additional arguments: 'max' -> Positive Integer (default: 10))\n- reverse: Reverses "
					+ "text\n- kevinyu: Message will always start and end with a ':clown:' and will include the subsequent message 'you suck 10 million d!' after the original message```");
				return;
			}
			var alteredMessage = '';
			var formatType = command[1];
			var message = arrayEndToString(2, command);

			if(formatType === 'altercase'){
				var caps = true;
				for(var i = 0; i < message.length; i++){
					var currChar = message.charAt(i);
					if(currChar.match('[a-zA-Z]')){
						if(caps){
							alteredMessage += currChar.toLocaleUpperCase();
						}
						else{
							alteredMessage += currChar.toLocaleLowerCase();
						}
						caps = !caps;
					}
					else
						alteredMessage += currChar;
				}
			}
			else if(formatType === 'leet'){
				//4bcd3f6h1jk1mn0pqr5tuvwxy2
				alteredMessage = message.replace(/[aA]/g, '4').replace(/[eE]/g, '3').replace(/[gG]/g, '6').replace(/[iIlL]/g, '1').replace(/[oO]/g, '0').replace(/[sS]/g, '5')
					.replace(/[zZ]/g, '2');
			}
			else if(formatType === 'whitespace'){
				var max = parseInt(command[2]);

				if(isNaN(max) || max < 0)
					max = 10;
				else
					message = arrayEndToString(3, command);

				var lTC = message.length - 1;
				for(var i = 0; i < lTC; i++){
					 var spaceCount = Math.floor(Math.random() * max);
					 alteredMessage += message.charAt(i) + ' '.repeat(spaceCount);
				}
				alteredMessage += message.charAt(message.length - 1);
			}
			else if(formatType === 'reverse'){
				alteredMessage = message.split('').reverse().join('');
			}
			else if(formatType === 'kevinyu'){
				alteredMessage = ':clown: {0} :clown:'.format(message);
			}
			else{
				channel.send('```Invalid format type!```');
				return;
			}

			channel.send(alteredMessage);
			if(formatType === 'kevinyu')
				channel.send('you suck 10 million d!')
		}
	}
});

bot.login(config.token);