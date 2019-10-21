import numpy as np
import math as m

def count_edges(G):  # Running Time: O(|V|)
    count = 0
    for i in range(len(G)):
        count += len(G[i])
    return count


def highest_count_edge(G):  # Running Time: O(|V| + |E|)
    highestEdge = 0
    counti = 0
    countj = 0
    for i in range(len(G)):
        for j in range(len(G[i])):
            if(G[i][j][1] > highestEdge):
                highestEdge = G[i][j][1]
                counti = i
                countj = G[i][j][0]
    return [counti] + [countj] + [highestEdge]


def out_degrees(G):  # Running Time: O(|V|)
    outDeg = np.zeros(len(G), dtype=int)
    for i in range(len(G)):
        outDeg[i] = len(G[i])
    return outDeg


def in_degree(G):  # Running Time: O(|V|^2)
    inDeg = np.zeros(len(G), dtype=int)
    for i in range(len(inDeg)):
        count = 0
        for j in range(len(G)):
            for k in range(0, len(G[j])):
                if(G[j][k] == i):
                    count += 1
        inDeg[i] = count
    return inDeg


def AMtoAL(G):  # Running Time: O(|V| + |E|)
    AL = []
    for i in range(len(G[0])):
        AL.append([])
    for i in range(len(G)):
        for j in range(len(G[i])):
            if(G[i][j] != m.inf):
                AL[j].append(G[i][j])
    return AL


def ELtoAL(G):
    AL = []
    for edge in G:
        s, d, w = edge[0], edge[1], edge[2]
        while(len(AL) <= s or len(AL) <=d):
                AL.append([])
        AL[s].append[[d, w]]
    return AL


def ELtoALW(G):
    AL = []
    for edge in G:
        s, d = edge[0], edge[1]
        while(len(AL) <= s or len(AL) <=d):
                AL.append([])
        AL[s].append[d]
    return AL


G = [[1, 1, 1, 1, 5, 7], [2, 8], [3, 9], [10], [11]]
print(out_degrees(G))
print(in_degree(G))
print(AMtoAL(G))

