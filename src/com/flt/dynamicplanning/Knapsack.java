package com.flt.dynamicplanning;

/**
 * @ClassName: Kapsack.java
 * @author: FLT
 * @description:背包问题的动态规划
 * @createTime: 2021年09月07日 22:36:00
 */
public class Knapsack {
    public static void main(String[] args) {

    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: weight,货物的重量
     * @param: index,选择到哪个货物
     * @param: reset,背包的剩余的重量
     * @param: prices;货物的价值
     * @Return: int 最大价值
     * @Date: 2021/9/7 22:37
     **/
    public static int dpWay(int[] weight, int index, int reset, int[] prices) {

        int[][] dp = new int[weight.length + 1][reset + 1];
        //这张表 的第一个数组是 0~~~~index 因为要考虑 index==weight.length的情况
        //第二个数组是 0~~~~~reset 剩余重量

        //初始化 int[index][0~~~rest]=0  因为java的特性我们可以不用初始化

        //观察原来递归的代码
        //不考虑当前index情况 剩余重量不变
//        int include = process(weight, values, index + 1, reset);
//        //考虑index的情况 剩余空间减去index的重量
//        int exclude = -1;
//        int excludeNext = process(weight, values, index + 1, (reset - weight[index]));
//        //说明考虑index的时候他是有价值的
//        if (excludeNext > 0) {
//            exclude = excludeNext + values[index];
//        }
//
//        return Math.max(include, exclude);

        //dp[index][reset]位置的值是根据=Math(dp[index+1][reset],dp[index+1][reset-weight[index]])得到的


        //所以我们就从index的最终行往前遍历，但是index这行已经确定所以只要遍历index-1即可
        for (int i = index - 1; i >= 0; i--) {
            //在依次填好每一列的值
            for (int j = 0; j < reset; j++) {
                //不包括当前index的情况
                int exculde = dp[index + 1][reset];
                int inculde = -1;
                //这里的if判断是因为可能reset会出现小于0的情况 小于0的时候这个值就没有了意义
                if (reset - weight[index] >= 0) {
                    //包括就是当前的价值加上 index+1后面的结果
                    inculde = prices[index] + dp[index + 1][reset - weight[index]];
                }
                dp[index][reset] = Math.max(exculde, inculde);
            }
        }
        return dp[index][reset];
    }
}
