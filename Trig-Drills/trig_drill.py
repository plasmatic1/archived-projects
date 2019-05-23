from tkinter import Tk, Label, Frame, Entry, Button, StringVar, IntVar
from random import randint, choice, sample
from math import sin, cos, tan, radians, sqrt
from threading import Thread
from time import sleep

COLS = 6  # Column count
ROWS = 5  # Row count
TIME = 120  # Time in seconds
DIMENSIONS = '800x420'  # Screen Size
FINAL_DIMENSIONS = '800x420'  # Screen Size at end of game

MIN_DIFF = 0.0001  # Min Percision (You probably shouldn't change this...)

GREEN = '#aaffaa'  # Green
RED = '#ffaaaa'  # Red

TITLE_FONT = ('Courier', 30)  # Title Font
FONT = ('Arial', 15)  # Regular Font (for labels)
SMALL_FONT = ('Arial', 10)  # Small Font (for answer inputs)

'''
Configuration Constants stop at this point.  Only edit anything below if you know what you are doing.
No, seriously.  Don't.
'''

def safe(errorType, default):
    def safe_deco(func):
        def wrapper(val):
            try:
                return func(val)
            except errorType:
                return default
        return wrapper
    return safe_deco

@safe(ArithmeticError, 'undefined')
def sec(x): 
    ans = 1 / cos(x)
    if abs(ans) > 1e10:
        return 'undefined'
    return ans

@safe(ArithmeticError, 'undefined')
def csc(x): 
    ans = 1 / sin(x)
    if abs(ans) > 1e10:
        return 'undefined'
    return ans

@safe(ArithmeticError, 'undefined')
def cot(x): 
    ans = 1 / tan(x)
    if abs(ans) > 1e10:
        return 'undefined'
    return ans

# Eval that returns None if fails
@safe(Exception, 'undefined')
def safeEval(code):
    if not code:
        return None
    return eval(code)

FUNC_NAMES_ = ['sin', 'cos', 'tan', 'sec', 'csc', 'cot']
FUNCS_ = [sin, cos, tan, sec, csc, cot]
FUNCS = list(zip(FUNC_NAMES_, FUNCS_))

# Euclidian GCD
def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)

# Display as degrees (why is this necessary?)
def degreesRepr(degs):
    return str(degs) + ' deg'

# Display as radians
def radiansRepr(degs):
    gcdv = gcd(degs, 180)
    num = degs // gcdv
    denom = 180 // gcdv

    if num == 0:
        return '0 rad'
    elif num == 1:
        if denom == 1:
            return 'pi rad'
        return 'pi / %d rad' % denom
    return '%dpi / %d rad' % (num, denom)

STR_FUNCS = [degreesRepr, radiansRepr]

# Random degree from 0 to 360
quads = [0, 90, 180, 270]
offs = [0, 30, 45, 60]
def randDegrees(count):
    degs = set()
    while len(degs) < count:
        degs.add(choice(quads) + choice(offs))
    return list(degs)

# Get functions
def randFuncs(count):
    return sample(FUNCS, k=count)

'''
Main starts at this point
'''

funcs = randFuncs(ROWS)
angles = randDegrees(COLS)

root = Tk()
root.title('Trig Drill')
root.geometry(DIMENSIONS)

title = Label(root, text='Trig Drill', font=TITLE_FONT)
title.pack()

# Question Labels
T_PADDING = {'padx': 5, 'pady': 5}
T_LABEL_OPTIONS = {'borderwidth': 2, 'relief': 'ridge'}
T_LABEL_OPTIONS.update(T_PADDING)

table = Frame(root)

# Answer inputs
answerVars = [[None] * COLS for _ in range(ROWS)]
answerEntries = [[None] * COLS for _ in range(ROWS)]
for i in range(ROWS):
    for j in range(COLS):
        var = StringVar(table)
        entry = Entry(table, font=SMALL_FONT, width=9, textvariable=var)
        entry.grid(row=i + 1, column=j + 1)

        answerEntries[i][j] = entry
        answerVars[i][j] = var

# Verdicts
verdictVars = [None] * COLS
verdictLabels = [None] * COLS
for i in range(COLS):
    var = StringVar(table)
    label = Label(table, text='?', font=FONT, textvariable=var)
    label.grid(row=ROWS + 1, column=i + 1, **T_PADDING)

    verdictVars[i] = var
    verdictLabels[i] = label

finalVerdictVar = StringVar(table)
finalVerdict = Label(table, text='?', font=FONT, textvariable=finalVerdictVar, **T_PADDING)
finalVerdict.grid(row=ROWS + 1, column=COLS + 1)

# Game Functions (Hooray for Atomic variables!)
timeLeft = TIME + 1
started = False
finished = False

def pulse():
    global timeVar, timeLeft

    while timeLeft > 0 and not finished:
        timeLeft -= 1
        timeVar.set(str(timeLeft))
        sleep(1)

    # Game Finished
    correctCount = 0
    questionCount = COLS * ROWS

    # Checking each column
    for i in range(COLS):
        curCorrect = 0
        for j in range(ROWS):
            # If anyone is wondering why is there such a weird and colvoluted answer check.  The reason is 
            # because of floating-point arithmetic screwing everything up.  If you don't understand what I
            # mean by this, just try entering math.tan(math.radians(90)) ans seeing what comes out the
            # other side.  It's not pretty.

            userAns = answerVars[j][i].get()
            isCorrect = False

            if userAns == 'undefined' and funcs[j][0] == 'tan' and \
                (angles[i] == 90 or angles[i] == 270):
                    isCorrect = True
                    continue

            userAns = safeEval(userAns)
            actual = funcs[j][1](radians(angles[i]))

            print('angle: %d, fun: %s, userAns: %s, actual: %s' % \
                (angles[i], funcs[j][0], str(userAns), str(actual)))

            if (type(userAns) == float or type(userAns) == int) and actual != 'undefined':
                if abs(userAns - actual) <= MIN_DIFF:
                    isCorrect = True
            elif userAns == 'undefined' and actual == 'undefined':
                isCorrect = True
                
            curCorrect += isCorrect
            answerEntries[j][i].configure(bg=GREEN if isCorrect else RED)
        
        if curCorrect == ROWS:
            verdictLabels[i].configure(bg=GREEN)
            verdictVars[i].set('%d/%d AC' % (ROWS, ROWS))
        else:
            verdictLabels[i].configure(bg=RED)
            verdictVars[i].set('%d/%d WA' % (curCorrect, ROWS))
        
        correctCount += curCorrect
    
    # Final Verdict
    if correctCount == questionCount:
        finalVerdictVar.set('%d/%d AC' % (questionCount, questionCount))
        finalVerdict.configure(bg=GREEN)
    else: 
        finalVerdictVar.set('%d/%d WA' % (correctCount, questionCount))
        finalVerdict.configure(bg=RED)
    
    root.geometry(FINAL_DIMENSIONS)

def finishGame():
    global started, finished
    if started:
        finished = True

timerThread = Thread(target=pulse)

def beginGame():
    global started, timerThread

    if not started:
        for i in range(COLS):
            label = Label(table, text=STR_FUNCS[randint(0, 1)](angles[i]), font=FONT, **T_LABEL_OPTIONS)
            label.grid(row=0, column=i + 1, padx=3, pady=3)

        for i in range(ROWS):
            label = Label(table, text=funcs[i][0], font=FONT, **T_LABEL_OPTIONS)
            label.grid(row=i + 1, column=0, padx=3, pady=3)

        timerThread.start()
        started = True

# Start Button and Finish Button
beginButton = Button(table, text='Start!', font=FONT, command=beginGame, **T_PADDING)
beginButton.grid(row=ROWS + 2, column=0)

finishButton = Button(table, text='Finish!', font=FONT, command=finishGame, **T_PADDING)
finishButton.grid(row=ROWS + 2, column=COLS + 1)

# Timer
timeText_ = Label(table, text='Time:', font=FONT, **T_PADDING)
timeText_.grid(row=ROWS + 2, column=1)

timeVar = StringVar(table)
timeText = Label(table, text='Time:', font=FONT, textvariable=timeVar, **T_PADDING)
timeText.grid(row=ROWS + 2, column=2)

table.pack()
root.mainloop()
