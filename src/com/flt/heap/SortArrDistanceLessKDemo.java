package com.flt.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName: SortArrDistanceLessK.java
 * @author: Cheems
 * @description: 已知一个几乎有序的数组，几乎有序是指，如果把数组排好序的话，
 * 每个元素移动的距离不超过K，并且K相对于数组长度说是比较小的，请选择一个合适的排序策略
 * ，对于这个数组进行排序
 * @createTime: 2021年07月21日 21:50:00
 */
public class SortArrDistanceLessKDemo {

    public static void main(String[] args) {
        int[] arr={1,3,2,4,5,6,7};
        sortArrDistanceLessK(arr,3);
        System.out.println(Arrays.toString(arr));
    }

    public static void sortArrDistanceLessK(int[] arr, int k) {
        //这个优先队列默认实现的是最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        //首先把0到K的数装进最小堆中
        for (; index <= Math.min(arr.length - 1, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            //第一步把a[0]的位置确定下来然后在从1~k的位置进行最小堆操作
            arr[i] = heap.poll();
            heap.add(arr[index]);
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

}
