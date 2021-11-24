package com.flt.bucketsort;

import java.util.Arrays;

/**
 * @ClassName: BucketSortDemo.java
 * @author: Cheems
 * @description:桶排序思想的实现
 * @createTime: 2021年07月22日 21:50:00
 */
public class BucketSortDemo {
    public static void main(String[] args) {
        String str="进厂检测报告(2021\\01th)CEM1".replace("\\","").replace(".","");
        String newStr=str.replace("\\","");
        System.out.println("");
    }

    //for data 0~200 arr数组装的值是0~200范围中的数
    public static void countSort(int[] arr) {
        int[] help = new int[201];
        //先构建词频数组
        for (int i = 0; i < arr.length; i++) {
            help[arr[i]] += 1;
        }
        int j = 0;
        for (int i = 0; i < help.length; i++) {
            for (int k = 0; k < help[i]; k++) {
                arr[j] = i;
                j++;
            }
        }

    }

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    public static int maxBits(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }

        return res;
    }

    public static void radixSort(int[] arr, int l, int r, int digit) {
        //默认是个十进制数
        final int radix = 10;
        int i = 0, j = 0;
        //构建一个help数组与data数组大小相等
        int[] help = new int[r - l + 1];
        //从个位开始 循环到最高位
        for (int dit = 1; dit <= digit; dit++) {
            //构建当前进制大小的数组 做词频统计 此时count数组的下标所对应的值
            //就是小于这个下标的数的个数
            int[] count = new int[radix];
            for (i = l; i <= r; i++) {
                j = getDigit(arr[i], dit);
                count[j]++;
            }
            //对count做前序和的操作，此时count数组下标所对应的值
            //就是小于等于这个下标数的个数
            for (i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }
            //从又往左遍历dataArr
            for (i = r; i >= l; i--) {
                //求出这个dit位上的数
                j = getDigit(arr[i], dit);
                //这个数在count'中可以知道他有多少个数小于等于他
                help[count[j] - 1] = arr[i];
                //放了一个 就说明少了一种情况 --
                count[j]--;
            }
            //再把help数组拷贝回去data数组
            for (i = l, j = 0; i <= r; i++, j++) {
                arr[i] = help[j];
            }
        }
    }


    public static int getDigit(int number, int digit) {
        return (int) (number / Math.pow(10, digit - 1) % 10);
    }

}
