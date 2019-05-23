n=int(input())

lasttn=0
lastn=1
cn=1

while n>1:
    cn=lasttn+lastn
    lasttn=lastn
    lastn=cn
    n-=1

print(cn%1000000007)
