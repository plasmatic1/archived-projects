var discordjs = require('discord.js');
var rcs = require('./reactions.js');
var symbols = require('./symbols.js');

class Menu{
	constructor(client, message){
		this.handlers = {};
		this.reactions = [];

		this.client = client;
		this.message = message;

		this.manager = new rcs.HandlerManager(client);

		var _this = this;

		this.manager.registerHandler('add', function(reaction, user){
			var name = reaction.emoji.name;
			// console.log('reaction:', name, 'is:', _this.handlers[name]);
			if(_this.handlers[name]){
				_this.handlers[name](reaction, user);
				reaction.remove(user);
			}
		}, this.message);
	}

	on(reaction, handler){
		this.handlers[reaction] = handler;
		this.reactions.push(reaction);
	}

	async init(){
		var _this = this;

		for(var reaction of _this.reactions){
			await _this.message.react(reaction);
		}
	}
}

module.exports = {
	Menu: Menu
	// ScrollPane: ScrollPane,
};