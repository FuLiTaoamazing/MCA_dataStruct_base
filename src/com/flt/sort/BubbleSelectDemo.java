package com.flt.sort;

import java.util.Arrays;

/**
 * @ClassName: BubleSelect.java
 * @author: FULITAO
 * @description:冒泡排序
 * @createTime: 2021年07月17日 13:16:00
 */
public class BubbleSelectDemo {

    public static void bubbleSelect(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int e = arr.length - 1; e > 0; e--) {
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        bubbleSelect(arr);
        System.out.println(Arrays.toString(arr));
    }
}
