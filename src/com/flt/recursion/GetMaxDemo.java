package com.flt.recursion;


/**
 * @ClassName: GetMaxDemo.java
 * @author: Cheems
 * @description:通过递归求arr[0,leng]上最大值
 * @createTime: 2021年07月19日 22:18:00
 */
public class GetMaxDemo {
    public int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public int process(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int mid = left + ((right - left) >> 1);
        int leftValue = process(arr, left, mid);
        int rightValue = process(arr, mid + 1, right);
        return Math.max(leftValue, rightValue);
    }
}
