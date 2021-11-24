package com.flt.dynamicplanning;

import java.rmi.server.RemoteServer;

/**
 * @ClassName: CoinsWay.java
 * @author: FLT
 * @description:动态规划第五题
 * @createTime: 2021年09月08日 21:47:00
 */
public class CoinsWay {
    public static void main(String[] args) {
        int[] arr = {10, 20, 50};
        int ami = 10000;
        long t1 = System.currentTimeMillis();
        System.out.println(ways1(arr, ami));
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 + "ms");
        long t3 = System.currentTimeMillis();
        System.out.println(ways2(arr, ami));
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3 + "ms");
        long t5 = System.currentTimeMillis();
        System.out.println(ways3(arr, ami));
        long t6 = System.currentTimeMillis();
        System.out.println(t6 - t5 + "ms");
        long t7 = System.currentTimeMillis();
        System.out.println(ways4(arr, ami));
        long t8 = System.currentTimeMillis();
        System.out.println(t8 - t7 + "ms");

    }

    //arr中都是正数且无重复，返回组成ami的方法数
    //暴力递归尝试方法
    public static int ways1(int[] arr, int ami) {
        if (arr == null || arr.length == 0 || ami < 0) {
            return 0;
        }
        return process1(arr, 0, ami);
    }

    //可以自由使用arr[index...]中的所有面值的货币，每一种面值的货币都能使用任意次
    //组成reset有多少种方法
    public static int process1(int[] arr, int index, int reset) {
        //当index来到了arr的中止位置的时候表示你没有货币可以选择了
        if (index == arr.length) {
            return reset == 0 ? 1 : 0;
        }
        int res = 0;
        int cost = arr[index];

        //枚举使用0张。。1张...2张。。。N张当前钞票的情况
        for (int i = 0; i * cost <= reset; i++) {
            res += process1(arr, index + 1, reset - (cost * i));
        }

        return res;
    }

    //记忆化搜索
    public static int ways2(int[] arr, int ami) {
        if (arr == null || arr.length == 0 || ami < 0) {
            return 0;
        }
        //增加一个缓存表 观察递归规程可以知道 index的变化方位是0...index reset的变化范围是0...ami
        int[][] dp = new int[arr.length + 1][ami + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(arr, 0, ami, dp);
    }


    public static int process2(int[] arr, int index, int reset, int[][] dp) {
        if (dp[index][reset] != -1) {
            return dp[index][reset];
        }
        //当index来到了arr的中止位置的时候表示你没有货币可以选择了
        if (index == arr.length) {
            dp[index][reset] = reset == 0 ? 1 : 0;
            return dp[index][reset];
        }
        int res = 0;
        int cost = arr[index];

        //枚举使用0张。。1张...2张。。。N张当前钞票的情况
        for (int i = 0; i * cost <= reset; i++) {
            res += process2(arr, index + 1, reset - (cost * i), dp);
        }
        dp[index][reset] = res;
        return dp[index][reset];
    }

    //动态规划1.0写法
    public static int ways3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int reset = 0; reset <= aim; reset++) {
                int res = 0;
                for (int i = 0; i * arr[index] <= reset; i++) {
                    res += dp[index + 1][reset - (i * arr[index])];
                }
                dp[index][reset] = res;
            }
        }

        return dp[0][aim];
    }

    //动态规划2.0写法 优化枚举
    public static int ways4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int reset = 0; reset <= aim; reset++) {
                dp[index][reset] = dp[index + 1][reset];
                if (reset - arr[index] >= 0) {
                    dp[index][reset] += dp[index][reset - arr[index]];
                }
            }
        }

        return dp[0][aim];
    }
}
