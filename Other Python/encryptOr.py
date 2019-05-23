'''
v=42071388039438961922810830458981702074337661611550976
___=(True<<(True<<True)<<True)
____=(True<<(True<<(True<<True)<<True))

def decode(n):
    s = ''
    while n > 0:
        s += chr(n % 256)
        n >>= 8
'''

OH = "_=lambda __:_(__>>(True<<(True<<True)<<True))+(chr(__%(True<<(True<<(True<<True)<<True)))if __%(True<<(True<<(True<<True)<<True)) else'') if __>0 else''"
OH2 = "_=lambda __:_(__>>8)+(chr(__%256)if __%256 else'') if __>0 else''"
OH3 = "(lambda a:lambda v:a(a,v))(lambda self,__:self(self,__>>8)+(chr(__%256)if __%256 else'') if __>0 else'')"
FL='exec(_(%d))'

def encode(s):
    n = 0
    for ch in s:
        n += ord(ch)
        n <<= 8
    return n

with open(input('file name: ')) as f:
    with open('encryptOr_out.txt', 'w') as fw:
        fw.write('%s\n' % OH3)
        fw.write('%s\n' % (FL % (encode(''.join(f.readlines())))))

_=lambda __:_(__>>(True<<(True<<True)<<True))+(chr(__%(True<<(True<<(True<<True)<<True)))if __%(True<<(True<<(True<<True)<<True)) else'') if __>0 else''
