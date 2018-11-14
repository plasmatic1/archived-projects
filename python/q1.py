#question 1
import sys
sys.setrecursionlimit(1000000)
MAX=100
ans=[-1]*MAX
def f(x):
    if ans[x] != -1:
        return ans[x]
    if x < 3:
        ans[x] = 1
    else:
        ans[x] = 2 * f(x - 1) + 2 * f(x - 2) + 2 * f(x - 3)
    return ans[x]
f(MAX-1)
print(ans)
for i in range(MAX-1):
    print('Ratio of %d, %d : %f' % (i, i + 1, float(ans[i+1])/float(ans[i])))
