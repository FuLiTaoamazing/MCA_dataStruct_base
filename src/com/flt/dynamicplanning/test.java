package com.flt.dynamicplanning;

/**
 * @ClassName: test.java
 * @author: FLT
 * @description:货币面值代码
 * @createTime: 2021年09月15日 21:34:00
 */
public class test {
    public static void main(String[] args) {
        int[] arr={10,3,7};
        System.out.println(mainTest1(arr, 100));
    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: parArr,货币数组里面存的是每张货币的面值
     * @param: ami;目标金额
     * @Return: int 返回一共多少方法
     * @Date: 2021/9/15 21:35
     **/
    public static int mainTest1(int[] parArr, int ami) {
        return process1(parArr, 0, ami);
    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: parArr,货币数组 不变参数
     * @param: index,来到了那张货币 可变参数
     * @param: reset;剩余多少钱
     * @Return: int
     * @Date: 2021/9/15 21:36
     **/
    public static int process1(int[] parArr, int index, int reset) {
        //base case来到最后的位置看看是否满足条件即剩余钱数=0 满足返回1 不满足返回0
        if (index == parArr.length) {
            return reset == 0 ? 1 : 0;
        }
        int cost = parArr[index];
        //开始枚举每种货币的使用情况
        int res = 0;
        for (int i = 0; i * cost <= reset; i++) {
            //枚举使用0 张 1 张 2 张的情况
            res += process1(parArr, index + 1, reset - i * cost);
        }

        return res;
    }
}
