def sum_list(n):
    s = 0
    for m in n:
        s += m
    return s


def getfactorial(n):
    if n==0:
        return 1
    return n * getfactorial(n-1)


def sort(L):
    if len(L) == 0:
        return L
    small = []
    large = []
    pivot = L[0]
    for s in L[1:]:
        if s < pivot:
            small += [s]
        else:
            large += [s]
    return sort(small) + [pivot] + sort(large)


def printUp(n):
    if n > -1:
        printUp(n-1)
        print(n)


def printDown(n):
    if n > -1:
        print(n)
        printDown(n-1)


def reverseString(str):
    if len(str) == 0:
        return str
    return str[-1] + reverseString(str[0:-1])


def exponential(n):
    if n == 0:
        return 1
    return exponential(n-1) * 2^n


def indexFinder(L, n):
    if (len(L) == 0):
        return -1
    if (n == L[0]):
        return 0
    p = indexFinder(L[1:], n)
    if(p > -1):
        p += 1
        return p


L = [20, 3, 51, 16]
a = indexFinder(L, 14)
print(a)
