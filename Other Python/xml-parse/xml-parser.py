from sys import argv, exit

# # # Init # # #

# Class
class objtree():
    def __init__(self):
        pass

    def __contains__(self, key):
        return key in self.__dict__

    def __setitem__(self, key, value):
        self.__dict__[key] = value

    def __getitem__(self, key):
        return self.__dict__[key]

    def __delitem__(self, key):
        del self.__dict__[key]

    def __str__(self):
        return str(self.__dict__)

# States

NONE = 0
KEY = 1
VALUE = 2

# Funs

def err(msg):
    print('Error while parsing: %s'%msg)
    exit()

def parse(xml_str):
    buf = ''
    state = NONE
    stack = []
    root = objtree()
    bufobj = root
    
    for char in xml_str:
        if char == '<':
            state = KEY
        
        if state == KEY:
            buf += char
            if char == '>':
                state = VALUE
                if buf[0] == '/':
                    kbuff = buf[1:]
                    if stack[-1] == kbuff:
                        if kbuff in bufobj:
                            err('Duplicate tag \'%s\''%kbuff)
                        bufobj[kbuff] = 
                        state = NONE
                    else:
                        err('Invalid end tag')
                else:
                    stack.append(buff)
                    buff = ''
                
        elif state == VALUE:
            if char == '<':
                
            buf += char 
                    
