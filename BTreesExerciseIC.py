# Code to implement a B-tree
# Programmed by Olac Fuentes
# Last modified June 28, 2019

import math as m

class BTree(object):
    # Constructor
    def __init__(self, item=[], child=[], isLeaf=True, max_items=5):
        self.item = item
        self.child = child
        self.isLeaf = isLeaf
        if max_items < 3:  # max_items must be odd and greater or equal to 3
            max_items = 3
        if max_items % 2 == 0:  # max_items must be odd and greater or equal to 3
            max_items += 1
        self.max_items = max_items


def FindChild(T, k):
    # Determines value of c, such that k must be in subtree T.child[c], if k is in the BTree
    for i in range(len(T.item)):
        if k < T.item[i]:
            return i
    return len(T.item)


def InsertInternal(T, i):
    # T cannot be Full
    if T.isLeaf:
        InsertLeaf(T, i)
    else:
        k = FindChild(T, i)
        if IsFull(T.child[k]):
            m, l, r = Split(T.child[k])
            T.item.insert(k, m)
            T.child[k] = l
            T.child.insert(k + 1, r)
            k = FindChild(T, i)
        InsertInternal(T.child[k], i)


def Split(T):
    # print('Splitting')
    # PrintNode(T)
    mid = T.max_items // 2
    if T.isLeaf:
        leftChild = BTree(T.item[:mid])
        rightChild = BTree(T.item[mid + 1:])
    else:
        leftChild = BTree(T.item[:mid], T.child[:mid + 1], T.isLeaf)
        rightChild = BTree(T.item[mid + 1:], T.child[mid + 1:], T.isLeaf)
    return T.item[mid], leftChild, rightChild


def InsertLeaf(T, i):
    T.item.append(i)
    T.item.sort()


def IsFull(T):
    return len(T.item) >= T.max_items


def Insert(T, i):
    if not IsFull(T):
        InsertInternal(T, i)
    else:
        m, l, r = Split(T)
        T.item = [m]
        T.child = [l, r]
        T.isLeaf = False
        k = FindChild(T, i)
        InsertInternal(T.child[k], i)


def Height(T):
    if T.isLeaf:
        return 0
    return 1 + Height(T.child[0])


def Print(T):
    # Prints items in tree in ascending order
    if T.isLeaf:
        for t in T.item:
            print(t, end=' ')
    else:
        for i in range(len(T.item)):
            Print(T.child[i])
            print(T.item[i], end=' ')
        Print(T.child[len(T.item)])


def PrintD(T, space):
    # Prints items and structure of B-tree
    if T.isLeaf:
        for i in range(len(T.item) - 1, -1, -1):
            print(space, T.item[i])
    else:
        PrintD(T.child[len(T.item)], space + '   ')
        for i in range(len(T.item) - 1, -1, -1):
            print(space, T.item[i])
            PrintD(T.child[i], space + '   ')


def Smallest(T):
    if T.isLeaf:
        return T.item[0]
    else:
        return Smallest(T.child[0])


def Largest(T):
    if T.isLeaf:
        return T.item[-1]
    else:
        return Largest(T.child[-1])


def NumItems(T):
    s = len(T.item)
    if T.isLeaf:
        return len(T.item)
    else:
        for c in range(len(T.item)):
            s += NumItems(T.child[c])
        return NumItems(T.child[len(T.item)]) + s


def LargestAtDepth(T,d):
    if(d == -1):
        return -m.inf
    elif(d == 0):
        return T.item[-1]
    else:
        return LargestAtDepth(T.child[-1], d-1)


def PrintAtDepth(T,d):
    if(d == -1):
        return None
    elif(d == 0):
        for a in T.item:
            print(a)
    else:
        1


def PrintDecending(T):
    if T.isLeaf:
        for i in range(len(T.item)-1, -1, -1):
            print(T.item[i], end=' ')
    else:
        PrintDecending(T.child[-1])
        for i in range(len(T.item)-1, -1, -1):
            print(T.item[i], end=' ')
            PrintDecending(T.child[i])


def FullNodes(T):
    # Prints items in tree in ascending order
    count = 0
    if(len(T.item) == T.max_items):
        count += 1
    if not T.isLeaf:
        for c in T.child:
            count += FullNodes(c)
    return count


def PrintAtDepth(T,d):
    # Prints items in tree in ascending order
    if(d == 0):
        for t in T.item:
            print(t, end=' ')
    else:
        if not T.isLeaf:
            for c in T.child:
                PrintAtDepth(c, d-1)


def Search(T,k):
    if k in T.item:
        return T
    if T.isLeaf:
        return None
    return Search(T.item[FindChild(T,k)], k)


if __name__ == "__main__":
    L = [6, 3, 16, 11, 7, 17, 14, 8, 5, 19, 15, 1, 2, 4, 18, 13, 9, 20, 10, 12, 21]

    T = BTree(item=[])
    for i in L:
        print('Inserting', i)
        Insert(T, i)
        print('Tree structure')
        PrintD(T, '')

    print('Keys in the tree: ')
    Print(T)
    print()
    print('Tree structure')
    PrintD(T, '')
    print(Smallest(T), end=' is the smallest value in the BTree!')
    print()
    print(Largest(T), end=' is the largest value in the BTree!')
    print()
    print(NumItems(T), end=' is the number of items in the BTree!')
    print()
    d = 2
    print(LargestAtDepth(T, d), end=' is the largest value at depth ' + str(d) + ' in the BTree!')
    print()
    PrintDecending(T)
    print()
    print(FullNodes(T), end=' is the number of full nodes in the BTree!')
    print()
    PrintAtDepth(T, 0)
