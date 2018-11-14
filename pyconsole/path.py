import os
import pause

desc = 'Prints PATH entries in a readable format (Windows only)'
usage = 'path'

def exec(args):
    print('PATH entries:')
    path = [print(p) for p in os.getenv('path').split(';')]
    print('%d total path entries found' % (len(path)))
    pause.pause()
