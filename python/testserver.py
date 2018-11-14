import socket

HOSTNAME=socket.gethostname()
PORT=6969

s=socket.socket()
s.bind((HOSTNAME, PORT))
s.listen(5)

print('Started server at %s:%d'%(HOSTNAME,PORT))

while 1:
    con, addr=s.accept()
    message=con.recv(1024)
    print('Received message from address %s'%str(addr))
    print('Message: %s'%str(message))
    con.send(b'Thank you for using the testserrver application!')
    con.close()
    print('Connection closed with %s'%str(addr))
    print()
