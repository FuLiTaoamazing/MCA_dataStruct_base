package com.flt.recursion;

/**
 * @ClassName: Knapsack.java
 * @author: Cheems
 * @description:3、背包问题，给定两个等长数组一个weight 和一个value  第一个数组的i位置代表values[i]位置的物品的加个，给你定一个整形bag代表一个袋子最多能装多少重量的货物，如何使得装的货物价格最高也只是考虑要这个货物或者不要这个货物
 * @createTime: 2021年08月31日 21:24:00
 */
public class Knapsack {

    public static int getMaxValue(int[] weight, int[] value, int bag) {
        return process(weight, value, 0, 0, bag);
    }

    /*
     * @Description:考虑到一共用了多少空间的解法
     * @Author: FULITAO
     * @param: weight, 不变的
     * @param: value,  不变的
     * @param: index,  index代表考虑到哪个下标上
     * @param: alReadyWeight, [0....index-1]上已经考虑的重量
     * @param: bag;  袋子的重量 不变
     * @Return: int
     * @Date: 2021/8/31 21:27
     **/
    public static int process(int[] weight, int[] value, int index, int alReadyWeight, int bag) {
        //前面考虑情况重量已经超过了 袋子的重量
        if (alReadyWeight > bag) {
            return -1;
        }
        //重量没超但是来到了最后的地方 但是index往后的已经没有价值了
        if (index == weight.length) {
            return 0;
        }
        //不把当前的index的价值考虑进去的情况
        int include = process(weight, value, index + 1, alReadyWeight, bag);
        //考虑index的价值
        int exclude = -1;
        //这是考虑index价值后 后面的情况的价值
        int excludeNext = process(weight, value, index + 1, (alReadyWeight + weight[index]), bag);
        //假如后面情况的价值有效的话
        if (excludeNext != -1) {
            //把当前的index的价值加上 他往后递归的价格
            exclude = value[index] + excludeNext;
        }

        return Math.max(include, exclude);
    }

    //考虑还剩多少空间的写法
    public static int process(int[] weight, int[] values, int index, int reset) {
        //当剩余空间小于等于的时候直接价值为0返回
        if (reset <= 0) {
            return 0;
        }
        //当index来到了数组的lmit这说明往后index+1已经没有价值了
        if (index == weight.length) {
            return 0;
        }
        //不考虑当前index情况 剩余重量不变
        int include = process(weight, values, index + 1, reset);
        //考虑index的情况 剩余空间减去index的重量
        int exclude = -1;
        int excludeNext = process(weight, values, index + 1, (reset - weight[index]));
        //说明考虑index的时候他是有价值的
        if (excludeNext > 0) {
            exclude = excludeNext + values[index];
        }

        return Math.max(include, exclude);
    }
}
