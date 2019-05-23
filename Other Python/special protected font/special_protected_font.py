import random

conc = 'this is a special protected font. you can\'t copy and paste it into discord. try it, i succeeded it.'
dic={}

with open('test.txt', encoding='utf-16') as f:
    global v, code
    os = f.readline()
    v = list(os)

def writeo(text);
    with open('out-enc.txt', 'w', encoding='utf-16') as f:
        f.write(text)

exit(0)

N = 10
MW = 10
Q = 40

l=[]
print(N)
for i in range(N):
    l.append(random.randint(0, MW))

print(' '.join(list(map(str, l))))

print(Q)

for i in range(Q):
    a = random.randint(0, N - 1)
    b = random.randint(a, N - 1)
    q = random.randint(1, MW)
    print('%d %d %d'%(a,b,q))
