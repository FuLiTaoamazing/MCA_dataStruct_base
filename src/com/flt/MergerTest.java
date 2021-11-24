package com.flt;

import java.util.Arrays;

/**
 * @ClassName: MergerTest.java
 * @author: FULITAO
 * @description:
 * @createTime: 2021年07月16日 14:01:00
 */
public class MergerTest {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1, 0};
        System.out.println("originalArr---->" + Arrays.toString(arr));
        test(arr);
        System.out.println("CurrentArr----->" + Arrays.toString(arr));
    }


    public static void test(int[] arr) {
        process(arr, 0, arr.length - 1);
    }


    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merger(arr, l, mid, r);
    }

    public static void merger(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            //这里不能用J代替 因为他是分成两边来进行排序  左和右各自排序好了之后  必须从l开始进行插入
            arr[l + j] = help[j];
        }
    }
}
