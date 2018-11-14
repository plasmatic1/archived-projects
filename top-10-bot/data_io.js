//Imports
var fs = require('fs');

var main = require('./index.js');

//Consts
const DATA_DIR = './data/';

/*
loadServerFile: Loads the JSON data from a server data file
 - folderFiles: the list of files in the server data folder
 - pathToFolder: the file path to the server data folder
 - fileName: the name of the file to be read
 
 Returns a [data, completed]
 - data: the loaded from the file as a JS object, will be undefined if completed == false
 - completed: whether the file could be read or not
 */
function loadServerFile(folderFiles, pathToFolder, fileName){
    if(folderFiles.includes(fileName)){
        main.debug('  - Found {0} for server!', fileName);
        return [JSON.parse(fs.readFileSync(pathToFolder + fileName, 'utf-8')), true];
    }
    else{
        main.debug('  - Could not find {0} for server!', fileName);
        return [undefined, false];
    }
}

/*
loadServerFiles: Loads the JSON data from the ./data/ folder for all the current servers
    
Returns an object containing the data for each server with each server ID mapped to a data value
- There is an additional property 'length', which contains the amount of servers loaded
*/
function loadServerFiles(){
    var servers = fs.readdirSync(DATA_DIR);
    var data = {};

    main.debug('Found {0} servers: ', servers.length);
    data.length = servers.length;

    servers.forEach(function(val, _){
        main.debug('- id \'{0}\'', val);
        const FOLDER_PATH = DATA_DIR + val + '/';
        var dataFolderFiles = fs.readdirSync(FOLDER_PATH); //List of files inside the server data folder files
        var serverData = {};

        var [cfg, comp1] = loadServerFile(dataFolderFiles, FOLDER_PATH, 'config.json');
        var [dataa, comp2] = loadServerFile(dataFolderFiles, FOLDER_PATH, 'data.json');
        serverData.cfg = cfg;
        serverData.data = dataa;
        serverData.complete = comp1 && comp2;

        if(!serverData.complete){
            serverData = createDefault();
        }
        
        data[val] = serverData;
    });

    return data;
}

function writeServerFiles(dataa){
    for(var [id, data] of dataa){
        writeSingleServerFile(id, data);
    }
}

function writeSingleServerFile(idd, dataa){
    var serverFolderPath = DATA_DIR + idd + '/';
    if(!fs.existsSync(serverFolderPath)){
        fs.mkdirSync(serverFolderPath);
    }

    fs.writeFileSync(serverFolderPath + 'config.json', JSON.stringify(dataa.cfg));
    fs.writeFileSync(serverFolderPath + 'data.json', JSON.stringify(dataa.data));
}

/*

*/
function deleteServerFiles(idd){
    var serverFolderPath = DATA_DIR + idd + '/';

    if(fs.existsSync(serverFolderPath)){
        fs.unlinkSync(serverFolderPath + 'config.json');
        fs.unlinkSync(serverFolderPath + 'data.json');
        fs.rmdirSync(serverFolderPath);
    }
}

function createDefConfig(){
    var obj = {
        modRole: null,
        leaderboardMessage: null,
        leaderboardMessageChannel: null,
        entryFormat: '',
        submissionFormat: '',
        entryCount: 10
    };

    return obj;
}

function createDefData(){
    var obj = {
        entries: [],
        submissions: []
    };

    return obj;
}

function createDefault(){
    var obj = {};

    obj.cfg = createDefConfig();
    obj.data = createDefData();

    return obj;
}

module.exports = {
    loadServerFiles, writeServerFiles, writeSingleServerFile, deleteServerFiles, createDefConfig, createDefData, createDefault
};