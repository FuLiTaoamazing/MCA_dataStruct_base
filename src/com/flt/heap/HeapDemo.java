package com.flt.heap;

import java.util.ArrayList;

/**
 * @ClassName: HeapDemo.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年07月21日 15:35:00
 */
public class HeapDemo {
    public int[] heap;
    public final int limit;
    public int heapSize;

    public HeapDemo(int limit) {
        this.limit = limit;
        this.heap = new int[limit];
        heapSize = 0;
    }

    public boolean empty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == limit;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("Heap is Full");
        }
        heap[heapSize] = value;
        insertHeap(heapSize++);
    }

    public int pop() {
        if (empty()) {
            throw new RuntimeException("Heap Is Empty");
        }
        int maxValue = heap[0];
        swap(0, --heapSize);
        heapIfy();
        return maxValue;
    }

    //进行插入操作整理最大堆顺序
    public void insertHeap(int index) {
        while (this.heap[index] > this.heap[(index - 1) / 2]) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //进行弹出操作 重新调整最大堆的性质
    public void heapIfy() {
        int left = 1;
        int iargest;
        int index = 0;
        while (left < heapSize) {
            //这里就是判断是这个节点的做儿子大还是又儿子大
            iargest = (left + 1) < heapSize && heap[left] < heap[left + 1] ? left + 1 : left;
            //这个if分支是 判断 头结点是否大于子节点 大于就直接退出
            if (heap[index] > heap[iargest]) {
                break;
            }
            swap(index, iargest);
            index = iargest;
            left = index * 2 + 1;
        }
    }


    public void swap(int i, int j) {
        heap[i] = heap[i] ^ heap[j];
        heap[j] = heap[i] ^ heap[j];
        heap[i] = heap[i] ^ heap[j];

    }
}
