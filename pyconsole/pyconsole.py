import os
import pathlib

#Constants
LIB_PATH = '%s\\lib_path.txt' % os.getenv('userprofile')

#Set Directory
if not os.path.isfile(LIB_PATH):
    print('Library path not set, configuration command should be run to properly install')
else:
    cwdf = open(LIB_PATH)
    cwd = cwdf.read()
    print('Current working directory is %s' % cwd)
    cwdf.flush()
    cwdf.close()
    os.chdir(cwd)

#Get Files
selfname = pathlib.Path(__file__).parts[-1]
modulelist = [str(path) for path in pathlib.Path().glob('*.py') if path.parts[-1] != selfname]

#Modules
modules = {}

#Methods
def debug(msg):
    print('[DEBUG]: %s' % msg)

def getif(module, key, default=None):
    moduledict = module.__dict__
    if key in moduledict:
        return moduledict[key]
    return default

#Loading Modules
for path in modulelist:
    name = path[:-3]
    module = __import__(name)
    if not getif(module, 'skip'):
        modules[name] = (module.exec, getif(module, 'desc', 'No Info'), getif(module, 'usage', 'No Help'), getif(module, 'flags', False))
        debug('Loaded module %s' % name)

#Default Modules

#-- Module Help --
def helpexec(args):
    if args:
        cmd = args[0]
        if cmd in modules:
            module = modules[cmd]
            print('== Command \'%s\' ==' % cmd)
            print('Desc : %s' % module[1])
            print('Usage : %s' % module[2])
        else:
            print('Command not found!')
    else:
        print('== Commands ==')
        for cmd in modules.keys():
            print('%s : %s' % (cmd, modules[cmd][1]))

modules['help'] = (helpexec, 'Help command', 'help [command]', False)
debug('Loaded module help')

#-- Module Configure --
def configureexec(args):
    dir_ = os.getcwd()
    pathf = open(LIB_PATH, 'w')
    pathf.write(dir_)
    pathf.flush()
    pathf.close()
    debug('Saving dir \'%s\' as library directory' % dir_)
    path = os.getenv('path')
    if dir_ in path:
        debug('The working directory is already in the PATH variable')
    else:
        os.system('setx path "%s;%s"\n' % (path, dir_))
    print('Configured!')

#modules['configure'] = (configureexec, 'Makes pyconsole executable from anywhere', 'configure', False)
#debug('Loaded module configure')

#Commands Running
while 1:
    cmd = input('> ').split(' ')
    args = cmd[1:]
    flags = {}
    if cmd[0] in modules:
        module = modules[cmd[0]]
        if module[3]:
            if len(args) > 0:
                index = 0
                while index < len(args):
                    arg = args[index]
                    if arg[0] == '-':
                        flag = arg[1:]
                        value = None
                        if index + 1 < len(args) and args[index + 1][0] != '-':
                            value = args[index + 1]
                            del args[index + 1]
                        else:
                            value = None
                    flags[flag] = value
                    index += 1
            args = flags
        modules[cmd[0]][0](args)
    else:
        print('Command not found')
