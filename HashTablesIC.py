def FindAndRemove(H,k):
    first_neg_2 = -2302
    for i in range(len(H.item)):
        pos = (k+i) % len(H.item)
        if(H.item[pos] == k):
            if(first_neg_2 != -2302):
                H.item[first_neg_2] = k
                H.item[pos] = -2
                pos = first_neg_2
            return pos
        if(H.item[pos] == -1):
            return -1
        if(H.item[pos] == -2 and first_neg_2 == -2302):
            first_neg_2 = pos
    return -1
