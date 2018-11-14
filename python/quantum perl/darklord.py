import re

# File Reading

with open('seps.txt') as f:
    global w
    w=f.readlines()

with open('begandend.txt') as f:
    global beg, end
    beg=f.readline()[:-1]
    end=f.readline()

with open('quantum.txt') as f:
    global n
    n=f.readlines()[0]
specs=set(map(lambda x: x[1], re.findall('\'.\'|"\'"', n)))
specs.add('@')
specs.add('#')
specs.add('%')

# Var Definitions

low='abcdefghijklmnopqrstuvwxyz'
up='ABCDEFGHIJKLMNOPQRSTUVWXYZ'
lets=low+up

s='Hail the Dark Lord Quantum!'
os="If a problem can't be solved with *regex*, it's a bad problem."

l=w[0].split('sep')

dic={}
for k, v in zip(os, l):
    dic[k] = v

mis={x for x in lets if x not in dic}
mfd={x for x in s if x in mis}

# Functions

def constro(string):
    return '.'.join(['(\'%s\'%s\'%s\')'%(tup[0],tup[2],tup[1]) for tup in
                     [possibles[ord(c)] for c in string]])

def constrof(string):
    return beg + constro(string) + end

def pc(string):
    print(constrof(string))

# Procs

possibles={}
zspecs=list(zip(specs, list(map(ord, specs))))
for i,j in zspecs:
    for k,l in zspecs:
    	possibles[j|l]=(i,k,'|')
    	possibles[j^l]=(i,k,'^')
