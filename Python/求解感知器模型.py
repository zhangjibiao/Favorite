# -*- coding: utf-8 -*-
# 求感知机模型
import numpy as np
import random

def originForm(x,y):
    #使用感知机的原始形式求解
    w=np.array([0,0])
    b=0
    
    counter=1
    finish=False
    sampleSize=x.shape[0]

    #进行迭代
    print("使用感知机的原始形式求解：")
    print("迭代次数 误分类点    w         b       wx+b,α为x的第一个元素，β为x的第二个元素")
    while(finish != True):
        index=random.randint(0,sampleSize-1)
        if (y[index] * (w.dot(x[index]) + b)<=0):
            w=w + y[index]*x[index]
            b=b + y[index]
    
            print(counter,end="\t\t")
            counter += 1
            print("x"+(str)(index+1),end="\t\t")
            print('w=[{:2},{:2}]'.format(w[0], w[1]), end="\t")
            print("b={:2}".format(b), end="\t")
            print('{}α + {}β + {}'.format(w[0], w[1], b))
    
            
        finish=True
        for index in range(sampleSize):
            if (y[index] * (w.dot(x[index]) + b) <= 0):
                finish=False
                break;
    #输出结果
    print("-"*50)
    print("w={},  b={}".format(w,b))
    print("分离超平面：{}α + {}β + {} = 0, 其中，α为x的第一个元素，β为x的第二个元素".format(w[0],w[1],b))


#使用感知机的对偶形式求解
def dualForm(x,y):
    b=0
    sampleSize=x.shape[0]
    a=np.zeros((sampleSize),dtype=int)
    gramMatrix=np.zeros((sampleSize, sampleSize), dtype=int)

    counter=0
    finish=False
    
    #计算gram矩阵
    for i in range(sampleSize):
        for j in range(sampleSize):
           gramMatrix[i][j]=x[i].dot(x[j])    

    #判断样本是否是误分类
    def is_corCl(i):
        sum=0    
        for j in range(sampleSize):
            sum += a[j] * y[j] * gramMatrix[j][i]
        result = y[i] * (sum+b)
        if result<=0:
            return False
        else: return True
    
    #打印迭代过程
    def printIte(i,counter):
        if counter==0:
            print("使用感知机的对偶形式求解：")
            print("Gram矩阵")
            print(gramMatrix)
            print("迭代次数 误分类点", end="\t")
            for i in range(sampleSize):
                print("a{}\t".format(i+1), end="")
            print("b")
             
        print("{:5}      x{:<2} ".format(counter, i), end="\t")
        for i in range(sampleSize):
            print("{}\t".format(a[i]), end="")
        print(b)
        
    #进行迭代
    while(finish != True):
        i=random.randint(0,sampleSize-1)
        if is_corCl(i) == False: #误分类点
            a[i] += 1
            b += y[i]
            printIte(i,counter)
            counter += 1
               
        #判断模型是否完成
        finish=True
        for i in range(sampleSize):
            if is_corCl(i)==False:
                finish=False
                break;
    #计算w
    w = np.array([0,0])
    for i in range(sampleSize):
        w += a[i] * x[i] *y[i]
    
    #输出模型
    print("-"*50)
    print("w={},  b={}".format(w,b))
    print("分离超平面：{}α + {}β + {} = 0, 其中，α为x的第一个元素，β为x的第二个元素".format(w[0],w[1],b))
    
        
def main():
    x=np.array([
        [1,5],
        [4,4],
        [5,6],
        [2,3],
        [3,2]])
    y=np.array([1,1,1,-1,-1])
    
    originForm(x,y)
    print("-"*50)
    print("-"*50)
    dualForm(x,y)
    
main()