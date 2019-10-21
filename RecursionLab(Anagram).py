import timeit

words = set(open("words_alpha.txt").read().split())
scrambledWords = set('')


def anagram():

    print('Enter a word or empty string to finish: ')
    word = input('Word: ')

    if(word != ''):  # if statement used to make sure that the program only runs when the user inputs in a word instead of an empty string

        start = timeit.default_timer()  # starts the timer

        anagram2(word, '')
        wordsIntersection = words.intersection(scrambledWords)  # creates a new set wordsIntersection containing all the anagrams based off the permutations of the inputed word
        print('The word ' + word + ' has the following ' + str(len(wordsIntersection)) + ' anagrams:')

        if(len(wordsIntersection) != 0):  # if statement used to make sure that the print statemnt is used only when there is an anagram for the inputed word
            wordsList = list(wordsIntersection)  # changes set to list
            wordsList.sort()
            for i in range(len(wordsList)):
                print(wordsList[i])

        stop = timeit.default_timer()  # stops the timer

        print('It took ' + str(stop - start) + ' seconds to find the anagrams' + '\n')
        wordsIntersection.clear()  # clears the set
        scrambledWords.clear()  # clears the set
        return 1
    else:
        print('Bye, thanks for using this program')
        return 0


def anagram2(baseWord, scrambled):
    if len(baseWord) == 0:  # Base Case
        scrambledWords.add(scrambled)
    else:  # Recursive Case

        for i in range(len(baseWord)):
            # Takes the letter at index i to be scrambled in order to find all permutations
            scrambledLetter = baseWord[i]

            # Removes the letter at index i form the remainingLetters of the baseWord
            remainingLetters = baseWord[:i] + baseWord[i + 1:]

            # Recursive Call for the function which takes the letter at index i and finds all possible permutations
            anagram2(remainingLetters, scrambled + scrambledLetter)


s = 1
while(s != 0):  # while loop used to run the function until the user inputs an empty string
    s = anagram()
