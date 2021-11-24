package com.flt.sort;

import java.util.Arrays;

/**
 * @ClassName: HeapSortDemo.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年07月21日 16:17:00
 */
public class HeapSortDemo {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            insertHeap(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapIfy(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }


    public static void insertHeap(int[] arr, int index) {
//        while (this.heap[index] > this.heap[(index - 1) / 2]) {
//            swap(index, (index - 1) / 2);
//            index = (index - 1) / 2;
//        }
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }


    public static void heapIfy(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        int largest = -1;
        while (left < heapSize ) {
            largest = (left + 1) < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            if (arr[index] > arr[largest]) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
