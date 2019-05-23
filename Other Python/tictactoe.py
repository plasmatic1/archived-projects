class tcrds():
    def __init__(self, x, y, xo = 'n'):
        self.x = x
        self.y = y
        self.xo = xo

    def isonCoords(self, x, y):
        if(self.x == x and self.y == y):
            return true

        return false

    def copy(self):
        return tcrds(self, self.x, self.y, self.xo)

    def isonCoords(self, coords):
        return isonCoords(self, coords.x, coords.y)

    def setX(self):
        self.xo = 'x'

    def setY(self):
        self.xo = 'y'

    def clear(self):
        self.xo = 'n'

    def isSep(self, exo):
        return xo == exo

    def setas(self, x, y, xo='n'):
        self.x = x
        self.y = y
        self.xo = xo

    def str_self(self):
        if(self.xo == 'x'):
            return 'X'
        elif(self.xo == 'o'):
            return 'O'
        elif(self.xo == 'n'):
            return ' '

board = {'0,0':tcrds(0, 0), '1,0':tcrds(1, 0), '2,0':tcrds(2, 0),
         '0,1':tcrds(0, 1), '1,1':tcrds(1, 1), '2,1':tcrds(2, 1),
         '0,2':tcrds(0, 2), '1,2':tcrds(1, 2), '2,2':tcrds(2, 2),
         }

def print_board():
    print(board['0,0'].str_self(), board['1,0'].str_self(), board['2,0'].str_self(), sep=' | ')
    print('---------')
    print(board['0,1'].str_self(), board['1,1'].str_self(), board['2,1'].str_self(), sep=' | ')
    print('---------')
    print(board['0,2'].str_self(), board['1,2'].str_self(), board['2,2'].str_self(), sep=' | ')

def askforinput():
    print('Welcome to TicTacToe!')
    print('In this game, you type in where you want to put the stuff in')
    print('Like This: x,y')
    print("Now let's get started!")

    inp = input("Input the square: ")
    

print_board()
