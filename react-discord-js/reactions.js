var discord = require('discord.js');
var util = require('util');

class HandlerManager{
	constructor(client){
		this.client = client;
		this.handlers = {};
		this.globalHandlers = [];
		this.offHandlers = {};
		this.offGlobalHandlers = [];

		var _this = this;
		var _removeAllId = function(message){
			_this.unregisterHandlers(message);
			// console.log(util.inspect(_this.handlers));
		};

		this.client.on('messageReactionAdd', function(reaction, user){
			if(user.id === _this.client.user.id){
				return;
			}

			var id = reaction.message.id;
			if(_this.handlers[id]){
				_this.handlers[id].forEach(handler => {
					handler(reaction, user);
				});
			}

			_this.globalHandlers.forEach(handler => {
				handler(reaction, user);
			});
		});

		this.client.on('messageReactionRemove', function(reaction, user){
			if(user.id === _this.client.user.id){
				return;
			}

			var id = reaction.message.id;
			if(_this.offHandlers[id]){
				_this.offHandlers[id].forEach(handler => {
					handler(reaction, user);
				});
			}

			_this.offGlobalHandlers.forEach(handler => {
				handler(reaction, user);
			});
		});

		this.client.on('messageDelete', function(message){
			_removeAllId(message);
		});

		this.client.on('messageDeleteBulk', function(messages){
			messages.array().forEach(_removeAllId);
		});
	}

	//TYPES: 'add', 'remove'
	registerHandler(eventType, handler, message){
		var append = function(object, key, value){
			if(object[key]){
				if(Array.isArray(object[key])){
					object[key].push(value);
				}
				else{
					throw 'handler objects are not array, perhaps someone is setting them incorrectly';
				}
			}
			else{
				object[key] = [value];
			}
		}

		if(eventType === 'add'){
			if(message){
				append(this.handlers, message.id, handler);
			}
			else{
				this.globalHandlers.add(handler);
			}
		}
		else if(eventType === 'remove'){
			if(message){
				append(this.offHandlers, message.id, handler);
			}
			else{
				this.offGlobalHandlers.add(handler);
			}
		}
	}

	unregisterHandlers(eventType, message){
		if(eventType === 'add'){
			delete this.handlers[message.id];
		}
		else if(eventType === 'remove'){
			delete this.offHandlers[message.id];
		}
		else if(eventType.id){
			delete this.handlers[eventType.id];
			delete this.offHandlers[eventType.id];
		}
	}

	unregisterGlobalHandlers(eventType){
		var _clearArray = function(array){
			while(array.length){
				array.pop();
			}
		};

		if(eventType === 'add'){
			_clearArray(this.globalHandlers);
		}
		else if(eventType === 'remove'){
			_clearArray(this.offGlobalHandlers);
		}
		else{
			_clearArray(this.globalHandlers);
			_clearArray(this.offGlobalHandlers);
		}
	}

	clearHandlers(eventType){
		var _clearObject = function(object){
			object.getOwnPropertyNames().forEach(property => {
				delete object[property];
			});
		};

		if(eventType){
			if(eventType === 'add'){
				_clearObject(this.handlers);
			}
			else if(eventType === 'remove'){
				_clearObject(this.offHandlers);
			}
		}
		else{
			_clearObject(this.handlers);
			_clearObject(this.offHandlers);
		}
	}

	clearGlobalhandlers(eventType){
		var _clearArray = function(array){
			while(array.length){
				array.pop();
			}
		};

		if(eventType){
			if(eventType === 'add'){
				_clearArray(this.globalHandlers);
			}
			else if(eventType === 'remove'){
				_clearArray(this.globalOffHandlers);
			}
		}
		else{
			_clearArray(this.globalHandlers);
			_clearArray(this.globalOffHandlers);
		}
	}
}

module.exports = {
	HandlerManager: HandlerManager,
	reactDefault: async function(message){
		for(var reaction of Array.from(arguments).slice(1)){
			await message.react(reaction);
		}
	},
	createLetterList: function(){
		const LETTER_A = 127462;
		var list = {};
		'abcdefghijklmnopqrstuvwxyz'.split('').forEach((char, index) => {
			list[char] = String.fromCodePoint(LETTER_A + index);
			// console.log('Letter', char, ':', String.fromCodePoint(LETTER_A + index), 'with code', LETTER_A + index);
		});

		return list;
	},
	getLetter: function(reaction){
		var num = reaction.emoji.name.codePointAt(0) - 127462;
		return num > -1 && num < 26 ? 'abcdefghijklmnopqrstuvwxyz'.charAt(num) : undefined;
	},
};
