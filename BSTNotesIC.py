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


def inOrder(T):
    if(T == None):
        return
    else:
        inOrder(T.left)
        print(T.item,end=' ')
        inOrder(T.right)


def smallest(T):
    if(T.left is None):
        return T
    return smallest(T.left)


def largest(T):
    if(T.right is None):
        return T
    return largest(T.right)

def search(T, k):
    if(T is None):
        return None
    if(T.item == k):
        return T
    if(T.item < k):
        return search(T.left,k)
    else:
        return search(T.right,k)


T = None
A = [70, 50, 90, 130, 150, 40, 10, 30, 100]
for a in A:
    T = InsertBST(T, a)

inOrder(T)
print()
print(largest(T).item)