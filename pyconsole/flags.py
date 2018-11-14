desc = 'Test command that prints out the argument list as flags'
usage = 'flags [args]'
flags = True

def exec(args):
    for (k, v) in args.items():
        print('%s : %s' % (k, v))
