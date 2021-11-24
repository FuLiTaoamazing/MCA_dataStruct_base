package com.flt.sort;

import java.util.Arrays;

/**
 * @ClassName: MergeSortDemo.java
 * @author: Cheems
 * @description:归并排序
 * @createTime: 2021年07月20日 14:15:00
 */
public class MergeSortDemo {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 0, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int arr[]) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    /**
     * 思想就是 给定一个数组arr，进行分解成 左边的子数组有序 和右边子数组有序的小问题
     * 即 l~mid 上有序  mid+1~r上有序 l和r分别是数组的开始和结束的位置
     * 在把左右两边有序的情况 通过一个help数组进行排序使他们整体有序
     * [1,3,5]
     * [2,4,6]
     * 定义两个指针 p1 和 p2 一个指向1的位置一个指向2的位置
     * help数组的长度等于这两个数组的长度之和
     * 然后进行判断 arr[p1]<=arr[p2]的话 把arr[p1]拷贝进help数组 p1++反之也是
     * 然后在把help数组有序的数重新拷贝回原来的arr
     * 但是要从l位置开始拷贝
     */

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        //使半边有序
        process(arr, l, mid);
        //使右半边有序
        process(arr, mid + 1, r);
        //进行整体运行
        merger(arr, l, mid, r);
    }


    public static void merger(int[] arr, int l, int m, int r) {

        //创建两个边界里数个数的大小
        int[] help = new int[r - l + 1];
        //这个i是用于插入help的位置所使用的
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        //此时的help数组就是 arr上左右两边子数组有序的情况
        for (i = 0; i < help.length; i++) {
            arr[i + l] = help[i];
        }

    }


    //求数组小和


}
