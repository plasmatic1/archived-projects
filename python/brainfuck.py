#imports
import os
import re
#constants
U_LIMIT=2**31-1
L_LIMIR=
#globals
array=[]
ptr=0
output=[]
program_input=None
program_end=None
program_curr=0
program_queue=[]
labels=[]
#actions
def inc_ptr():
    global ptr
    ptr+=1
def dec_ptr():
    global ptr
    ptr-=1
def inc_val():
    global array, ptr
    array[ptr]+=1
def dec_val():
    global array, ptr
    array[ptr]-=1
def put_chr():
    global output, array, ptr
    output+=[chr(array[ptr])]
def inp_chr():
    global array, ptr, program_input
    array[ptr]=next(program_input)
def loop_start():
    global labels
    labels+=[program_curr]
def loop_end():
    global array, ptr, labels, program_curr, labels
    if array[ptr]==0:
        labels.pop()
    else:
        program_curr=labels[-1]
#miscs
for i in range(4000):
    array.append(0)
print('Initialized Brainfuck Interpreter')
while True:
    print('Enter file to interpret, \'q\' to quit, \'ls\' to list available bf scripts')
    cmd=input()
    if cmd=='q':
        break
    elif cmd=='ls':
        print('Scripts are files that end with \'.bf\'')
        print('--[Files]--')
        [print(x) for x in filter(lambda x: x.endswith('.bf'), os.listdir())]
    else:
        try:
            f=open(cmd, 'r')
            if os.path.isdir(cmd):
                raise IOError
            if not cmd.endswith('.bf'):
                raise IOError
            pis=input('Input for program\n')
            program_input=(c for c in pis)
            print('Reading script...')
            script=f.read()
            script=re.sub(r'[^\<\>\*\.\,\+\-\[\]]','',script)
            print('Parsing script...')
            for ch in script:
                print('Parsing char %c'%ch)
                if ch=='>':
                    program_queue+=[inc_ptr]
                elif ch=='<':
                    program_queue+=[dec_ptr]
                elif ch=='+':
                    program_queue+=[inc_val]
                elif ch=='-':
                    program_queue+=[dec_val]
                elif ch=='.':
                    program_queue+=[put_chr]
                elif ch==',':
                    program_queue+=[inp_chr]
                elif ch=='[':
                    program_queue+=[loop_start]
                elif ch==']':
                    program_queue+=[loop_end]
            program_end=len(program_queue)
            print('Interpreting script...')
            while program_curr<program_end:
                program_queue[program_curr]()
                print('On instruction %i... Function %s'%(program_curr, str(program_queue[program_curr])))
                program_curr+=1
            print('Output:\n',''.join(output))
            #reset globals
            array=[]
            ptr=0
            output=[]
            program_input=None
            program_end=None
            program_curr=0
            program_queue=[]
            labels=[]
            for i in range(4000):
                array.append(0)
        except IOError:
            print('File is not available for execution!')
    print('')
