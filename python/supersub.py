SUPER='⁰¹²³⁴⁵⁶⁷⁸⁹'
SUB='₁₂₃₄₅₆₇₈₉₀'

def super(num):
    sv=str(num)
    fs=''
    for c in sv:
        fs+=SUPER[int(c)]
    return fs
def sub(num):
    sv=str(num)
    fs=''
    for c in sv:
        fs+=SUB[int(c)]
    return fs