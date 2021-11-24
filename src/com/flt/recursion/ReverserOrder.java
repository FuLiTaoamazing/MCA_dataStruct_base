package com.flt.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ReverserOrder.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年07月20日 16:53:00
 */
public class ReverserOrder {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 0, 5};
        List<String> strings = reverserOrder(arr);
        System.out.println(strings);
    }

    public static List<String> reverserOrder(int[] arr) {
        List<String> result = new ArrayList<>();
        process(result, arr, 0, arr.length - 1);
        return result;
    }

    public static void process(List<String> result, int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(result, arr, l, mid);
        process(result, arr, mid + 1, r);
        merge(result, arr, l, mid, r);
    }

    public static void merge(List<String> result, int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m+1;
        while (p1 <= m && p2 <= r) {
            if (arr[p1] > arr[p2]) {
                result.add("{" + arr[p1] + "," + arr[p2] + "}");
            }
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
    }
}
