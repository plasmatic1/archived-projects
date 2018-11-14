import collections

def gi(): # Gets int
    return int(input())

def split_ln(): # Gets line and splits it by spaces
    return raw_input().split(' ')

def gi_split(): # Gets line and splits it by spaces and casts all of them to ints
    return map(int, split_ln())

w = gi()
words = collections.OrderedDict()

for i in range(w):
    p = input()
    words[p] = 0

n = gi()
for i in range(n):
    line = input()
    for word in words.keys():
        words[word] += line.count(word)

for word, count in words.items():
    print(count)

'''
3
"who"
"shawty"
"hawt"
2
"Get-it-shawty-Get-it-shawty"
"Woah-W-W-Woah-Shawtyyyyy"
'''
