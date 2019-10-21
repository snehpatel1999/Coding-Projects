def adjacencyList():
    text = open("AdjacencyListFile.txt").read().split()
    source = []
    vertex = []
    AdjacencyList = []
    for i in range(0, len(text), 2):
        source.append(text[i])
    for i in range(1, len(text), 2):
        vertex.append(text[i])
    print(text)

adjacencyList()