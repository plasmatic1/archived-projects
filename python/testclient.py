import socket

HOSTNAME=socket.gethostname()
PORT=6970

s=socket.socket()

s.connect((HOSTNAME,6969))
s.send(b'HI')
s.send(b'test2')
