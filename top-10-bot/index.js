String.prototype.format = function(args) {
    return this.replace(/{(\d+)}/g, function(match, number) {
      return typeof args[number] != 'undefined'
        ? args[number]
        : match
      ;
  });
}

String.prototype.isNumber = function(){
    return this.match(/-?\d+/);
}

String.prototype.$ = function(){
    return this.format(arguments);
}

Array.prototype.remove = function(index){
    return this.slice(0, index).concat(this.slice(index + 1, this.length));
}

//Exports to other classes
module.exports = {
    debug,
    data: function(){
        return data;
    }
};

//"Constants"
const DEBUG_PREF = '[DEBUG]';
var LOGIN_URL = undefined;

const EQUALS_PLHDR = '%space%';
const EQUALS_PLHDR_REGEX = /%space%/g;

//Imports
var discordjs = require('discord.js');
var clipboardy = require('clipboardy');
var util = require('util');

var dataIo = require('./data_io.js');
var cmds = require('./commands.js');
var manager = require('./lb_manager.js');

var config = require('./config.json');
var help = require('./help.json');

//Variables
var client = new discordjs.Client();
var data = {};

//Data Loading
data = dataIo.loadServerFiles();

//Event Handling
client.login(config.token);
client.on('ready', function(e){
	console.log('Bot Initialized!');
	LOGIN_URL = 'https://discordapp.com/oauth2/authorize?&client_id=' + client.user.id + '&scope=bot&permissions=0';

	if(config.showUrl){
		console.log('URL to add bot to server is', LOGIN_URL);
		clipboardy.writeSync(LOGIN_URL);
    }

    //Info commands ------------------------------------------------------------------------------------------------------------------------------------------------------

    cmds.register('help', function(_, channel, _, args, message){
        var final = '';
        var categories = help.categories;

        if(args.length < 1){
            final += help.topBanner + '\n';
            for(var key of Object.keys(categories)){
                final += '- \'{0}\': Help for \'{1}\'\n'.$(key, categories[key].name);
            }
        }
        else{
            var category = categories[args[0]];

            if(!category){
                message.reply('Invalid help category \'{0}\''.$(args[0]));
                return;
            }

            final += '-- {0} --\n'.$(category.name);
            category.lines.forEach(line => final += line + '\n');
        }

        final += '\n' + help.footnoteBanner + '\n' + help.footnotes.join('\n');

        channel.send(final);
    }, cmds.NONE);

    cmds.register('github', function(_, channel){
        channel.send('The repository is here! **-> https://github.com/Elytrus/top-10-bot <-**')
    });

    //Leaderboard management ------------------------------------------------------------------------------------------------------------------------------------------------------

    cmds.register('lbinit', function(_, channel, guild, _, message){
        if(data[guild.id]){
            message.reply('Leaderboard has already been initialized for server \'{0}\''.$(guild.name));
            return;
        }

        var newObj = dataIo.createDefault();
        var role = findRole(guild, config.defaultLeaderboardModeratorRole);

        if(role){
            newObj.cfg.modRole = role.id;
            channel.send('Set Leaderboard Moderator role to {0} ({1})'.$(config.defaultLeaderboardModeratorRole, role.id));
        }
        else{
            channel.send('Could not find default Leaderboard Moderator role, defaulting to permission \'0x8\'');
        }

        writeGuildData(guild.id, newObj);

        message.reply('Initialized leaderboard for server \'{0}\''.$(guild.name));
        
    }, cmds.NONE);

    cmds.register('lbdelete', function(_, _, guild, _, message){
        deleteGuild(guild.id);
        message.reply('Deleted leaderboard for server \'{0}\''.$(guild.name));
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lbmodrole', function(_, _, guild, args, message){
        if(args.length == 0){
            var modRole = data[guild.id].cfg.modRole;
            if(modRole){
                message.reply('The leaderboard mod role is \'{0}\'!'.$(guild.roles.get(data[guild.id].cfg.modRole).name));
            }
            else{
                message.reply('There is no leaderboard mod role!');
            }
        }
        else{
            var roleName = args.join(' ');
            var role = findRole(guild, roleName);

            if(role){
                data[guild.id].cfg.modRole = role.id;
                saveGuildData(guild);

                message.reply('Set moderation role to \'{0}\' ({1})'.$(role.name, role.id));
            }
            else{
                message.reply('Could not find role {0}'.$(roleName));
            }
        }
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lbconfig', function(_, channel, guild, args){
        if(args.length == 0){
            var final = '\\*\\***Configuration for Server \'{0}\'**\\*\\*\n'.$(guild.name);
            var config = data[guild.id].cfg;
            for(var key of Object.keys(config)){
                final += '{0}: {1}\n'.$(key, config[key]);
            }
            channel.send(final);
        }
        else if(args.length == 1){
            var config = data[guild.id].cfg;
            
            if(config[args[0]] != undefined){
                channel.send('{0}: {1}'.$(args[0], config[args[0]]));
            }
            else{
                channel.send('Config value \'{0}\' does not exist!'.$(args[0]));
            }
        }
        else{
            var config = data[guild.id].cfg;

            if(config[args[0]] != undefined){
                //Check for int
                var val;
                if(args[1].isNumber()){
                    val = parseInt(args[1]);
                }
                else{
                    val = args.slice(1).join(' ');
                }

                config[args[0]] = val;
                saveGuildData(guild);

                channel.send('Set config value \'{0}\' to \'{1}\' (Type \'{2}\')'.$(args[0], val, typeof val));
            }
            else{
                channel.send('Config value \'{0}\' does not exist!'.$(args[0]));
            }
        }
    }, cmds.REQ_MOD_ROLE);

    //Entry management ------------------------------------------------------------------------------------------------------------------------------------------------------

    cmds.register('lbsetentry', function(_, channel, guild, args, message){
        fulfillEntryList(guild.id);

        //Placement, name, author, percent
        var entryObj = parseKVSet(args.join(' '));

        if(Object.keys(entryObj).length > 4){
            channel.send('Too many pairs!');
            return;
        }

        function hasProp(prop){
            if(entryObj[prop]){
                return true;
            }
            channel.send('Key \'{0}\' was not found!'.$(prop));
            return false;
        }

        function hasProps(){
            return Array.from(arguments).map(prop => hasProp(prop)).reduce((acc, curr) => acc && curr);
        }

        if(!hasProps('placement', 'name', 'author', 'percent')){
            return;
        }

        if(!entryObj.placement.isNumber()){
            message.reply('Value for key \'placement\' must be a number!');
            return;
        }

        entryObj.placement = parseInt(entryObj.placement);
        entryObj.submissions = [];
        var index = entryObj.placement;
        delete entryObj.placement;
        data[guild.id].data.entries[index] = entryObj;
        saveGuildData(guild);

        message.reply('Set entry #{0}!'.$(index));
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lbremoveentry', function(_, _, guild, args, message){
        if(!args[0].isNumber()){
            message.reply('Record index must be a number');
        }

        var index = parseInt(args[0]);
        var entryCount = data[guild.id].cfg.entryCount;
        var entries = data[guild.id].data.entries;

        if(index >= entryCount || index < 0){
            message.reply('Placement value must be between 0 and {0}!'.$(entryCount));
            return;
        }

        data[guild.id].data.entries = entries.remove(index);

        message.reply('Removed entry {0}!'.$(index));

        fulfillEntryList(guild.id);
        saveGuildData(guild);
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lblistentries', function(_, channel, guild){
        var final = '**\\*\\*List Entries for server \'{0}\'\\*\\***\n'.$(guild.name);
        data[guild.id].data.entries.forEach((entry, ind) => final += entry == null ? '{0}: Nothing.\n'.$(ind + 1) : ('{0}: \'{1}\', by \'{2}\' (Requires {3}% to qualify)\n'.$(ind + 1, entry.name, entry.author, entry.percent)));

        channel.send(final);
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lbtruncateentries', function(_, _, guild, _, message){
        data[guild.id].data.entries = data[guild.id].data.entries.slice(0, data[guild.id].cfg.entryCount);

        message.reply('Truncated leaderboard entries!');
        saveGuildData(guild);
    });

    //Creating+Editing leaderboard --------------------------------------------------------------------------------------------------------------------------------------------------

    cmds.register('lbcreatelbmessage', async function(_, channel, guild, _, om){
        var cfg = data[guild.id].cfg;
        fulfillEntryList(guild.id);

        if(cfg.leaderboardMessage && cfg.leaderboardMessageChannel){
            om.reply('Leaderboard Message already exists!');
            return;
        }

        var final = demonsListMessageText(guild.id);
        var message = await channel.send(final);

        cfg.leaderboardMessage = message.id;
        cfg.leaderboardMessageChannel = channel.id;
        om.delete();

        saveGuildData(guild);
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lbdeletelbmessage', async function(_, channel, guild, _, messagee){
        var cfg = data[guild.id].cfg;

        if(cfg.leaderboardMessage == null || cfg.leaderboardMessageChannel == null){
            messagee.reply('There is no leaderboard message!');
            return;
        }

        var message = null;

        try{
            message = await guild.channels.get(cfg.leaderboardMessageChannel).fetchMessage(cfg.leaderboardMessage);
        } 
        catch(err){
            channel.send('An error occured during message deletion: {0}'.$(err));
            return;
        }

        message.delete();

        cfg.leaderboardMessage = null;
        cfg.leaderboardMessageChannel = null;
        saveGuildData(guild);

        messagee.reply('Deleted leaderboard message!');
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lbreloadlbmessage', async function(_, _, guild, _, messagee){
        reloadLbMessage(guild, messagee);
    }, cmds.REQ_MOD_ROLE);

    //Record submission and management ------------------------------------------------------------------------------------------------------------------------------------------

    cmds.register('lbsubmitrecord', function(_, channel, guild, args, message){
        var entryObj = parseKVSet(args.join(' '));

        if(Object.keys(entryObj).length > 5){
            channel.send('Too many pairs!');
            return;
        }

        function hasProp(prop){
            if(entryObj[prop]){
                return true;
            }
            channel.send('Key \'{0}\' was not found!'.$(prop));
            return false;
        }

        function hasProps(){
            return Array.from(arguments).map(prop => hasProp(prop)).reduce((acc, curr) => acc && curr);
        }

        //$player, $percent, $proof, and $hz
        if(!hasProps('placement', 'player', 'percent', 'proof', 'hz')){
            return;
        }

        if(!entryObj.placement.isNumber()){
            message.reply('Value for key \'placement\' must be a number!');
            return;
        }

        entryObj.placement = parseInt(entryObj.placement);

        var entryCount = data[guild.id].cfg.entryCount;
        if(entryObj.placement >= entryCount || entryObj.placement < 0){
            message.reply('Placement value must be between 0 and {0}!'.$(entryCount));
            return;
        }

        if(!data[guild.id].data.entries[entryObj.placement]){
            message.reply('Invalid placement! ({0})'.$(entryObj.placement));
            return;
        }

        data[guild.id].data.submissions.push({
            placement: entryObj.placement,
            player: entryObj.player,
            percent: entryObj.percent,
            proof: entryObj.proof,
            hz: entryObj.hz
        });

        message.reply('Submission added!');

        saveGuildData(guild);
    }, cmds.REQ_INIT);

    cmds.register('lblistpending', function(_, channel, guild){
        var final = '**\\*\\*Current Pending Submissions for Server \'{0}\'\\*\\***\n'.$(guild.name);

        data[guild.id].data.submissions.forEach((submission, index) => {
            final += '({5}) - \'{0}\': {1}% on \'{2}\' on {3}hz (Proof: \'{4}\')\n'.$(submission.player, submission.percent, data[guild.id].data.entries[submission.placement].name, submission.hz, submission.proof, index);
        });

        channel.send(final);
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lbapproverecord', function(_, _, guild, args, message){
        if(!args[0].isNumber()){
            message.reply('Record index must be a number');
        }

        var index = parseInt(args[0]);
        var submissions = data[guild.id].data.submissions;
        var entries = data[guild.id].data.entries;

        var submission = submissions[index];

        if(entries[submission.placement] == null){
            message.reply('Entry {0} has not been initialized yet!'.$(index));
            return;
        }

        data[guild.id].data.submissions = submissions.remove(index);
        entries[submission.placement].submissions.push(submission);

        message.reply('Approved submission {0}!'.$(index));

        saveGuildData(guild);
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lbrejectrecord', function(_, _, guild, args, message){
        if(!args[0].isNumber()){
            message.reply('Record index must be a number');
        }

        var index = parseInt(args[0]);
        var submissions = data[guild.id].data.submissions;
        var entries = data[guild.id].data.entries;

        if(entries[submissions[index].placement] == null){
            message.reply('Entry {0} has not been initialized yet!'.$(index));
            return;
        }

        data[guild.id].data.submissions = submissions.remove(index);

        message.reply('Rejected submission {0}!'.$(index));

        saveGuildData(guild);
    }, cmds.REQ_MOD_ROLE);

    cmds.register('lbremoveapprovedrecord', function(_, _, guild, args, message){
        if(!args[0].isNumber()){
            message.reply('Entry index must be a number');
            return;
        }

        if(!args[1].isNumber()){
            message.reply('Submission index must be a number');
            return;
        }

        var entryIndex = parseInt(args[0]);
        var submissionIndex = parseInt(args[1]);

        var entryCount = data[guild.id].cfg.entryCount;
        if(entryIndex >= entryCount || entryIndex < 0){
            message.reply('Entry index value must be between 0 and {0}!'.$(entryCount));
            return;
        }

        if(data[guild.id].data.entries[entryIndex] == null){
            message.reply('Invalid entry!');
            return;
        }

        var submissions = data[guild.id].data.entries[entryIndex].submissions;
        var submissionCount = submissions.length;
        if(submissionIndex >= submissionCount || submissionIndex < 0){
            message.reply('Submission index value must be between 0 and {0}!'.$(submissionCount));
            return;
        }

        data[guild.id].data.entries[entryIndex].submissions = submissions.remove(submissionIndex);
        saveGuildData(guild);
        
        message.reply('Removed submission {0} from entry {1}'.$(submissionIndex, entryIndex));
    });

    //Debug and Owner commands ---------------------------------------------------------------------------------------------------------------------------------------------------

    cmds.register('printdataobj', function(author, channel, _, _, message){
        if(author.id != config.ownerID){
            message.reply('Insuffucient permissions!');
            return;
        }

        channel.send(JSON.stringify(data));
    }, cmds.NONE);

    cmds.register('reloadlb', function(author, _, _, _, message){
        if(author.id != config.ownerID){
            message.reply('Insuffucient permissions!');
            return;
        }

        data = dataIo.loadServerFiles();
        message.reply('Reloaded data files!');
    }, cmds.NONE);
});

client.on('message', async function(message){
    cmds.handleMessage(message, await message.guild.fetchMember(message.author));
});

function debug(msg){
    if(arguments.length > 1){
        console.log(DEBUG_PREF, msg.format(Array.prototype.slice.call(arguments, 1)));
    }
    else{
        console.log(DEBUG_PREF, msg);
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

function findRole(guild, name){
    return guild.roles.find(role => role.name == name);
}

async function tagFromID(guild, id){
    var member = await guild.fetchMember(id);

    return new Promise(function(res, _){
        if(!member){
            res('undefined');
        }

        res(member.user.username + '#' + member.user.discriminator);
    });
}

function writeGuildData(guild, dataa){
    data[guild] = dataa;
    dataIo.writeSingleServerFile(guild, dataa);
}

function saveGuildData(guild){
    dataIo.writeSingleServerFile(guild.id, data[guild.id]);
}

function deleteGuild(guild){
    delete data[guild];
    dataIo.deleteServerFiles(guild);
}

function parseKVSet(str){
    var obj = {};
    // console.log(str.split(',').map(strr => strr.replace(/(".*?")/g, match => '')));
    for(var pair of str.split(',').map(strr => strr.replace(/(?<=").*?(?=")/g, match => match.replace(/=/g, EQUALS_PLHDR)).trim().split('='))){
        var [key, val] = pair;
        obj[key] = val.replace(EQUALS_PLHDR_REGEX, '=');
    }

    return obj;
}

//Makes sure to make the array of list entries is of the proper length (it appends any elements if required)
function fulfillEntryList(id){
    var maxEntries = data[id].cfg.entryCount;
    var entries = data[id].data.entries;

    for(var i = entries.length; i < maxEntries; i++){
       entries.push(null);
    }

    dataIo.writeSingleServerFile(id, data[id]);
}

function demonsListMessageText(id){
    var entries = data[id].data.entries;
    var format = data[id].cfg.entryFormat;
    var submissionFormat = data[id].cfg.submissionFormat;
    var final = '```**Top {0} Demons List**\n\n'.$(entries.length);
    //$name, $author, and $percent
    entries.forEach((entry, index) => {
        if(entry == null){
            final += 'None.\n\n';
            return;
        }

        final += format.replace(/\$placement/g, index + 1).replace(/\$name/g, entry.name).replace(/\$author/g, entry.author).replace(/\$percent/g, entry.percent) + '\n';

        if(entry.submissions.length > 0){
            for(var submission of entry.submissions){
                //$player, $percent, $proof, and $hz
                final += submissionFormat.replace(/\$player/g, submission.player).replace(/\$percent/g, submission.percent).replace(/\$hz/g, submission.hz).replace(/\$proof/g, submission.proof) + '\n';
            }
        }
        else{
            final += 'No submissions.\n';
        }
        final += '\n';
    });

    return final + '```';
}

async function reloadLbMessage(guild, messagee){
    var cfg = data[guild.id].cfg;
    fulfillEntryList(guild.id);

    if(cfg.leaderboardMessage == null || cfg.leaderboardMessageChannel == null){
        messagee.reply('There is no leaderboard message!');
        return;
    }

    var final = demonsListMessageText(guild.id);
    var message = null;

    try{
        message = await guild.channels.get(cfg.leaderboardMessageChannel).fetchMessage(cfg.leaderboardMessage);
    } 
    catch(err){
        channel.send('An error occured during message deletion: {0}'.$(err));
        return;
    }

    message.edit(final);
    messagee.reply('Reloaded leaderboard message!');
}