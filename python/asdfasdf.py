def roger(v):
    if v < 10:
        print(v)
        return True

    strv = list(map(int, str(v)))
    work = True
    for i in range(1, len(strv)):
        if abs(strv[i] - strv[i - 1]) < 2:
            work = False
            break

    if work:
        print(v)

    return work

i = 110
rogerc = 0
while i <= 999:
    rogerc += roger(i)
    i += 1

print('rogerc is %d' % rogerc)
