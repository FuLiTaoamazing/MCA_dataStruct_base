package com.flt.dynamicplanning;

/**
 * @ClassName: WalkDemo.java
 * @author: FLT
 * @description:详情看动态递归第一题
 * @createTime: 2021年09月06日 22:21:00
 */
public class WalkDemo {

    public static void main(String[] args) {
        System.out.println(dynamicWalk(7, 2, 5, 3));
    }

    public static int walkMain1(int n, int cur, int reset, int p) {
        if (n < 2 || cur > n || reset == 0 || p > n || p < n) {
            return 0;
        }
        return walk1(n, cur, reset, p);
    }


    /*
     * @Description:
     * @Author: FULITAO
     * @param: n,一共有多少个格子
     * @param: cur,当前在那个格子
     * @param: reset,还剩多少步
     * @param: p;目标格子是哪个
     * @Return: int
     * @Date: 2021/9/6 22:22
     **/
    public static int walk1(int n, int cur, int reset, int p) {
        //当没有步数可以走的时候判断是否到达了p位置
        if (reset == 0) {
            return cur == p ? 1 : 0;
        }
        //当来到了0位置 此时只能向1位置移动
        if (cur == 0) {
            return walkMain1(n, cur + 1, reset - 1, p);
        }
        //当来到了n位置 只能向n-1位置移动
        if (cur == n) {
            return walkMain1(n, cur - 1, reset - 1, p);
        }
        //当没有命中上面情况说明当前cur在中间 可以往左 也可以往右
        return walk1(n, cur - 1, reset - 1, p) + walk1(n, cur + 1, reset - 1, p);
    }


    //加上缓存的递归
    public static int walk1MainCache(int n, int cur, int k, int p) {
        if (n < 2 || cur > n || k == 0 || p > n || p < n) {
            return 0;
        }
        //这个dp是用于记录 所有位置与步数的结果
        //            cur 是当前位置 他不可能超过n  所以取n+1 0位置不使用
        //            reset 是步数  他不可能超过k  所以取k+1 0位置不使用
        int[][] dp = new int[n + 1][k + 1];
        //对这张表进行初始化操作 规定没有算过的都为-1
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        return walk1Cache(n, cur, k, p, dp);
    }

    public static int walk1Cache(int n, int cur, int reset, int p, int[][] dp) {
        //假如缓存表中已经有这个 位置以及步数的记录 直接return结果即可
        if (dp[cur][reset] != -1) {
            return dp[cur][reset];
        }
        //当没有步数可以走的时候判断是否到达了p位置
        if (reset == 0) {
            dp[cur][reset] = cur == p ? 1 : 0;
            return dp[cur][reset];
        }
        //当来到了0位置 此时只能向1位置移动
        if (cur == 0) {
            dp[cur][reset] = walk1Cache(n, cur + 1, reset - 1, p, dp);
            return dp[cur][reset];
        }
        //当来到了n位置 只能向n-1位置移动
        if (cur == n) {
            dp[cur][reset] = walk1Cache(n, cur - 1, reset - 1, p, dp);
            return dp[cur][reset];
        }
        //当没有命中上面情况说明当前cur在中间 可以往左 也可以往右
        dp[cur][reset] = walk1Cache(n, cur - 1, reset - 1, p, dp) + walk1Cache(n, cur + 1, reset - 1, p, dp);
        return dp[cur][reset];
    }


    public static int dynamicWalk(int n, int m, int k, int p) {
        //首先构造出这个提议的表
        int[][] dp = new int[n + 1][k + 1];
        //初始化操作
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i == p && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = -1;

            }
        }

        //初始化中通过的到的表开始往后推 通过列遍历行
        for (int col = 1; col <= k; col++) {
            for (int row = 1; row <= n; row++) {
                //此时 i是列  j是行
                if (row == 1) {
                    dp[row][col] = dp[row + 1][col - 1];
                    continue;
                }
                if (row == n) {
                    dp[row][col] = dp[row - 1][col - 1];
                    continue;
                }
                dp[row][col] = dp[row + 1][col - 1] + dp[row - 1][col - 1];
            }
        }

        return dp[m][k];
    }


}
