import operator as op
import functools as ft
def ncr(n, r):
    r = min(r, n-r)
    numer = ft.reduce(op.mul, range(n, n-r, -1), 1)
    denom = ft.reduce(op.mul, range(1, r+1), 1)
    return numer // denom
