import numpy as np
from statistics import mode
import timeit as time


# Part 1 (Backtracking)
def knapsack(W, V, goal):
    if goal == 0:
        return 0
    if goal < 0 or len(W) == 0 or np.sum(W) < goal:
        return 0
    take_first_solution = knapsack(W[1:], V[1:], goal-W[0])
    if take_first_solution is not None:
        return V[0] + take_first_solution
    dont_take_first_solution = knapsack(W[1:], V[1:], goal)
    return dont_take_first_solution


# Part 2 (Greedy)
def greedyKnapsack(W, V, goal):
    temp = []
    sumVal = 0

    for i in range(len(W)):
        temp.append([W[i], V[i]])
    temp.sort(reverse=True)

    while(len(temp) > 0):
        if goal - temp[0][0] >= 0:
            goal = temp[0][0]
            sumVal += temp[0][1]
        else:
            frac = goal / temp[0][0]
            sumVal += temp[0][0] * frac
            goal = int(goal - (temp[0][0] * frac))
        temp = temp[1:]
    return sumVal


# Part 3 (Randomization)
def randKnapsack(N, goal):
    final = []
    temp = 0
    n = []
    x = 0
    for i in range(100):
        W = []
        V = []
        Wtemp = []
        Vtemp = []
        g = goal
        N1 = np.random.permutation(N)
        for j in range(len(N1)):
            W.append(N1[j][0])
            V.append(N1[j][1])
        while(g > 0 and len(W) > 0):
            Vtemp.append(V[0])
            Wtemp.append(W[0])
            g -= W[0]
            W = W[1:]
            V = V[1:]
        final.append([Wtemp, np.sum(Vtemp)])
    for i in range(len(final)):
        if(final[i][1] > temp):
            n = final[i][0]
            x = final[i][1]
    return n, x


# Part 4 (Dynamic)
def dynamicKnapsack(W, V, goal):
    final = []
    for i in range(np.sum(W) + np.sum(V)):
        final.append(0)
    for c in range(goal + 1):
        for d in range(len(V)):
            if W[d] <= c:
                final[d] = np.maximum(final[c], final[c - W[d]] + V[d])
    return np.max(final)


avgTi = []
a = 0
while(a < 29):
    W = np.random.randint(18, size=10)
    V = np.random.randint(18, size=10)
    print(W)
    print(V)
    N = []
    for i in range(len(W)):
        N.append([W[i], V[i]])
    print(N)
    goal = np.random.randint(1, np.sum(W) + 1)
    print("The Goal is: " + str(goal))
    fastestTime = []

    # Part 1 (Backtracking)
    start = time.default_timer()
    print("The Highest-Value Load was: " + str(knapsack(W, V, goal)))
    stop = time.default_timer()
    time1 = stop - start
    print("Time Elapased: " + str(time1) + "s")
    fastestTime.append(time1)


    # Part 2 (Greedy)
    start = time.default_timer()
    print("The Highest-Value Load was: " + str(greedyKnapsack(W, V, goal)))
    stop = time.default_timer()
    time2 = stop - start
    print("Time Elapased: " + str(time2) + "s")
    fastestTime.append(time2)


    # Part 3 (Randomization)
    start = time.default_timer()
    S, n = randKnapsack(N, goal)
    print("The Highest-Value Load Permutation was: " + str(S) + " and the Load was: " + str(n))
    time3 = stop - start
    print("Time Elapased: " + str(time3) + "s")
    fastestTime.append(time3)


    # Part 4 (Dynamic)
    start = time.default_timer()
    print("The Highest-Value Load was: " + str(dynamicKnapsack(W, V, goal)))
    time4 = stop - start
    print("Time Elapased: " + str(time4) + "s")
    fastestTime.append(time4)


    # Determine the Fast Design Technique
    Ti = -1

    temp = 0
    for i in range(len(fastestTime)):
        if(fastestTime[i] > temp):
            temp = fastestTime[i]
            Ti = i
    avgTi.append(Ti)
    if(Ti == -1):
        print("Error Calculating Fastest Time!")
    elif(Ti == 0):
        print("The Fastest Time was of Algorithm 1 (Backtracking) with a time of: " + str(temp) + "s")
    elif(Ti == 1):
        print("The Fastest Time was of Algorithm 2 (Greedy) with a time of: " + str(temp) + "s")
    elif(Ti == 2):
        print("The Fastest Time was of Algorithm 3 (Randomization) with a time of: " + str(temp) + "s")
    elif(Ti == 3):
        print("The Fastest Time was of Algorithm 4 (Dynamic) with a time of: " + str(temp) + "s")
    a += 1
print("The Fastest Algorithm in most of the cases is Algorithm Number " + str(mode(avgTi)+1))
