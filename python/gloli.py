import random
 
print("Welcome to StudyTerms, a new program where you can study terms and definitions for any of your classes.\nNO, WE ARE NOT QUIZLET. We're worse because and only because Gloria created this :)\nSeriously. If you want to actually study, go to quizlet.com. I highly recommend it.")
 
print("After you input the terms and definitions, you'll be able to study through:\n1) Flashcards\n2) Multiple choice questions\n3) You writing the definition")
 
print()
 
print("First things first, enter your terms and definitions!\nPlease note, this program is extremely sensitive to lowercases and uppercases, so we advise you to just put everything in capitals or lowercases in order not to be confused.")
 
print("If you ever input your term or definition wrong, please type 'I WANT TO RE-INPUT', remembering to type in all capitals.\nOr, if you would like to finish entering your terms and definitions, please enter 'I AM DONE ENTERING'.\nAlso, you must have a minimum of 4 terms!")
 
done=False #keep entering terms going
terms=[] #make a list
definitions=[] #make a list
count=-1 #counts # of terms, list starts at 0
 
print("Please enter your first term and press enter.")
 
while done!=True:
        count=count+1 #count terms
        term=input()
        while term in terms or term=="" or term=="I WANT TO RE-INPUT" or term=="I AM DONE ENTERING":
                if term in terms: #no repeating terms
                        print("Sorry, you have already made that a term.\nPlease re-enter the term.")
                        term=input()
                elif term=="": #no empty terms
                        print("Sorry, you cannot have no term.\nPlease re-enter the term.")
                        term=input()
                elif term=="I WANT TO RE-INPUT":
                        if count==0:
                                print("Sorry, you did not enter anything that you may want to re-input.\nPlease enter your first term and press enter.")
                                term=input()
                        else:
                                print("Okay! The program will erase the definition you just inputted.\nPlease input the definition again.")
                                definitions.pop()
                                definition=input()
                                while definition=="I AM DONE ENTERING" or definition=="" or definition=="I WANT TO RE-INPUT":
                                        if definition=="I AM DONE ENTERING":
                                                print("Sorry, but you cannot have no definition for your term.\nPlease input a definition for your term.")
                                                definition=input()
                                        elif definition=="I WANT TO RE-INPUT":
                                                print("You currently are trying to re-input your definition.\nPlease input your definition.")
                                                definition=input()
                                        else: #when definition==""
                                                print("Sorry, you cannot have no definition.\nPlease input a definition.")
                                                definition=input()
                                definitions.append(definition)
                                print("Now enter your next term.")
                                term=input()
                               
                else: #when term=="I AM DONE ENTERING"
                        if 0<=count<=3:
                                print("Sorry, the minimum number of terms is 4.\nPlease input your next term.")
                                term=input()
                        else:
                                count=count-1
                                done=True
                                term="finished"
       
        if done == False:
                terms.append(term)
                print("Okay! Please input your definition.")
                definition=input()
                while definition=="I AM DONE ENTERING" or definition=="I WANT TO RE-INPUT" or definition=="":
                        if definition=="I AM DONE ENTERING":
                                print("Sorry, but you cannot have no definition for your term.\nPlease input a definition for your term.")
                                definition=input()
                        elif definition=="I WANT TO RE-INPUT":
                                print("Okay! The program will erase the term you just inputted.\nPlease input the term again.")
                                terms.pop()
                                term=input()
                                while term in terms or term=="" or term=="I AM DONE ENTERING" or term=="I WANT TO RE-INPUT":
                                        if term in terms: #no repeating terms
                                                print("Sorry, you have already made that a term.\nPlease re-enter the term.")
                                                term=input()
                                        elif term=="": #no empty terms
                                                print("Sorry, you cannot have no term.\nPlease re-enter the term.")
                                                term=input()
                                        elif term=="I WANT TO RE-INPUT":
                                                print("You already are re-inputting the term.\nPlease input a definition for your term.")
                                                term=input()
                                        else: #when term=="I AM DONE ENTERING"
                                                if 0<=count<=3:
                                                        print("Sorry, the minimum number of terms is 4.\nPlease input your next term.")
                                                        term=input()
                                                else:
                                                        done=True
                                                        count = count-1
                                                        term="finished"
                                if done!=True:
                                        terms.append(term)
                                        print("Now enter your definition.")
                                        definition=input()
                               
                        else: #when definitions==""
                                print("Sorry, you cannot have no definition.\nPlease input a definition.")
                                definition=input()
                definitions.append(definition)
                print("Thanks! Now input your next term.")
 
print()
 
#study
print("Great! Now, let's study!\nHow would you like to study?\nThere are 3 ways you can use study terms: flashcards, multiple choice questions, and written questions (given the term or definition, write the respective term or definition).\nYou can also get randomized tests, which is a combination of multiple choice and writing questions!")
 
finish=False #continues studying
while finish!=True:
        print("If you would like flashcards, input 'Flashcards'.\nIf you would like multiple choice, input 'Multiple Choice'.\nIf you would like writing questions, input 'Writing'.\nIf you want to stop studying, input 'Stop' to stop the program.\nDon't forget to capitalize each word and press enter!")
        study=input()
        while study!='Flashcards' and study!='Multiple Choice' and study!='Writing' and study!='Stop':
                print("Sorry, that was not valid answer. You can only input 'Flashcards', 'Multiple Choice', 'Writing', or 'Stop'. Make sure the first letter is capitalised, and re-enter your choice, making sure to press enter after you input your choice.")
                study=input()
        if study=="Flashcards":
                print("Great! Let's study through flashcards.") #terms first, in orderr; give more options if time
                for i in range (0,count):
                        print()
                        print("Term:\n"+str(terms[i]))
                        print()
                        print("Please press enter to see the definition.")
                        flip=input() #only function is to see definition
                        print("Definition:\n"+str(definitions[i]))
                        print()
                        print("When you are ready, press enter.")
                        ready=input() #only function is to continue
                print("That was all the terms and definitions! What would you like to do now?")
               
        elif study=="Multiple Choice":
                print("Great! Let's study through some multiple choice questions.")
                print("How many questions would you like to do? There is a minimum of 4 questions, maximum of 20.")
                stop=True
                while stop!=False:
                        try:
                                questions=int(input())
                                if questions<4 or questions>20:
                                        print("Sorry, that is not a valid number of questions\nThere is a minimum of 4 questions and a maximum of 20.")
                                else:
                                        stop=False
                        except:
                                print("Sorry, we were expecting a number for the number of questions.")
                               
                               
                for i in range (1, questions+1):
                        questionType=random.randint(1,2)
                        if questionType==1:
                                correctDef=random.randint(0,count-1)
                                print("Which of the following is the correct definition for "+str(terms[correctDef])+"?")
                                correct=random.randint(0,3)
                                print(correct)
                                answers=[]
                                for i in range (correct):
                                        incorrect=random.randint(0,count-1)
                                        while definitions[incorrect] in answers or incorrect==correctDef:
                                                incorrect=random.randint(0,count-1)
                                        answers.append(definitions[incorrect])
                                        print(definitions[incorrect])
       
                                print(definitions[correctDef])
                                answers.append(definitions[correctDef])
                               
                                for i in range (4-correct):
                                        incorrect=random.randint(0,count-1)
                                        while incorrect==correctDef or definitions[incorrect] in answers:
                                                incorrect=random.randint(0,count-1)
                                        answers.append(definitions[incorrect])
                                        print(definitions[incorrect])
                       
                                print("Type out the answer you think is correct.")
                                answer=input()
                                while answer not in answers:
                                        print("Sorry, that was not one of the answers shown. Please enter the answer you think is correct.")
                                        answer=input()
                                if answer==definitions[correctDef]:
                                        print("Good job! You got it right.")
                                else:
                                        print("Oops, that was not the correct answer. The answer was", definitions[correctDef])
                                print()
                                               
                        else:
                                correctTerm=random.randint(0,count-1)
                                print("Which of the following is the correct term for "+str(definitions[correctTerm])+"?")
                                correct=random.randint(0,3)
                                print(correct)
                                answers=[]
                                for i in range (correct):
                                        incorrect=random.randint(0,count-1)
                                        while incorrect==correctTerm or terms[incorrect] in answers:
                                                incorrect=random.randint(0,count-1)
                                        answers.append(terms[incorrect])
                                        print(terms[incorrect])      
                                               
                                print(terms[correctTerm])
                                answers.append(terms[correctTerm])
                               
                                for i in range (4-correct):
                                        incorrect=random.randint(0,count-1)
                                        while incorrect==correctTerm or terms[incorrect] in answers:
                                                incorrect=random.randint(0,count-1)
                                        answers.append(terms[incorrect])
                                        print(terms[incorrect])  
                               
                                print("Type out the answer you think is correct.")
                                answer=input()
                                while answer not in answers:
                                        print("Sorry, that was not one of the answers shown. Please enter the answer you think is correct.")
                                        answer=input()
                                if answer==terms[correctTerm]:
                                        print("Good job! You got it right.")
                                else:
                                        print("Oops, that was not the correct answer. The answer was", terms[correctTerm])
                                print()
        elif study=="Writing":
                print("Great! Let's study through some writing questions.")
        else:#when study=="Stop"
                finish=True
 
print("Thank you for using StudyTerms! Good luck on your test. :)")