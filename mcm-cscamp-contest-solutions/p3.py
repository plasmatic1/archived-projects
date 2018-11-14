def gi(): # Gets int
    return int(input())

def split_ln(): # Gets line and splits it by spaces
    return raw_input().split(' ')

def gi_split(): # Gets line and splits it by spaces and casts all of them to ints
    return map(int, split_ln())

n, m = gi_split()

cnt = n # num of ppl still in game
ppl = [True] * n # original people
leave = [] # order of ppl leaving

l = len(ppl)
i = -1
while cnt > 0:
    person = -1
    true_cnt = 0

    while true_cnt < m:
        i = (i + 1) % n
        if ppl[i]:
            true_cnt += 1

    person = i
    ppl[i] = False
    leave.append(person)
    cnt -= 1

print(' '.join(map(str, leave)))

'''
7 2

'''
