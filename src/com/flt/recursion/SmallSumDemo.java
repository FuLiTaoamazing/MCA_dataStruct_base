package com.flt.recursion;

/**
 * @ClassName: SmallSumDemo.java
 * @author: Cheems
 * @description:求数组小和
 * @createTime: 2021年07月20日 16:29:00
 */
public class SmallSumDemo {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(smallSum(arr));
    }


    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }


    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }


    public static int merge(int[] arr, int l, int m, int r) {
        int res = 0;
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = m + 1;
        int i = 0;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[j + l] = help[j];
        }
        return res;
    }
}
