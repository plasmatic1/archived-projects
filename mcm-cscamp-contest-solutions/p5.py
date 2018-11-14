def gi(): # Gets int
    return int(input())

def split_ln(): # Gets line and splits it by spaces
    return raw_input().split(' ')

def gi_split(): # Gets line and splits it by spaces and casts all of them to ints
    return map(int, split_ln())

t = gi()

for i in range(t):
    n, k = gi_split()
    print(n % k)

'''
2
5 2
11 3
'''
