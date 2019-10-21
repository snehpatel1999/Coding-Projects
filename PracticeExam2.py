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


def parent(i):
    return (i-1)//2


def leftChild(i):
    return 2*(i)+1


def rightChild(i):
    return 2*(i)+2


def HeapInsert(H, item):
    H.append(item)
    i = len(H)-1
    while(i > 0 and item > H[parent(i)]):
        H[i] = H[parent(i)]
        i = parent(i)
    H[i] = item


def sumPath(T,k):
    sum = 0
    if T is None:
        return -1
    if(k == T.item):
        return k
    if(k < T.item):
        sum += sumPath(T.left, k)
    if(k > T.item):
        sum += sumPath(T.right, k)
    if(sum == -1):
        return sum
    return sum + T.item


def sumPathHeap(H, k):
    s = H[k]
    if(k > 0):
        s += sumPathHeap(H, parent(k))
    return s


def largerThan(H,k):
    return largerThan_(H, k, 0)


def largerThan_(H, k, i):
    if(i >= len(H) or H[i] <= k):
        return 0
    return 1 + largerThan_(H, k, leftChild(i)) + largerThan_(H, k, rightChild(i))


T = []
A = [11, 8, 16, 5, 9, 2, 7, 3, 10, 13, 12, 20, 19]
for a in A:
    HeapInsert(T, a)

#inOrder(T)
print()
print(sumPathHeap(T, 3))
