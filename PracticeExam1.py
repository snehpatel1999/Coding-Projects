def reverse(A, first, last):
    if last > first:
        A[first], A[last] = A[last], A[first]
        reverse(A, first+1, last-1)


def reverseWithSlicing(A, first, last):
    if last > first:
        A[first], A[last] = A[last], A[first]
        A[first+1:last] = reverse(A, first+1, last-1)
    return A


A = [10, 20, 30, 40, 50, 60]
print(A)
reverseWithSlicing(A, 0, len(A)-1)
print(A)