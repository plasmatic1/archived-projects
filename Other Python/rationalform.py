from supersub import *
class Rational:
    def __init__(self, nml=1, root=1, pwr=2):
        self.nml=nml
        self.root=root
        self.pwr=pwr
    def simplify(self):
        trt=self.root    
        for i in range(2,self.root):
            poff=0
            while trt%i==0:
                poff+=1
                trt/=i
            if poff>=self.pwr:
                self.nml*=i**(int(poff/self.pwr))
                self.root/=i**poff
            if trt==1:
               break
        return self
    def rootform(self):
        self.root*=self.nml**self.pwr
        self.nml=1
        return self
    def rationalform(self):
        self.simplify()
        return self
    def __repr__(self):
        rstr=''
        if self.nml!=1:
            rstr+=str(self.nml)
        if self.pwr!=2:
            rstr+=super(self.pwr)
        if self.root>1:
            rstr+='√%d'%self.root
        return rstr
    def __str__(self):
        return self.__repr__()
    def __mul__(self, other):
        if self.pwr==other.pwr:
            return Rational(self.nml*other.nml,self.root*other.root,self.pwr).simplify()
        raise ValueError('root powers are different!')
    def __div__(self, other):
        if self.pwr==other.pwr:
            return Rational(self.nml/other.nml,self.root/other.root,self.pwr).simplify()
        raise ValueError('root powers are different!')

#TESTS

test=Rational(1,12,2)
test2=Rational(3,3,2)
test3=Rational(root=25)

print(test3.simplify())

print(test*test2)

print(test)
test.simplify()
print(test)
