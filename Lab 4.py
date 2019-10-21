import numpy as np
import timeit

class Words(object):
    def __init__(self, word, embedding):
        self.word = word
        self.embedding = embedding


class HashTable(object):
    # Builds a hash table of size 'size', initilizes items to -1 (which means empty)
    # Constructor
    def __init__(self, size, item=[], new_size=0, num_items=0):
        self.item = item
        self.num_items = num_items
        if(new_size > size):
            for i in range(new_size - size):
                self.item.append(-1)
        else:
            for i in range(size):
                self.item.append(-1)


def longestChain(H):
    neg_ones = []
    for i in range(len(H.item)):
        if(H.item[i] == -1):
            neg_ones.append(i)
    if len(neg_ones) == 0:
        return len(H.item)
    neg_ones = np.append(neg_ones, neg_ones[0] + len(H.item))
    chain_lens = neg_ones[1:] - neg_ones[:-1] - 1
    return np.max(chain_lens), int(np.average(chain_lens))


def readFile(FN):
    OpFN = open(FN)
    lineList = []
    line = [line.rstrip('\n') for line in OpFN]
    for i in line:
        if(i[0].isalnum()):
            lineList.append((i.split(" ")))
        else:
            line.remove(i)
    for i in range(len(lineList)):
        for j in range(1, len(lineList[i])):
            lineList[i][j] = float(lineList[i][j])
    return lineList


def stringLentoHash(lineList):
    hash = HashTable(101)
    for i in range(len(lineList)):
        a = Words(lineList[i][0], lineList[i][1:])
        if(hash.num_items >= (len(hash.item)//2)):
            hash = HashTable(len(hash.item), hash.item, (len(hash.item)*2)+1, hash.num_items)
        InsertStringLen(hash, a, len(lineList[i][0]))
    return hash


def asciitoHash(lineList):
    hash = HashTable(101)
    for i in range(len(lineList)):
        a = Words(lineList[i][0], lineList[i][1:])
        if(hash.num_items >= (len(hash.item)//2)):
            hash = HashTable(len(hash.item), hash.item, (len(hash.item)*2)+1, hash.num_items)
        InsertAscii(hash, a)
    return hash


def prodAsciitoHash(lineList):
    hash = HashTable(101)
    for i in range(len(lineList)):
        a = Words(lineList[i][0], lineList[i][1:])
        if(hash.num_items >= (len(hash.item)//2)):
            hash = HashTable(len(hash.item), hash.item, (len(hash.item)*2)+1, hash.num_items)
        InsertProdAscii(hash, a)
    return hash


def sumAsciitoHash(lineList):
    hash = HashTable(101)
    for i in range(len(lineList)):
        a = Words(lineList[i][0], lineList[i][1:])
        if(hash.num_items >= (len(hash.item)//2)):
            hash = HashTable(len(hash.item), hash.item, (len(hash.item)*2)+1, hash.num_items)
        InsertSumAscii(hash, a)
    return hash


def recursivetoHash(lineList):
    hash = HashTable(101)
    for i in range(len(lineList)):
        a = Words(lineList[i][0], lineList[i][1:])
        if(hash.num_items >= (len(hash.item)//2)):
            hash = HashTable(len(hash.item), hash.item, (len(hash.item)*2)+1, hash.num_items)
        InsertRecursive(hash, a)
    return hash


def fullProdAsciitoHash(lineList):
    hash = HashTable(101)
    for i in range(len(lineList)):
        a = Words(lineList[i][0], lineList[i][1:])
        if(hash.num_items >= (len(hash.item)//2)):
            hash = HashTable(len(hash.item), hash.item, (len(hash.item)*2)+1, hash.num_items)
        InsertFullProdAscii(hash, a)
    return hash


def InsertStringLen(H, k, ssize):
    for i in range(len(H.item)):
        pos = (ssize + i) % len(H.item)
        if(type(H.item[pos]) != type(k)):
            if H.item[pos] < 0:
                H.item[pos] = k
                H.num_items += 1
                return pos
    return -1


def InsertAscii(H, k):
    for i in range(len(H.item)):
        pos = (ord(k.word[0]) + i) % len(H.item)
        if(type(H.item[pos]) != type(k)):
            if H.item[pos] < 0:
                H.item[pos] = k
                H.num_items += 1
                return pos
    return -1


def InsertProdAscii(H, k):
    for i in range(len(H.item)):
        prod = ord(k.word[0]) * ord(k.word[-1])
        pos = (prod + i) % len(H.item)
        if(type(H.item[pos]) != type(k)):
            if H.item[pos] < 0:
                H.item[pos] = k
                H.num_items += 1
                return pos
    return -1


def InsertSumAscii(H, k):
    for i in range(len(H.item)):
        sum = 0
        for j in range(len(k.word)):
            sum += ord(k.word[j])
        pos = (sum + i) % len(H.item)
        if(type(H.item[pos]) != type(k)):
            if H.item[pos] < 0:
                H.item[pos] = k
                H.num_items += 1
                return pos
    return -1


def InsertRecursive(H, k):
    for i in range(len(H.item)):
        rec = recursiveFormulation(k.word, len(H.item))
        #pos = (sum + i) % len(H.item)
        if(type(H.item[rec]) != type(k)):
            if H.item[rec] < 0:
                H.item[rec] = k
                H.num_items += 1
                return rec
    return -1


def recursiveFormulation(S, n):
    if(len(S) == 0):
        return 1
    else:
        return (ord(S[0]) + 225*recursiveFormulation(S[1:], n)) % n


def InsertFullProdAscii(H, k):
    for i in range(len(H.item)):
        prod = 0
        for j in range(len(k.word)):
            prod *= ord(k.word[j])
        pos = (prod + i) % len(H.item)
        if(type(H.item[pos]) != type(k)):
            if H.item[pos] < 0:
                H.item[pos] = k
                H.num_items += 1
                return pos
    return -1


def FindstringLen(H, word):
    for i in range(len(H.item)):
        pos = (len(word) + i) % len(H.item)
        if H.item[pos] == -1:
            return -1
        if H.item[pos].word == word:
            return pos
    return -1


def FindAscii(H, word):
    for i in range(len(H.item)):
        pos = (ord(word[0]) + i) % len(H.item)
        if H.item[pos] == -1:
            return -1
        if H.item[pos].word == word:
            return pos
    return -1


def FindProdAscii(H, word):
    for i in range(len(H.item)):
        prod = ord(word[0]) * ord(word[-1])
        pos = (int(prod) + i) % (len(H.item))
        if H.item[pos] == -1:
            return -1
        if H.item[pos].word == word:
            return pos
    return -1


def FindSumAscii(H, word):
    for i in range(len(H.item)):
        sum = 0
        for j in range(len(word)):
            sum += ord(word[j])
        pos = (sum + i) % len(H.item)
        if H.item[pos] == -1:
            return -1
        if H.item[pos].word == word:
            return pos
    return -1


def FindRecursive(H, word):
    for i in range(len(H.item)):
        rec = recursiveFormulation(word, len(H.item))
        if H.item[rec] == -1:
            return -1
        if H.item[rec].word == word:
            return rec
    return -1


def FindFullProdAscii(H, word):
    for i in range(len(H.item)):
        prod = 0
        for j in range(len(word)):
            prod *= ord(word[j])
        pos = (prod + i) % len(H.item)
        if H.item[pos] == -1:
            return -1
        if H.item[pos].word == word:
            return pos
    return -1


def similiartiesFunc1(H, w0, w1):
    word0 = FindstringLen(H, w0)
    word1 = FindstringLen(H, w1)
    if(word0 != -1 and word1 != -1):
        dotProd = np.dot(H.item[word0].embedding, H.item[word1].embedding)
        magW0 = np.linalg.norm(H.item[word0].embedding)
        magW1 = np.linalg.norm(H.item[word1].embedding)
        mag = magW0 * magW1
        return dotProd/mag
    return -1


def similiartiesFunc2(H, w0, w1):
    word0 = FindAscii(H, w0)
    word1 = FindAscii(H, w1)
    if(word0 != -1 and word1 != -1):
        dotProd = np.dot(H.item[word0].embedding, H.item[word1].embedding)
        magW0 = np.linalg.norm(H.item[word0].embedding)
        magW1 = np.linalg.norm(H.item[word1].embedding)
        mag = magW0 * magW1
        return dotProd/mag
    return -1


def similiartiesFunc3(H, w0, w1):
    word0 = FindProdAscii(H, w0)
    word1 = FindProdAscii(H, w1)
    if(word0 != -1 and word1 != -1):
        dotProd = np.dot(H.item[word0].embedding, H.item[word1].embedding)
        magW0 = np.linalg.norm(H.item[word0].embedding)
        magW1 = np.linalg.norm(H.item[word1].embedding)
        mag = magW0 * magW1
        return dotProd/mag
    return -1


def similiartiesFunc4(H, w0, w1):
    word0 = FindSumAscii(H, w0)
    word1 = FindSumAscii(H, w1)
    if(word0 != -1 and word1 != -1):
        dotProd = np.dot(H.item[word0].embedding, H.item[word1].embedding)
        magW0 = np.linalg.norm(H.item[word0].embedding)
        magW1 = np.linalg.norm(H.item[word1].embedding)
        mag = magW0 * magW1
        return dotProd/mag
    return -1


def similiartiesFunc5(H, w0, w1):
    word0 = FindRecursive(H, w0)
    word1 = FindRecursive(H, w1)
    if(word0 != -1 and word1 != -1):
        dotProd = np.dot(H.item[word0].embedding, H.item[word1].embedding)
        magW0 = np.linalg.norm(H.item[word0].embedding)
        magW1 = np.linalg.norm(H.item[word1].embedding)
        mag = magW0 * magW1
        return dotProd/mag
    return -1


def similiartiesFunc6(H, w0, w1):
    word0 = FindFullProdAscii(H, w0)
    word1 = FindFullProdAscii(H, w1)
    if(word0 != -1 and word1 != -1):
        dotProd = np.dot(H.item[word0].embedding, H.item[word1].embedding)
        magW0 = np.linalg.norm(H.item[word0].embedding)
        magW1 = np.linalg.norm(H.item[word1].embedding)
        mag = magW0 * magW1
        return dotProd/mag
    return -1


def switch1(userInput):
    lineList = readFile("glove.6B.50d.txt")
    if(userInput == 1):
        return stringLentoHash(lineList)
    elif(userInput == 2):
        return asciitoHash(lineList)
    elif(userInput == 3):
        return prodAsciitoHash(lineList)
    elif(userInput == 4):
        return sumAsciitoHash(lineList)
    elif(userInput == 5):
        return recursivetoHash(lineList)
    elif(userInput == 6):
        return fullProdAsciitoHash(lineList)


def switch2(userInput):
    lineList = open("SimiliartiesInputs.txt").read().split("\n")
    start = []
    stop = []
    average = []
    for i in range(len(lineList)):
        lineList[i] = lineList[i].split(" ")
    if(userInput == 1):
        for i in range(len(lineList)):
            start.append(timeit.default_timer())
            sim = similiartiesFunc1(H, lineList[i][0], lineList[i][1])
            stop.append(timeit.default_timer())
            if(sim == -1):
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = One or more of the words is not in the glove.6B.50d.txt file!")
            else:
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = " + str(sim))
    elif(userInput == 2):
        for i in range(len(lineList)):
            start.append(timeit.default_timer())
            sim = similiartiesFunc2(H, lineList[i][0], lineList[i][1])
            stop.append(timeit.default_timer())
            if (sim == -1):
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = One or more of the words is not in the glove.6B.50d.txt file!")
            else:
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = " + str(sim))
    elif(userInput == 3):
        for i in range(len(lineList)):
            start.append(timeit.default_timer())
            sim = similiartiesFunc3(H, lineList[i][0], lineList[i][1])
            stop.append(timeit.default_timer())
            if (sim == -1):
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = One or more of the words is not in the glove.6B.50d.txt file!")
            else:
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = " + str(sim))
    elif(userInput == 4):
        for i in range(len(lineList)):
            start.append(timeit.default_timer())
            sim = similiartiesFunc4(H, lineList[i][0], lineList[i][1])
            stop.append(timeit.default_timer())
            if (sim == -1):
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = One or more of the words is not in the glove.6B.50d.txt file!")
            else:
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = " + str(sim))
    elif(userInput == 5):
        for i in range(len(lineList)):
            start.append(timeit.default_timer())
            sim = similiartiesFunc5(H, lineList[i][0], lineList[i][1])
            stop.append(timeit.default_timer())
            if (sim == -1):
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = One or more of the words is not in the glove.6B.50d.txt file!")
            else:
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = " + str(sim))
    elif(userInput == 6):
        for i in range(len(lineList)):
            start.append(timeit.default_timer())
            sim = similiartiesFunc6(H, lineList[i][0], lineList[i][1])
            stop.append(timeit.default_timer())
            if (sim == -1):
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = One or more of the words is not in the glove.6B.50d.txt file!")
            else:
                print("Similartiy [" + lineList[i][0] + "," + lineList[i][1] + "] = " + str(sim))
    for i in range(len(start)):
        average.append(stop[i] - start[i])
    return np.average(average)


print("Choose Hash Function 1 to 6:")
print()
userInput = int(input("Choice: "))
print("Building Hash Table")
print()
start = timeit.default_timer()
H = switch1(userInput)
stop = timeit.default_timer()
print("Hash Table Stats:")
print("Table Size: " + str(len(H.item)))
print("Load Factor: " + str(H.num_items/len(H.item)))
longChain, averageChain = longestChain(H)
print("Longest Chain: " + str(longChain))
print("Average Chain Length: " + str(averageChain))

aveTime = switch2(userInput)
print()
print("Running time for query processing: " + str(stop - start))
print("Average table slots examined per word query: " + str(aveTime))
