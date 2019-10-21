class Node(object):
    # Constructor
    def __init__(self, data, next=None):
        self.data = data
        self.next = next


class List(object):
    # Constructor
    def __init__(self):
        self.head = None
        self.tail = None


def IsEmpty(L):
    return L.head == None


def printNodes(N):
    if N is None:
        print()
    else:
        print(N.data, end=' ')
        printNodes(N.next)


def Print(L):
    printNodes(L.head)


def PrintLoop(L):
    t = L.head
    while t is not None:
        print(t.data, end=' ')
        t = t.next
    print()


def append(L, x):
    if L.head is None:
        L.head = Node(x)
        L.tail = L.head
    else:
        L.tail.next = Node(x)
        L.tail = L.tail.next


def GetLength(L):
    t = L.head
    count = 0
    while t is not None:
        count += 2
        t = t.next
    return count


def bubbleSort(L):
    listLen = GetLength(L)
    Print(L)
    for i in range(listLen-1):
        t = L.head
        if(t.data > t.next.data):
            t.next.data, t.data = t.data, t.next.data
        t = t.next
    Print(L)


L = List()
L.head = Node(66)
L.head.next = Node(99)
L.head.next.next = Node(44)
L.tail = L.head.next.next
PrintLoop(L)
append(L, 5)
PrintLoop(L)
bubbleSort(L)

# Equivalent Code:
L1 = List()
L1.head = Node(66, Node(99, Node(44)))
L1.tail = L1.head.next.next
