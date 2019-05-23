import math
primes=[2]
x=3
tsum=2
pnum=1
while 1:
    prime=True
    nsqrt=math.sqrt(x)
    for num in primes:
        if num > nsqrt:
            break
        if x % num == 0:
            prime=False
            break
    if prime:
        primes.append(x)
        tsum+=x
        pnum+=1
        print('New prime : %d, Total sum is : %d, Total primes : %d'%(x,tsum,pnum))
    if tsum > 400000000:
        break
    x+=1
print('Final prime is : %d, prime number %d'%(primes[-1],pnum))
