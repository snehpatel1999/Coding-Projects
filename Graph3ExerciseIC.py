import Graphs2ExerciseIC as g2Exer
import Graphs as g
import numpy as np
import math as m

def topoSort(G):
    TS = []
    inDeg = g2Exer.in_degree(G)
    Q = list(inDeg[inDeg == 0])
    count = 0
    while(len(Q) > 0):
        TS.append(Q[0])
        u = Q.pop(0)
        for v in G[u]:
            inDeg[v] -= 1
            if(inDeg[v] == 0):
                Q.append(v)
    if(len(TS) == len(G)):
        return TS
    return None


def dijkstraShortestPath(G, startV):
    unvisisted = [i for i in range(len(G))]
    dist = [m.inf for v in range(len(G))]
    path = [-1 for v in G]
    dist[startV] = 0

    while(len(unvisisted) > 0):
        currentV = unvisisted.pop(0)
        for i in range(len(G[currentV])):
            edgeWeight = G[currentV][i][1]
            alternativePathDistance = dist[currentV] + edgeWeight

            if(alternativePathDistance < dist[G[currentV][i][0]]):
                dist[G[currentV][i][0]] = alternativePathDistance
                path[G[currentV][i][0]] = currentV
    return path, dist



def g1(n):
    G = np.zeros((n, n), dtype=bool)
    for i in range(n):
        for j in range(n):
            G[i, j] = i > j and i-j < 3
    return G


def g2(n):
    G = [ [] for i in range(n) ]
    for i in range(n):
        G[i].append((i+1)%n)
    return G


def g3(n):
    if n==0:
        return []
    return [[n-1, n]] + g3(n-1)


def in_degOfV(G, v):
    count = 0
    for i in range(len(G)):
        for j in range(len(G[i])):
            if(G[i][j] == v):
                count += 1
    return count


def changeOut(G, v):
    for i in range(len(G[v])):
        if(G[v][i] == True):
            G[v][i] = False
    return G


#G = [[1, 4], [2], [], [2], [1, 5, 7], [6, 8], [2, 3], [5, 8], [6]]
G = [[[1, 1], [2, 4]], [[2, 2], [4, 6]], [[3, 4], [4, 2]], [], [[3, 1]]]
print(dijkstraShortestPath(G, 0))

