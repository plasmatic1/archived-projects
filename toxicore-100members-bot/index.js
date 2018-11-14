String.prototype.format = function(args) {
    return this.replace(/{(\d+)}/g, function(match, number) {
      return typeof args[number] != 'undefined'
        ? args[number]
        : match
      ;
  });
};


//"Constants"
const DEBUG_PREF = '[DEBUG]';
// const QUEUE_DELAY = 1000;
const SRC_DELETE_WAIT = 5000;
var LOGIN_URL = undefined;

//Imports
var discordjs = require('discord.js');
var clipboardy = require('clipboardy');
var util = require('util');
var fs = require('fs');

var config = require('./config.json');

//Variables
var client = new discordjs.Client();
var cmds = {};
var ownerOnly = {};

console.log('Attempting login...');

//Event Handling
client.login(config.token);
client.on('ready', function(e){
	console.log('Bot Initialized!');
	LOGIN_URL = 'https://discordapp.com/oauth2/authorize?&client_id=' + client.user.id + '&scope=bot&permissions=0';

	if(config.showUrl){
		console.log('URL to add bot to server is', LOGIN_URL);
		clipboardy.writeSync(LOGIN_URL);
    }

    register('who me?', function(_, channel){
        channel.send('i am plas no joke plz dont hurt me');
    });

    register('favourite?', function(_, channel){
        channel.send('i like shorts because theyre comfy and easy to wear');
    });

    register('mention me', function(_, _, _, _, message){
        message.reply('Sure!');
    });

    register('src??', function(_, channel){
        channel.send('https://gist.github.com/Elytrus/e546b9fa887dddeb4303e700c34a1f4e').then(message => message.delete(SRC_DELETE_WAIT));
    });

    register('nicks', async function(author, _, guild, _, message){
        var members = getMembers(guild).map(member => '- Member \'{0}#{1}\', Nick \'{2}\''.format([member.user.username, member.user.discriminator, member.nickname]));
        
        //Send DM
        var dmChannel = await author.createDM();
        dmChannel.send(members.join('\n').substr(0, 1999));
        message.reply('Check your DMs!');
    });

    register('player count', function(_, channel, guild){
        var excludes = readExcludeIDs();
        var members = getMembers(guild).filter(member => !member.user.bot).filter(member => !excludes.includes(member.user.id));
        channel.send('There are {0} non-bot users in this server right now!'.format([members.length]));
    });

    register('save nicks', function(_, _, guild, _, message){
        var data = {};
        getMembers(guild).forEach(member => data[member.id] = member.nickname);

        fs.writeFile(config.nickFile, JSON.stringify(data), () => {
            message.reply('Saved current nicknames to {0}! Please check the file for more details'.format([config.nickFile]));
        });
    }, true);

    register('overwrite nicks', function(_, _, guild, _, message){
        var members = getMembers(guild);

        members.forEach(member => {
            member.setNickname(config.overwriteNick);
        });

        message.reply('Renamed all players!');
    }, true);

    register('reset nicks', function(_, channel, guild, _, message){
        var data = {};

        if(fs.existsSync(config.nickFile)){
            data = JSON.parse(fs.readFileSync(config.nickFile));
        }
        else{
            data = {};
        }

        for(var [id, member] of guild.members.entries()){
            if(data[id]){
                member.setNickname(data[id]);
            }
            else{
                member.setNickname(member.user.username);
            }
        }

        message.reply('Reset the nicks of all players!');
    }, true);

    register('add exclude', function(_, channel, guild, args){
        var tag = Array.from(args).join(' ');
        var member = fromUserTag(guild, tag);
        var excludes = readExcludeIDs();

        if(!member){
            channel.send('Member \'{0}\' could not be found!'.format([tag]));
            return;
        }

        if(excludes.includes(member.user.id)){
            channel.send('Member \'{0}\' is already part of the excludes list!'.format([tag]));
            return;
        }

        excludes.push(member.user.id);
        channel.send('Added member \'{0}\' (ID: {1}) to the excludes list!'.format([tag, member.user.id]));
        writeExcludeIDs(excludes);
    });
    
    register('remove exclude', function(_, channel, guild, args){
        var tag = Array.from(args).join(' ');
        var member = fromUserTag(guild, tag);
        var excludes = readExcludeIDs();

        if(!member){
            channel.send('Member \'{0}\' could not be found!'.format([tag]));
            return;
        }

        if(!excludes.includes(member.user.id)){
            channel.send('Member \'{0}\' is not part of the excludes list!'.format([tag]));
            return;
        }
        
        var remIndex = excludes.indexOf(member.user.id);
        excludes = excludes.slice(0, remIndex).concat(excludes.slice(remIndex + 1, excludes.length));

        channel.send('Removed member \'{0}\' (ID: {1}) from the excludes list!'.format([tag, member.user.id]));
        writeExcludeIDs(excludes);
    });

    register('list excludes', async function(_, channel, guild, _){
        var excludes = readExcludeIDs();
        var finalString = 'Excluded Players:\n';

        for(var entryID of excludes){
            var tag = await tagFromID(guild, entryID);
            var entryString = '- \'{0}\' (ID: {1})'.format([tag, entryID]);
            finalString += entryString + '\n';
        }

        channel.send(finalString);
    });

    // register('stealpic', async function(_, _, guild, args){
    //     var members = await guild.fetchMembers();
    //     clipboardy.writeSync(JSON.stringify(members));
    //     console.log(members);
    // });
});

client.on('message', async function(message, user){
    var {author, channel, guild, content} = message;

    if(author.bot){
        return;
    }

    handle(author, channel, guild, content, message);
});

function debug(msg){
    if(arguments.length > 1){
        console.log(DEBUG_PREF, msg.format(Array.prototype.slice.call(arguments, 1)));
    }
    else{
        console.log(DEBUG_PREF, msg);
    }
}

function register(name, handler, ownerOnlyy){
    cmds[name] = handler;
    if(ownerOnlyy){
        ownerOnly[name] = 1;
    }
}

function getMembers(guild){
    return Array.from(guild.members.values());
}

//Creates GuildMember from Username#Discriminator
function fromUserTag(guild, tag){
    var [username, discriminator] = tag.split(/\#/g);

    for(var member of guild.members.values()){
        if(member.user.username == username && member.user.discriminator == discriminator){
            return member;
        }
    }
    return undefined;
}

function readExcludeIDs(){
    if(fs.existsSync(config.excludesFile)){
        return JSON.parse(fs.readFileSync(config.excludesFile));
    }
    return [];
}

function writeExcludeIDs(excludes){
    fs.writeFileSync(config.excludesFile, JSON.stringify(excludes));
}

// function queueMessages(channel){
//     var messages = Array.from(arguments).slice(1);
//     var index = 0, length = messages.length;

//     var id = setTimeout(() => {
//         if(index >= length){
//             cancelTimeout(id);
//         }

//         channel.send(messages[index]);

//         index++;
//     }, QUEUE_DELAY);
// }

async function tagFromID(guild, id){
    var member = await guild.fetchMember(id);

    return new Promise(function(res, _){
        if(!member){
            res('undefined');
        }

        res(member.user.username + '#' + member.user.discriminator);
    });
}

//Author, channel, guild, args, message
async function handle(author, channel, guild, content, message){
    for(let cmdName of Object.keys(cmds)){
        if(content.startsWith(cmdName)){
            var authorMember = await guild.fetchMember(author).catch(console.err);
            var roles = Array.from(authorMember.roles.values()).map(role => role.name);
            if(!roles.some(role => config.roles.includes(role))){
                channel.send('no u u succ u cant do that noob git gud');
                return;
            }

            if(ownerOnly[cmdName] && author.id != config.ownerID){
                channel.send('!! Owner Only !!');
                return;
            }
            
            debug('Executed command \'{0}\'', cmdName);
            cmds[cmdName](author, channel, guild, content.substr(cmdName.length + 1).split(' '), message);
        }
    }
}