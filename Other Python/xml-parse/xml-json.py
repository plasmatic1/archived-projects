from sys import argv, exit

# # # Initialization of Program # # #

## defs ##

USAGE = '''
USAGE: brainfuck.py [-file file] [-input-file file] [-size int] [-rw-int]
    -file        : Specifies the path to the BF script used [MANDATORY]
    -input-file  : Specifies the path to the file used for input; if this is not specified, STDIN is used
    -size        : Specifies the size of the array used, default is 30000

    -rw-int      : Input and output will be done as integers instead of as characters
    -no-input    : Specifies that there is no input for the program
'''

PAUSE = lambda: input('Press ENTER to continue')

def toflag(args):
    index = 0
    filled = 0
    flags = {}
    while index < len(args):
        arg = args[index]
        if arg[0] == '-':
            if (index == len(args) - 1) or (args[index + 1][0] == '-'):
                flags[arg[1:]] = None
            else:
                flags[arg[1:]] = args[index + 1]
                del args[index + 1]
                filled += 1
        index += 1
    return (flags, filled)

## Mandatory Args ##

flags, argc = toflag(argv[1:])
