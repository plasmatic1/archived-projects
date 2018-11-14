def gi(): # Gets int
    return int(input())

def spl_gl(): # Gets line and splits it by spaces
    return input().split(' ')

def gi_spl(): # Gets line and splits it by spaces and casts all of them to ints
    return map(int, spl_line())

n = gi()
CUR = (100, 50, 10, 5, 2, 1)

for i in range(n):
    amt = gi()
    cnt = 0
    for mon in CUR:
        cnt += amt / mon
        amt %= mon
    print(cnt)

'''
3
1200
500
242
'''
