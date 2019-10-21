# ======================================
# DO NOT MODIFY THIS CODE
class Node(object):
    def __init__(self, data, next=None):
        self.data = data
        self.next = next


class List(object):
    def __init__(self):
        self.head = None
        self.tail = None


def Append(L, x):
    if L.head is None:
        L.head = Node(x)
        L.tail = L.head
    else:
        L.tail.next = Node(x)
        L.tail = L.tail.next


# Use the BuildList function to create a reference-based list L
# from a native list pList
def BuildList(pList):
    L = List()
    for d in pList:
        Append(L, d)
    return L


# DO NOT MODIFY THIS CODE
# ======================================


def Prepend(L, x):
    if(L.head is None):
        L.head = Node(x)
        L.tail = L.head
    elif(L.head == L.tail):
        L.head = Node(x)
        L.head.next = L.tail
    else:
        temp = L.head
        L.head = Node(x)
        L.head.next = temp


def Copy(L):
    NewL = List()
    temp = L
    while(temp.head.next is not None):
        Append(NewL, temp.head.data)
        temp.head = temp.head.next
    return NewL


L = List()
Append(L, 5)
Append(L, 10)
Append(L, 15)
Append(L, 20)
NL = Copy(L)
NL.head = NL.head.next
print(L.head.next.data)
