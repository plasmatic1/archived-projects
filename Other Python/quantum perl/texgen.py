import re

specs = {"'", '"', '`', '+', '[', ',', '%', ')', '-', '(', '#', '!', '*', '{', '$', '&', '/', '.', '@'}
beg = '\'\'=~(\'(\'.\'?\'.\'{\'.(\'[\'^\'+\').(\'[\'^\')\').(\'`\'|\')\').(\'`\'|\'.\').(\'[\'^\'/\').\'"\'.'
end = '.\'"\'.\'}\'.\')\')'

possibles = {}
zspecs = list(zip(specs, list(map(ord, specs))))
for i, j in zspecs:
    for k, l in zspecs:
    	possibles[j | l] = (i, k, '|')
    	possibles[j ^ l] = (i, k, '^')

def quot(char):
    if char == '\'':
        return '"\'"'
    return '\'%s\'' % char

def constro(string):
    return beg + '.'.join(['(%s%s%s)' % (quot(tup[0]), tup[2], quot(tup[1]))
                     for tup in [possibles[ord(c)] for c in string]]) + end

inp = input()
conv = constro(inp)
print(conv)
print('wrote to out.txt')
with open('out.txt', 'w') as f:
    f.write(conv)
input()
