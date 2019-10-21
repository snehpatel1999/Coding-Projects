import math
import numpy as np


class HashTable(object):
    def __init__(self, size):
        self.item = np.zeros(size, dtype=np.int) - 1


def Insert(H, k):
    for i in range(len(H.item)):
        pos = (k + i) % len(H.item)
        if H.item[pos] < 0:
            H.item[pos] = k
            return pos
    return -1


def maxList(L):
    if (len(L) == 0):
        return -math.inf
    else:
        maxVal = 0
        for i in range(len(L)):
            if (L[i] > maxVal):
                maxVal = L[i]
        return maxVal


def longestChain(H):
    if (len(H.item) != 0):
        count = 0
        tempCount = 0
        for i in range(len(H.item)):
            if (H.item[i] != -1):
                tempCount += 1
            else:
                if (tempCount > count):
                    count = tempCount
                    return count
        return count


def longestChain2(H):
    curr_chain = 0
    longest_chain = 0
    i = 0
    while True:
        if H.item[i%len(H.item)] != -1:
            curr_chain += 1
            if curr_chain == len(H.item):
                return curr_chain
        else:
            if curr_chain > longest_chain:
                longest_chain = curr_chain
            curr_chain = 0
            if i >= len(H.item):
                break
        i += 1
    return longest_chain

"""""
def longestChain1(H):
    neg_ones= []
    for i in range(len(H.item)):
        if H.item[i] == -1:
            neg_ones.append(i)
    if len(neg_ones)
"""""


def isHeap(L):
    if (len(L) == 0):
        return False
    else:
        for i in range(len(L)):
            if (2 * i + 2 < len(L)):
                if (L[i] < L[(2 * i) + 1] or L[i] < L[(2 * 1) + 2]):
                    return False
        return True


L = [1, 2, 3, 4, 5, 2, 3, 1, 4, 6, 2]
print(maxList(L))

H = HashTable(5)
Insert(H, 5)
Insert(H, 6)
Insert(H, -2)
Insert(H, 3)
Insert(H, 4)
print(H.item)
print(longestChain(H))