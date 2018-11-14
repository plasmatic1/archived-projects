def gi():
    return int(input())

n = gi()

for i in range(n):
    c = gi()
    #print(c)
    line = input().split(' ')
    b = True
    for i, word in enumerate(line):
        #print("i %d, word %s"%(i, word))
        if i > 0 and (word == 'cookie' and line[i - 1] == 'cookie'):
            #print("Reached no at index %d (%s %s)"%(i, word, line[i - 1]))
            b = False
            break
        if i == c - 1 and word == 'cookie':
            #print('Reached no at index %d'%i)
            b = False
    if b:
        print('YES')
    else:
        print('NO')

'''
4
7
"cookie milk milk cookie milk cookie milk"
5
"cookie cookie milk milk milk"
4
"milk milk milk milk"
1
"cookie"
'''
