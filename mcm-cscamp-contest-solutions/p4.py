def gi(): # Gets int
    return int(input())

def split_ln(): # Gets line and splits it by spaces
    return raw_input().split(' ')

def gi_split(): # Gets line and splits it by spaces and casts all of them to ints
    return map(int, split_ln())

n, d = gi_split()
sticks = []

for i in range(n):
    sticks.append(gi())

sorted(sticks)

i = 0
n_ = n - 1
cnt = 0
while i < n_:
    if sticks[i + 1] - sticks[i] <= d:
        cnt += 1
        i += 2
    else:
        i += 1

print(cnt)

'''
5 2
1
3
3
9
4
'''
