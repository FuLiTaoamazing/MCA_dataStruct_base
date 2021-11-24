package com.flt.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName: InsertSelector.java
 * @author: FULITAO
 * @description:
 * @createTime: 2021年07月17日 13:34:00
 */
public class InsertSelectorDemo {

    public static void insertSelector(int[] arr) {
        //思想就是
        //0~0上有序
        //0~1上有序
        //0~2上有序
        //0~i上有序
        for (int i = 1; i < arr.length; i++) {
            //里面的for循环 就是做到 0~i上有序的操作
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
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
        insertSelector(arr);
        System.out.println(Arrays.toString(arr));
    }
}
