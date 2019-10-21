import math

# Team Members: Sneh Patel, Nicholas Pincus, Edgar Moriel

#1
def sumList(L):
    if(len(L) > 0):
        return L[0] + sumList[1:]

#2
def inList(a, A):
    if(len(A) > 0):
        if(A[0] == a):
            return True
        else:
            return inList(a, A[1:])

#3
def smallest(L):
    if(len(L) > 0):
        small = smallest(L[1:])
        if(L[0] < small):
            return L[0]
        else:
            return small
    else:
        return math.inf

#4
def toBinary(n):
    if(n == 0):
        return '0'
    if(n == 1):
        return '1'
    s = ['0', '1']
    return toBinary(n//2) + s[n%2]

#5
def isSame(L1, L2):
    if(len(L1) == 0 and len(L2) == 0 or len(L1) != len(L2)):
        return False
    else:
        if(L1[0] != L2[0]):
            return False
        else:
            return isSame(L1[1:], L2[1:])

#6
def reverseArr(L):
    if len(L) == 0:
        return L
    return L[-1] + reverseArr(L[0:-1])

#7
def isAscending(L):
    if(len(L) < 1):
        return True
    else:
        if(L[0] < L[1]):
            return isAscending(L[1:])
        else:
            return False

#8
def printBinary(n, strSoFar):
    if(n > 0):
        printBinary(n - 1, strSoFar + '0')
        printBinary(n - 1, strSoFar + '1')
    else:
        printBinary(n, '')

#9
def permutation(s):
    if(len(s) == 0):
        return s
    else:
        seed = permutation(s[1:])
        return