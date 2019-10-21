import numpy as np

def min_coins(c, den):
    C = np.zeros(c+1, dtype=int) + 1000
    C[0] = 0
    for i in range(1, c+1):
        for d in den:
            if i >= d and C[i] > C[i-d]+1:
                C[i] = C[i-d]+1
    print(C)
    return C[-1]


def min_coins2(totalVal, coinVal):
    coins = np.zeros(totalVal+1, dtype=int) + 10000
    coins[0] = 0
    for c in coinVal:
        coins[c] = 1
        for d in range(c, totalVal + 1):
            if coins[d-c] + 1 < coins[d]:
                coins[d] = coins[d-c]+1
    print(coins)
    return coins[-1]


def integerEqual(f1, f2, tries=1000, tolerance=0.0001):
    for i in range(tries):
        x = np.random.random_integers()
        y1 = eval(f1)
        y2 = eval(f2)
        if np.abs(y1-y2) > tolerance:
            return False
    return True



den = [1, 3, 5, 7, 10]
c = 100
print(min_coins2(c, den))