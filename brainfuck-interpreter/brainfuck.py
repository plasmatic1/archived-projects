from sys import argv, exit

# # # Initialization of Program # # #

## defs ##

USAGE = '''
USAGE: brainfuck.py <-file file> [-input-file file] [-size int] [-rw-int] [-no-input]
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

INPUT_FILE = None
FILE = None
SIZE = 30000
INSTRUCTIONS = list('<>+-.,[]')
INT_IO = 'rw-int' in flags

if 'file' in flags:
    FILE = flags['file']
else:
    print('File not specified!')
    print(USAGE)
    exit()

## Non-Mandatory Args ##

if FILE.split('.')[1] != 'bf':
    print('Invalid file extension, file extension must be \'.bf\'')
    PAUSE()
    exit()

if 'size' in flags:
    try:
        SIZE = int(flags['size'])
    except ValueError:
        print('Invalid size argument (Must be int)')
        exit()
else:
    print('Note: No size given! Defaulting to 30000')

if 'input-file' in flags:
    INPUT_FILE = flags['input-file']
elif 'no-input' not in flags:
    print('Note: No input file given, defaulting to standard input!')

if INT_IO:
    print('Note: Integer I/O is being used!')

print()

# # # PARSING AND EXECUTION # # #

## Basic Parsing ##

input_chars = []
instructions = []
loop_stack = []
loops = {}
memory = [0] * SIZE
pointer = 0
index = 0
out = None

if INT_IO:
    out = []
else:
    out = ''

try:
    file = open(FILE, 'r')
    for line in file.readlines():
        for char in list(line):
            if char in INSTRUCTIONS:
                instructions.append(char)
    file.close()
except IOError:
    print('File could not be opened!')
    PAUSE()
    exit()

if not 'no-input' in flags:
    if INPUT_FILE:
        try:
            input_file = open(INPUT_FILE, 'r')
            for line in input_file.readlines():
                if INT_IO:
                    try:
                        input_chars += map(int, line.split(' '))
                    except ValueError:
                        print('Malformed input! (Input must be integers separated by spaces (or line breaks))')
                        exit()
                else:
                    input_chars += list(line)
        except IOError:
            print('Input File could not be opened!')
            PAUSE()
            exit()
    else:
        if INT_IO:
            try:
                input_chars = map(int, input('Input here: ').split(' '))
            except ValueError:
                print('Malformed input! (Input must be integers separated by spaces)')
                exit()
            
        input_chars = list(input('Input here: '))
        print()

## loop parsing ##

EOF = len(instructions)

while index < EOF:
    ins = instructions[index]

    if ins == '[':
        loop_stack.append(index)
    elif ins == ']':
        if len(loop_stack) == 0:
            print('Error encountered while parsing: Danging loop ending!')
            exit()
        start = loop_stack.pop()
        loops[start] = index
        loops[index] = start
    index += 1

if len(loop_stack) > 0:
    print('Error encountered while parsing: Loop not ended!')
    exit()

## execution ##

index = 0
while index < EOF:
    ins = instructions[index]
    
    if ins == '>':
        pointer += 1
    elif ins == '<':
        pointer -= 1
    elif ins == '+':
        memory[pointer] += 1
    elif ins == '-':
        memory[pointer] -= 1
    elif ins == '.':
        if INT_IO:
            out += [memory[pointer]]
        else:
            out += chr(memory[pointer])
    elif ins == ',':
        if INT_IO:
            memory[pointer] = input_chars.pop()
        else:
            memory[pointer] = ord(input_chars.pop())
    elif ins == '[':
        if memory[pointer] == 0: 
            index = loops[index]
    elif ins == ']':
        if memory[pointer] != 0:
            index = loops[index]
    index += 1
    
if INT_IO:
    print(' '.join(map(str, out)))
else:
    print(out)
