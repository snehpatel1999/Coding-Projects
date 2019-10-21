def parent(i):
    return (i-1)//2


def leftChild(i):
    return 2(i)+1


def rightChild(i):
    return 2(i)+2


def HeapInsert(H, item):
    H.append(item)
    i = len(H)-1
    while(i > 0 and item > H[parent(i)]):
        H[i] = H[parent(i)]
        i = parent(i)
    H[i] = item


def ExtractMax(H):
    if(len(H) < 1):
        return None
    if(len(H) == 1):
        return H.pop()
    root = H[0]
    p = H.pop()
    i = 0
    while True:
        L = [p]
        if(leftChild(i) < len(H)):
            L.append(H[leftChild(i)])
            if(rightChild(i) < len(H)):
                L.append(H[rightChild(i)])
        m = max(L)
        H[i] = m
        if(m == p):
            break
        elif(m == L[1]):
            i = leftChild(i)
        else:
            i = rightChild(i)
    return root


def HeapSort(A):
    H = []
    for a in A:
        HeapInsert(H, a)
    for i in range(len(A)):
        A[-(1+i)] = ExtractMax(H)


def sibling(H, k):
    if(k % 2 == 0):
        s = k - 1
    else:
        s = k + 1
    if(s > 0 and s < len(H)):
        return s
    return None


def secondLargest(H):
    if len(H) == 2:
        return H[1]
    if H[1] > H[2]:
        return 1
    return 2
