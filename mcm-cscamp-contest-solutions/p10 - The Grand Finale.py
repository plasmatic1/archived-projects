#import sys
#from StringIO import StringIO

def test_in():
    return '''
4 10 5
10
15
17
20
2 16
1 25
3 35
0 38
2 0
'''

#sys.stdin = StringIO(test_in())

def gi(): # Gets int
    return int(raw_input())

def split_ln(): # Gets line and splits it by spaces
    return raw_input().split(' ')

def gi_split(): # Gets line and splits it by spaces and casts all of them to ints
    return map(int, split_ln())

n, l, m = gi_split()

eles = [] # elephants

for i in range(n):
    eles.append(gi())

for _ in range(m):
    i, y = gi_split()
    eles[i - 1] = y
    phants = sorted(eles)
    #print('Test phants\': %s'%phants.__str__())
    cnt = 0
    begin_ind = -1
    for ele in phants: # bad meme
        if begin_ind == -1:
            begin_ind = ele
            cnt += 1
            #print('set begin_ind to %d'%begin_ind)
        else:
            #print('camera counting!: ele: %d, begin: %d'%(ele, begin_ind))
            if ele - begin_ind > l:
                #print('camera is over!')
                #begin_ind = -1

                begin_ind = ele
                cnt += 1
                #print('set begin_ind to %d'%begin_ind)
    if begin_ind == -1:
        cnt += 1
    print(cnt)

'''
4 10 5
10
15
17
20
2 16
1 25
3 35
0 38
2 0
'''
