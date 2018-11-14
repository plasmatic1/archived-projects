def gi(): # Gets int
    return int(input())

def split_ln(): # Gets line and splits it by spaces
    return raw_input().split(' ')

def gi_split(): # Gets line and splits it by spaces and casts all of them to ints
    return map(int, split_ln())

n = gi()
l = [[] for _ in range(n)]

def sum_of(list_, len_, i, j):
    if i < 0 or i >= len_:
        return list_[j]
    elif j < 0 or j >= len_:
        return list_[i]
    return list_[i] + list_[j]

if n > 0:
    l[0] = [1]
if n > 1:
    l[1] = [1, 1]

for i in range(2, n):
    #print(' row %d, prev row %s'%(i, l[i - 1].__str__()))
    for j in range(i + 1):
        l[i].append(sum_of(l[i - 1], i, j - 1, j))
    #print('curr row %s, nx row %s'%(l[i].__str__(), l[i + 1].__str__()))

for row in l:
    print(' '.join(map(str, row)))
'''
3

8
'''
