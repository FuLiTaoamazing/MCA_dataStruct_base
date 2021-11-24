package com.flt.sort;

import java.util.Arrays;

/**
 * @ClassName: SelectSortDemo.java
 * @author: FULITAO
 * @description:选择排序 复杂度是N的平方
 * @createTime: 2021年07月17日 13:06:00
 */
public class SelectSortDemo {


    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //  0~n-1
        //  1~n-1
        //  2~n-1
        for (int i = 0; i < arr.length - 1; i++) {
            //先记录最小值的index
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    //for test  产生一个随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (((maxValue + 1) * Math.random()) - ((maxValue + 1) * Math.random()));
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr = generateRandomArray(20,20);
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
