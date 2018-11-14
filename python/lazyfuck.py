#imports
import os
#constants (Configuration)
##PRINTED
DEBUG=True
##NOT PRINTED
DIV='--------------------------------------------'
DEBUG_MSG='[DEBUG]: '
#methods
def return_brainfuck(char):
    code=ord(char)
    return '+'*code+'.'+'-'*code
def pend_input(message):
    input_=input('%s(y/n): '%message)
    if input_=='y':
        return True
    else:
        return False
#intro
print('Welcome to LazyFuck brainfuck')
print('Current working directory is %s'%os.getcwd())
print('Files will be written here')
print('Enter \'q\' for the file name for the program to terminate')
print('Configuration settings: (These can be edited within the script itself)')
print('DEBUG: %s'%DEBUG)
print(DIV)
#loop
while True:
    fname=input('File name: ')
    if fname=='q':
        break
    if os.path.exists(fname):
        print('File already exists!')
        overwrite=pend_input('Overwrite file?')
        if overwrite:
            file=open(fname, 'w')
        else:
            continue
    else:
        file=open(fname,'w+')
    strgen=input('String to be generated into bf: ')
    
    for x in strgen:
        if DEBUG:
            print('%sParsing character %s'%(DEBUG_MSG, x))
        file.write(return_brainfuck(x))
    file.close()
    print('File writing complete')
    print(DIV)
