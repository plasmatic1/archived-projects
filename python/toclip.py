import os

def toClip(string):
    os.system('echo %s | clip' % string)
