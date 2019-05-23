import math
import collections

v = int(input('Input value: '))
facts = collections.defaultdict(int)
maxv = int(math.sqrt(v)) + 2

# Factor

while not (v % 2):
    v /= 2
    facts[2] += 1

i = 3
while i <= maxv:
    while not (v % i):
        v /= i
        facts[i] += 1

    i += 2

# Checking for primes

if v > 1:
    facts[v] += 1

# Printing stuff out

print(' * '.join(map(lambda tup: '%d^%d' % tup, sorted(facts.items()))))
