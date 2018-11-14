//Imports
var main = require('./index.js');
var config = require('./config.json');

//Vars
var commands = {};
var requires = {};

const NONE = 0;
const _R_MODR = 1 << 1;

const REQ_INIT = 1;
const REQ_MOD_ROLE = REQ_INIT | _R_MODR;

//Funs
function handleMessage(message, member){
    var content = message.content;
    
    if(content.startsWith(config.prefix)){
        var stripContent = content.substring(config.prefix.length, content.length).split(' ');

        if(stripContent[0] in commands){
            if(message.author.bot){
                return;
            }

            var flags = requires[stripContent[0]];

            if(flags & REQ_INIT){
                var serverData = main.data()[message.guild.id];

                if(serverData){
                    if(flags & REQ_MOD_ROLE){
                        var modRole = serverData.cfg.modRole;
                        if(!modRole){
                            message.channel.send('**Warn: No leaderboard moderation role exists, defaulting to permission \'0x8\' for moderation access!**');

                            if(!member.hasPermission(8)){ //8 is admin permission
                                message.reply('You don\'t have the required permission (0x8) to execute this command!');
                                return;
                            }
                        }
                        else{
                            if(!member.roles.has(modRole)){
                                message.reply('You don\'t have the required leaderboard moderation role to execute this command!');
                                return;
                            }
                        }
                    }
                }
                else{
                    message.reply('The leaderboard for this server has not been initialized yet! (Use the command \'lbinit\' to initialize the leaderboard)');
                    return;
                }
            }

            commands[stripContent[0]](message.author, message.channel, message.guild, stripContent.slice(1), message);
        }
    }
}

/*
Registers command under name 'name' with handler 'handler'
- Name: Name of command as string
- Handler: Command handler for said command

function handler can take the parameters (author, channel, guild, args, message)
- Author: Author of the message
- Channel: Channel where the message was sent
- Guild: Guild where the message was sent
- Args: Arguments of message
- Message: The message object itself
*/
function register(name, handler, requiress){
    commands[name] = handler;
    requires[name] = requiress;
    main.debug('Registered command {0}!', name);
}

function getCommands(){
    return Object.keys(commands);
}

//Exports
module.exports = {
    handleMessage, register, getCommands,
    NONE, REQ_INIT, REQ_MOD_ROLE
};