package com.flt.xor;

import jdk.nashorn.internal.objects.NativeError;

/**
 * @ClassName: EvenTimesOddTimes.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年07月19日 16:16:00
 */
public class EvenTimesOddTimes {
    //打印了出现奇数次个数的数
    public static void printOddTimesNum1(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        System.out.println(xor);
    }

    //打印两个出现奇数次个数的数
    public static void printOddTimesNum2(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            //第一次异或的值 是这两个出现奇数次的值得 结果
            xor ^= arr[i];
        }
        //取出异或结果的最右边的1
        //xor与上他的补码就能得到他最右边的1
        int onlyOne = xor & ((~xor) + 1);
        //第二次异或的值 取在onleOne这个值上有1的数进行异或
        int xor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((onlyOne & arr[i]) > 0) {
                xor2 ^= arr[i];
            }
        }
        System.out.println("第一个数" + xor2);
        System.out.println("第二个数" + (xor ^ xor2));
    }


    public static void bitCounter(int number) {
        int counter = 0;
        while (number != 0) {
            int nearOne = number & ((~number) + 1);
            counter++;
//            number ^= nearOne;
            number ^= nearOne;
        }


        System.out.println("一共有" + counter);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 1, 3, 3, 4, 5, 1, 1, 3, 4, 5};
        printOddTimesNum2(arr);
        bitCounter(-8);
    }
}
