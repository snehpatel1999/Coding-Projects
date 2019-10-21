import xlrd
import math as m
import os
import glob
import csv
from xlsxwriter.workbook import Workbook


def dijkstraShortestPath(G, startV):
    unvisisted = [i for i in range(len(G))]
    dist = [m.inf for v in range(len(G))]
    path = [-1 for v in G]
    path2 = ['' for v in G]
    dist[startV] = 0

    while(len(unvisisted) > 0):
        currentV = unvisisted.pop(0)
        for i in range(len(G[currentV])):
            if(G[currentV][i][1] != -1):
                edgeWeight = G[currentV][i][1]
                alternativePathDistance = dist[currentV] + edgeWeight

                if(alternativePathDistance < dist[G[currentV][i][0]]):
                    dist[G[currentV][i][0]] = alternativePathDistance
                    path[G[currentV][i][0]] = currentV
    return path, dist


def createAL():
    AL = []
    for i in range(sheet.nrows-1):
        AL.append([])
        for j in range(sheet.ncols-1):
            AL[i].append([j])
    for i in range(1, sheet.nrows):
        for j in range(1, sheet.ncols):
            AL[i-1][j-1].append(int(sheet.cell_value(i, j)))
    return AL


def prim(G):
    currentV = 0
    MST = []
    E = []
    visited = []
    minE = [None, None, m.inf]

    while(len(MST) != len(G)):
        visited.append(currentV)

        for i in range(len(G)):
            if(G[currentV][i][1] != 0):
                E.append([currentV, i, G[currentV][i][1]])

        for i in range(len(E)):
            if(E[i][1] not in visited and E[i][2] < minE[2] and E[i][2] > -1):
                minE = E[i]
        if(minE == [None, None, m.inf]):
            return MST
        E.remove(minE)
        MST.append(minE)
        currentV = minE[1]
        minE = [None, None, m.inf]
    return MST


def createALfromPrim(primAM):
    ALb = []
    for i in range(sheet.nrows - 1):
        ALb.append([])
    for i in range(len(primAM)):
        ALb[primAM[i][0]].append(primAM[i][1:])
    return ALb


def printPath(path, k):
    for j in range(len(path)):
        if(path[j] == -1):
            continue
        elif(path[j] == k):
            print("The Path to " + str(sheet.cell_value(0, j+1)) + " from " + str(sheet.cell_value(k+1, 0)) + " is: " + str(sheet.cell_value(0, path[j]+1)))
        else:
            pathListNum = []
            pathListStr = []
            pathListNum.append(path[j])
            while(pathListNum[-1] != k):
                a = pathListNum[-1]
                pathListNum.append(path[a])
            while(len(pathListNum) != 0):
                b = pathListNum.pop(-1)
                pathListStr.append(str(sheet.cell_value(0, b+1)))
            print("The Path to " + str(sheet.cell_value(0, j+1)) + " from " + str(sheet.cell_value(k+1, 0)) + " is: " + str(pathListStr).replace("[", '').replace("'", '').replace("]", ''))


def printPathPrim(path, dist, distVals, k):
    for j in range(len(path)):
        if(path[j] == -1):
            continue
        elif(path[j] == k):
            print("The Path to " + str(sheet.cell_value(0, j+1)) + " from " + str(sheet.cell_value(k+1, 0)) + " is: " + str(sheet.cell_value(0, path[j]+1)) + "     Distance is: " + str(dist[j]) + "     Increase in Distance: " + str(dist[j]-distVals[j]))
        else:
            pathListNum = []
            pathListStr = []
            pathListNum.append(path[j])
            while(pathListNum[-1] != k):
                a = pathListNum[-1]
                pathListNum.append(path[a])
            while(len(pathListNum) != 0):
                b = pathListNum.pop(-1)
                pathListStr.append(str(sheet.cell_value(0, b+1)))
            print("The Path to " + str(sheet.cell_value(0, j+1)) + " from " + str(sheet.cell_value(k+1, 0)) + " is: " + str(pathListStr).replace("[", '').replace("'", '').replace("]", '') + "     Distance is: " + str(dist[j]) + "     Increase in Distance: " + str(dist[j]-distVals[j]))


for csvfile in glob.glob(os.path.join('.', 'cities.csv')):
    wb = Workbook(csvfile[:-4] + '.xlsx')
    sheet = wb.add_worksheet()
    with open(csvfile, 'rt', encoding='utf8') as f:
        reader = csv.reader(f)
        for r, row in enumerate(reader):
            for c, col in enumerate(row):
                sheet.write(r, c, col)
    wb.close()
wb = xlrd.open_workbook('cities.xlsx')
sheet = wb.sheet_by_index(0)
G = createAL()
primAM = prim(G)
AM = createALfromPrim(primAM)

distVals = []
for i in range(len(G)):
    distVals.append([])

print()
print("PATH BEFORE PRIM'S ALGORITHM: ")
for i in range(len(G)):
    path, dist = dijkstraShortestPath(G, i)
    printPath(path, i)
    distVals[i] = dist

print()
print("PATH AFTER PRIM'S ALGORITHM: ")
for i in range(len(AM)):
    path1, dist1 = dijkstraShortestPath(AM, i)
    printPathPrim(path1, dist1, distVals[i], i)
