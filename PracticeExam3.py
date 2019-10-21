import numpy as np


def left_handed_and_compressed(S):
    for i in range(len(S)):
        if S[i] >= 0:
            if S[i] > i or S[S[i]] != -1:
                return False
    return True


def num_set0(S):
    return np.sum(S<0)


def num_set1(S):
    count = 0
    for s in S:
        if s < 0:
            count += 1
    return count


def is_in_singleton(S, i):
    return i not in S and S[i] < 0

