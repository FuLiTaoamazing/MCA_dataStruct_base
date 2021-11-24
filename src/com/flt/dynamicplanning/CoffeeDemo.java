package com.flt.dynamicplanning;

import java.util.Arrays;

/**
 * @ClassName: CoffeeDemo.java
 * @author: FLT
 * @description:题目8:洗咖啡杯问题
 * @createTime: 2021年09月23日 21:05:00
 */
public class CoffeeDemo {
    public static void main(String[] args) {
        int[] drinks = {1, 1, 5, 5, 7, 10, 12, 12, 12, 12,12,12,15,20};
        int a = 3;
        int b = 10;
        System.out.println(process1(drinks, a, b, 0, 0));

        System.out.println(process2(drinks, a, b));

    }


    /*
     * @Description:暴力递归解法
     * @Author: FULITAO
     * @param: drinks,咖啡喝完的时间点，固定参数
     * @param: a,使用咖啡机洗咖啡杯所使用的的时间，固定参数
     * @param: b,等咖啡杯自己挥发干净的时间，固定参数
     * @param: index,轮到了哪个杯子喝完  可变参数
     * @param: washLine;洗咖啡杯的机器最早的完成时间 可变参数
     * @Return: int 最早完成的时间
     * @Date: 2021/9/23 21:06
     **/
    public static int process1(int[] drinks, int a, int b, int index, int washLine) {
        //当洗到了最后一个杯子只要考虑他是否使用咖啡机来洗还是等他自己挥发
        if (index == drinks.length - 1) {
            //这里取使用咖啡机 还是 自己挥发干净的最优解即到达点数最小的情况
            return Math.min(
                    //为什么这里要考虑 washLine 和drinks[index]的最大值呢
                    //可能会出现咖啡机在 8点钟就可以洗咖啡杯了，但是最后一杯咖啡得到12点才能喝完 所以取他们的最大值在加上洗咖啡杯所使用的的时间
                    Math.max(washLine, drinks[index]) + a,
                    //这里表示的是最后一杯喝完的时间点加上他自然挥发的时间就是他最终完成drinks[0...index-1]上所有咖啡杯完成的时间
                    drinks[index] + b);
        }

        //洗index这个杯子的所使用的的时间要考虑两个方面 他使用咖啡机来喜washLine得更新，他不使用咖啡机来洗 wasLine不更新

        //使用咖啡机来洗的情况
        //当前咖啡喝完加上洗干净的时间等于他咖啡机能解锁的时间
        int wash = drinks[index] + a;
        //因为使用了咖啡机 得改变washLine 然后再看完成所有咖啡所使用的时间
        int includeNextTime = process1(drinks, a, b, index + 1, wash);
        //这里考虑的是假如当前这个杯子放进咖啡机来洗的话他最终完成时间都大于他往下走的过程的时间  所以得考虑这两个结果的最大值
        int includeTime = Math.max(wash, includeNextTime);


        //自然挥发的情况
        //当前杯子自然挥发来到的时间点
        int currTime = drinks[index] + b;
        //当前杯子自然挥发 以及往下的杯子 变干净来到的时间点
        int excludeNextTime = process1(drinks, a, b, index + 1, washLine);
        //和上面判断的逻辑是相同的
        int excludeTime = Math.max(currTime, excludeNextTime);


        //最终判断是考虑 使用和不使用的情况的最优值 即两个数的最小值
        return Math.min(includeTime, excludeTime);
    }

    /*
     * @Description:动态规划的写法
     * @Author: FULITAO
     * @param: drinks,
     * @param: a,
     * @param: b;
     * @Return: int
     * @Date: 2021/9/23 21:33
     **/
    public static int process2(int[] drinks, int a, int b) {
        //先看下都使用咖啡机洗的话最离谱的情况下的最大值washLine
        int limit = drinks[0] + a;
        for (int i = 1; i < drinks.length; i++) {
            limit = Math.max(drinks[i], limit) + a;
        }
        int n = drinks.length;
        int[][] dp = new int[n][limit + 1];
        //通过base case初始化这个dp表
//        if (index == drinks.length - 1) {
//            这里取使用咖啡机 还是 自己挥发干净的最优解即到达点数最小的情况
//            return Math.min(
//                   为什么这里要考虑 washLine 和drinks[index]的最大值呢
//                   可能会出现咖啡机在 8点钟就可以洗咖啡杯了，但是最后一杯咖啡得到12点才能喝完 所以取他们的最大值在加上洗咖啡杯所使用的的时间
//                    Math.max(washLine, drinks[index]) + a,
//                    这里表示的是最后一杯喝完的时间点加上他自然挥发的时间就是他最终完成drinks[0...index-1]上所有咖啡杯完成的时间
//                    drinks[index] + b);
//        }
        //可以知道base case所填的值就是 n-1位置 washLine从0遍历到limit的情况
        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[n - 1][washLine] = Math.min(Math.max(washLine, drinks[n - 1] )+ a, drinks[n - 1] + b);
        }

        //开始填其他位置 index的坐标从 n-2开始到0
        //wash的坐标从0 到limit
        for (int index = n - 2; index >= 0; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {
                //看暴力递归
                //使用咖啡机来洗的情况
                //当前咖啡喝完加上洗干净的时间等于他咖啡机能解锁的时间
                //int wash = drinks[index] + a;
                //因为使用了咖啡机 得改变washLine 然后再看完成所有咖啡所使用的时间
                //int includeNextTime = process1(drinks, a, b, index + 1, wash);
                //这里考虑的是假如当前这个杯子放进咖啡机来洗的话他最终完成时间都大于他往下走的过程的时间  所以得考虑这两个结果的最大值
                // int includeTime = Math.max(wash, includeNextTime);


                //自然挥发的情况
                //当前杯子自然挥发来到的时间点
                //int currTime = drinks[index] + b;
                //当前杯子自然挥发 以及往下的杯子 变干净来到的时间点
                //int excludeNextTime = process1(drinks, a, b, index + 1, washLine);
                //和上面判断的逻辑是相同的
                //int excludeTime = Math.max(currTime, excludeNextTime);
                //最终判断是考虑 使用和不使用的情况的最优值 即两个数的最小值
                //return Math.min(includeTime, excludeTime);
                int includeTime = Integer.MAX_VALUE;
                int wash = drinks[index] + a;
                if (wash <= limit) {
                    includeTime = Math.max(wash, dp[index + 1][wash]);
                }

                int excludeTime = Math.max(drinks[index] + b, dp[index + 1][washLine]);

                dp[index][washLine]=Math.min(includeTime,excludeTime);
            }
        }

        return dp[0][0];
    }


}
