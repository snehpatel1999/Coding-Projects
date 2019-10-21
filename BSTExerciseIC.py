class BST(object):
    # Constructor
    def __init__(self, item, left=None, right=None):
        self.item = item
        self.left = left
        self.right = right


def InsertBST(T, newItem):
    if(T == None):
        T = BST(newItem)
    elif(T.item > newItem):
        T.left = InsertBST(T.left, newItem)
    else:
        T.right = InsertBST(T.right, newItem)
    return T


def ShowTree(T, ind):
    if(T is not None):
        ShowTree(T.right, ind+'   ')
        print(ind, T.item)
        ShowTree(T.left, ind+'   ')


def lessThan(T, k):
    if(T == None):
        return None
    else:
        if(T.item > k):
            lessThan(T.left, k)
            print(T.item)
        else:
            lessThan(T.left, k)
            print(T.item)
            lessThan(T.right, k)
            print(T.item)


def printByDepth(T):
    L = [[T,0]]
    d = - 1
    while(len(L)>0):
        front = L.pop(0)
        t,depth = front[0],front[1]
        if(t is not None):
        if depth != d:
            d = depth
            print('\nNodes at depth', depth)
        print(t.item,end='  ')
        L.append([t.left,depth+1])
        L.append([t.right,depth+1])
    print()


def printAtDepth(T, d):
    if(T is not None):
        if(d == 0):
            print(T.item)
        else:
            printAtDepth(T.left, d-1)
            printAtDepth(T.right, d-1)

def listToTree(L):
    if(len(L) == 0):
        return None
    mid = len(L)//2
    return BST(L[mid], listToTree(L[:mid]), listToTree(L[mid+1:]))

T1 = None
A = [70, 50, 90, 130, 150, 40, 10, 30, 100]
A.sort()
print(A)

T2 = listToTree(A)
ShowTree(T2, '')
L = []
L.rem
Q = [T1]
b = Q.pop(0)
Q.append(b.left)
Q.append(b.right)
