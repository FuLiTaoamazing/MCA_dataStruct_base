package com.flt.binarysearch;

import java.util.Arrays;

/**
 * @ClassName: NearLeftDemo.java
 * @author: FULITAO
 * @description:找到最左边大于等于value的下标
 * @createTime: 2021年07月17日 14:27:00
 */
public class NearLeftDemo {

    public static int nearestIndex(int[] arr, int value) {
        //用于记录最左边大于等于value的index
        int index = -1;
        //左指针
        int l = 0;
        //右指针
        int r = arr.length - 1;

        while (l <= r) {
            //计算中点
            int mid = l + ((r - l) >> 1); //等价于 mid (l+r)/2
            if (arr[mid] >= value) {
                index = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }


    // for test
    public static int comparator(int[] arr, int maxValue) {
        int i = -1;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] >= maxValue) {
                i = j;

            }
        }

        return i;
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

    }

}
