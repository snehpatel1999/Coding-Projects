# Implementation of hash tables with linear probing
# Programmed by Olac Fuentes
# Last modified July 11, 2019

import numpy as np
import inspect


class HashTable(object):
    # Builds a hash table of size 'size', initilizes items to -1 (which means empty)
    # Constructor
    def __init__(self, size):
        self.item = np.zeros(size, dtype=np.object) - 1


def Insert(H, k):
    # Inserts k in H unless H is full
    # Returns the position of k in H, or -1 if k could not be inserted
    for i in range(len(H.item)):
        pos = (k + i) % len(H.item)
        if H.item[pos] < 0:
            H.item[pos] = k
            return pos
    return -1


def InsertStringLen(H, k, ssize):
    # Inserts k in H unless H is full
    # Returns the position of k in H, or -1 if k could not be inserted
    for i in range(len(H.item)):
        pos = (ssize + i) % len(H.item)
        if isinstance(Words, type):
            if H.item[pos] < 0:
                H.item[pos] = k
                return pos
    return -1


def Find(H, k):
    # Returns the position of k in H, or -1 if k is not in the table
    for i in range(len(H.item)):
        pos = (k + i) % len(H.item)
        if H.item[pos] == k:
            return pos
        if H.item[pos] == -1:
            return -1
    return -1


def Delete(H, k):
    # Deletes k from H. It returns the position where k was, or -1 if k was not in the table
    # Sets table item where k was to -2 (which means deleted)
    f = Find(H, k)
    if f >= 0:
        H.item[f] = -2
    return f


def h(H, k):
    return k % len(H.item)


def longestChain(H):
    neg_ones = np.argwhere(H.item == -1)
    if len(neg_ones) == 0:
        return len(H.item)
    neg_ones = np.append(neg_ones, neg_ones[0] + len(H.item))
    print('Negative ones:', neg_ones)
    chain_lens = neg_ones[1:] - neg_ones[:-1] - 1
    print('Chain lengths:', chain_lens)
    return np.max(chain_lens)


if __name__ == "__main__":

    H = HashTable(11)
    A = [23, 5, 7, 9, 12, 32, 45, 22]

    for a in A:
        Insert(H, a)
        print('Insert', a)
        print('Table:', H.item)
        print('Longest Chain:', longestChain(H))


