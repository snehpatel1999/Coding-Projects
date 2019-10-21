# Program to build a one-node decision tree
# Programmed by Olac Fuentes
# Last modified July 3, 2019

import numpy as np
import random


class decisionTreeNode(object):
    # Constructor
    def __init__(self, att, thr, left, right):
        self.attribute = att
        self.threshold = thr
        # left and right are either bynary classifications, or references to
        # decision tree nodes
        self.left = left
        self.right = right


def entropy(l, m=[]):
    ent = 0
    for p in [l, m]:
        if len(p) > 0:
            pp = sum(p) / len(p)
            pn = 1 - pp
            if pp < 1 and pp > 0:
                ent -= len(p) * (pp * np.log2(pp) + pn * np.log2(pn))
    ent = ent / (len(l) + len(m))
    return ent


def classify(DT, atts):
    if atts[DT.attribute] < DT.threshold:
        if DT.left in [0, 1]:  # DT.left is a classification rule
            return DT.left
        else:  # DT.left is a reference to a decision tree
            return classify(DT.left, atts)
    else:
        if DT.right in [0, 1]:
            return DT.right
        else:
            return classify(DT.right, atts)


def buildDT(attributes, target, n_splits, goal_acc, min_size):
    # Builds a one-node decision tree to classify data
    # It tries n_splits random splits and keeps the one that results in the lowest entropy
    print('examples:', len(target), ' default accuracy', max([np.mean(target), 1 - np.mean(target)]))
    print('Trying random splits')
    best_ent = 1
    min_att_val = np.min(attributes, axis=0)
    for i in range(n_splits):
        while True:
            a = random.randrange(attributes.shape[1])
            ex = random.randrange(attributes.shape[0])
            thr = attributes[ex, a]
            if thr > min_att_val[a]:  # making sure we don't have an empty split
                break
        less = attributes[:, a] < thr
        more = ~ less
        tgt_less = target[less]
        tgt_more = target[more]
        ent = entropy(tgt_less, tgt_more)
        print(i, a, thr, ent)  # Used for debugging
        if ent < best_ent:
            best_ent, best_a, best_thr = ent, a, thr
            left_child = int(np.mean(tgt_less) + .5)
            right_child = int(np.mean(tgt_more) + .5)
            left_best_split = less
            right_best_split = more
            print(a, thr, ent)  # Used for debugging
    print('Best split:', best_a, best_thr, best_ent)
    ml = np.mean(target[left_best_split])
    left_acc = max([ml, 1 - ml])
    left_size = np.sum(left_best_split)
    mm = np.mean(target[right_best_split])
    right_acc = max([mm, 1 - mm])
    right_size = np.sum(right_best_split)
    print('Accuracies:', left_acc, right_acc)
    print('Sizes:', left_size, right_size)
    # Modify as follows:
    # if left_acc is less than goal accuracy and left_size is greater than min_size
    if(left_acc < goal_acc and left_size > min_size):
        left_child = buildDT(attributes[left_child:], target[left_child:], n_splits, goal_acc, min_size)
    if(right_acc < (1-goal_acc) and right_size > min_size):
        right_child = buildDT(attributes[right_child:], target[right_child:], n_splits, goal_acc, min_size)
    # build left decision subtree recursively
    # do the same for the right side
    return decisionTreeNode(best_a, best_thr, left_child, right_child)


def numNode(T):
    return 1 + numNodeLeft(T.left) + numNodeRight(T)


def numNodeLeft(T):
    if(T == 1 or T == 0):
        return 0
    if (T.left == 1 or T.left == 0):
        return 1
    return 1 + numNode(T.left)


def numNodeRight(T):
    if(T == 1 or T == 0):
        return 0
    if(T.right == 1 or T.right == 0):
        return 1
    return 1 + numNode(T.right)


def numAtt(T):
    if(T == 1 or T == 0):
        return 0
    if((T.left == 1 or T.left == 0) and (T.right == 1 or T.right == 0)):
        return 1
    return 1 + numAtt(T.left) + numAtt(T.right)


def height(T):
    sumLeft = 0
    sumRight = 0
    if(T.left != 1 and T.left != 0):
        sumLeft += height(T.left)
    else:
        sumLeft = 1
    if(T.right != 1 and T.right != 0):
        sumRight += height(T.right)
    else:
        sumRight = 1
    if(sumLeft >= sumRight):
        sumVal = sumLeft
    else:
        sumVal = sumRight
    return 1 + sumVal


def printStat(dt):
    print("The number of Nodes in the DTree is: " + str(numNode(dt)))
    print("The number of times attributes are used for splitting is: " + str(numAtt(dt)))
    print("The height of the DTree is: " + str(height(dt)))


attributes = []
target = []
infile = open("magic04.txt", "r")
for line in infile:
    target.append(int(line[-2:-1] == 'g'))
    attributes.append(np.fromstring(line[:-2], dtype=float, sep=','))
infile.close()
attributes = np.array(attributes)
target = np.array(target)

# Split data into training and testing
ind = np.random.permutation(len(target))
split_ind = int(len(target) * 0.8)
train_data = attributes[ind[:split_ind]]
test_data = attributes[ind[split_ind:]]
train_target = target[ind[:split_ind]]
test_target = target[ind[split_ind:]]

dt = buildDT(train_data, train_target, 200, .82, 10)

train_pred = np.zeros(train_target.shape, dtype=int)
for i in range(len(train_pred)):
    train_pred[i] = classify(dt, train_data[i])
train_acc = np.sum(train_pred == train_target) / len(train_pred)
print('train accuracy:', train_acc)

test_pred = np.zeros(test_target.shape, dtype=int)
for i in range(len(test_pred)):
    test_pred[i] = classify(dt, test_data[i])
test_acc = np.sum(test_pred == test_target) / len(test_pred)
print('test accuracy:', test_acc)

L = []
for a in range(40):
    dt1 = buildDT(train_data, train_target, 200, .82, 10)
    train_pred = np.zeros(train_target.shape, dtype=int)
    for i in range(len(train_pred)):
        train_pred[i] = classify(dt1, train_data[i])
    train_acc1 = np.sum(train_pred == train_target) / len(train_pred)

    test_pred = np.zeros(test_target.shape, dtype=int)
    for i in range(len(test_pred)):
        test_pred[i] = classify(dt1, test_data[i])
    test_acc1 = np.sum(test_pred == test_target) / len(test_pred)
    L.append(test_acc1)
print("Test Accuracy of Single Decision Tree: " + str(test_acc))
ave = np.average(L)
print("Average Test Accuracy of Multiple Decision Trees: " + str(ave))
printStat(dt)
