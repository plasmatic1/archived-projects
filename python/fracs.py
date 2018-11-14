from math import gcd

def take_input(prompt):
    return map(lambda fs: tuple((int(x) for x in fs.split('/'))),
              input(prompt).split(' '))

def addfrac():
    # Input
    fracs=take_input('Fracs: ')
    beg=next(fracs)

    # Scales the fraction according to the new denominator
    def scalen(frac, newdeno):
        return frac[0] * newdeno / frac[1]

    # Adds fractions together
    for n in fracs:
        lcm = beg[1] * n[1] / gcd(beg[1], n[1])
        num = scalen(beg, lcm) + scalen(n, lcm)

        beg = (num, lcm)

    # Simplify end result
    beg_gcd = gcd(int(beg[0]), int(beg[1]))
    beg = tuple([int(v / beg_gcd) for v in beg])

    # Print
    print('%d/%d' % beg)

def multfrac():
    # Input
    fracs=take_input('Fracs: ')
    beg=next(fracs)

    # Multiply fractions together
    for n in fracs:
        beg = (beg[0] * n[0], beg[1] * n[1])

    # Simplify end result
    beg_gcd = gcd(int(beg[0]), int(beg[1]))
    beg = tuple([int(v / beg_gcd) for v in beg])

    # Print
    print('%d/%d' % beg)

TYPES = {'add': addfrac, 'mult': multfrac}
TYPE_PROMPT = 'Type of operation? (%s): ' % ', '.join(TYPES.keys())

while 1:
    TYPES[input(TYPE_PROMPT)]()
