from random import *
END_LEN=36
def roll():
    return (randint(1,6), randint(1,6))
def trial():
    tupleset=set()
    printmatrix=[[0]*6 for i in range(6)]
    total=0
    while not len(tupleset)>=END_LEN:
        tup=roll()
        tupleset.add(tup)
        printmatrix[tup[0]-1][tup[1]-1]+=1
        total+=1
    for a in printmatrix:
        print(a)
    print("Total roll count: %d"%total)
    return total
TRIAL_COUNT=10000
sum_=0.
for n in range(1,TRIAL_COUNT+1):
    print("Trial Number: %d"%n)
    sum_+=trial()
print("Average roll count per trial: %f"%(sum_/TRIAL_COUNT))
input()
