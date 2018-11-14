from toclip import *
import random

while True:
    text=input('Input text: ')
    maxspace=int(input('Input max number of spaces: '))
    newtext=[]
    for char in text:
        newtext.append(char)
        newtext.append(' ' * random.randint(0, maxspace))
    newtextstr=''.join(newtext)
    print('Text is %s' % newtextstr)
    print('Copying to clipboard')
    toClip(newtextstr)
