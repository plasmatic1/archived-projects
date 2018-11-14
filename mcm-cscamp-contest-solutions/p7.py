def gi(): # Gets int
    return int(input())

def split_ln(): # Gets line and splits it by spaces
    return raw_input().split(' ')

def gi_split(): # Gets line and splits it by spaces and casts all of them to ints
    return map(int, split_ln())

n = gi()

for i in range(n):
    v = gi()
    print(v // 2 + 1)

'''
2
2
5
'''
