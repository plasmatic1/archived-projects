MAXR = 30
class rep:
    def add(self, arg):
        if arg in self.dict:
            self.dict[arg] += 1
        else:
            self.dict[arg] = 1
    
    def __init__(self, *args):
        self.dict = {}

        for arg in args:
            self.add(arg)

    def __iadd__(self, other):
        for k, v in other.dict.items():
            if k in self.dict:
                self.dict[k] += v
            else:
                self.dict[k] = v

        return self

    def __str__(self):
        return ','.join(['%s*%s' % (k, v) for k, v in self.dict.items()]).ljust(MAXR)

    def __repr__(self):
        return self.__str__()

l = [rep('a'), rep('b'), rep('c'), rep('d'), rep('e'), rep('f')]
sz = len(l)

def psumarr():
    for i in range(1, sz):
        l[i] += l[i - 1]

print('Arr:', l)
for i in range(100):
    psumarr()
    print('Arr after %d ops:' % (i + 1), l)
