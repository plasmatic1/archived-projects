import sys

def gi(): # Gets int
    return int(input())

def split_ln(): # Gets line and splits it by spaces
    return raw_input().split(' ')

def gi_split(): # Gets line and splits it by spaces and casts all of them to ints
    return map(int, split_ln())

def gcd(i, j):
    max_ = max(i, j)
    min_ = min(i, j)

    #print('entering gcd of (%d %d)'%(max_, min_))

    if max_ % min_ == 0:
        return min_
    else:
        return gcd(max_ % min_, min_)

sys.setrecursionlimit(1500000)
t = gi()

for i in range(t):
    n, d = gi_split()
    cnt = 0
    for j in range(1, n + 1): # Order does not change answer (eliminating duplicate values)
        for l in range(j, n + 1):
            if gcd(j, l) == d:
                #print('Pair (%d, %d) got it!'%(j, l))
                cnt += 1
    print(cnt)

'''
1
12 3
'''
