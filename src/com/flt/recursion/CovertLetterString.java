package com.flt.recursion;

/**
 * @ClassName: CovertLetterString.java
 * @author: Cheems
 * @description:规定1和A对应，2和B对应，3和C对应。给定一个数字字符串“111“他可以表示为 AAA 或者KA 或者AK，给定一个数字字符串，返回他能有多少中结果
 * @createTime: 2021年08月31日 21:08:00
 */
public class CovertLetterString {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        System.out.println(covert("1113"));
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 + "ms");
        long t3 = System.currentTimeMillis();
        System.out.println(dpway("1113"));
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3 + "ms");
    }

    public static int covert(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    //这个算法的递归主要就是要考虑i位置的情况
    //当 i位置 <3的时候 即i=1或者2的时候 他才有第二种可能性不然只有第一种可能性就是i本身转换的可能性
    //就考虑 i是否能和i+1是否有可能性 考虑 i位置 和i+ （i+1）的可能性
    public static int process(char[] chars, int i) {
        //当来到了 结尾的时候  i只有自己这种可能性不能和别人结合了
        //所以return 1
        if (i == chars.length) {
            return 1;
        }
        //当i位置的数字为0的时候
        //它不能和i+1的位置进行结合直接return 0即可
        if (chars[i] == '0') {
            return 0;
        }
        //当 i位置的值等于1的时候
        if (chars[i] == '1') {
            //只考虑i位置的情况  i+1往后的去递归
            int res = process(chars, i + 1);
            //考虑i和(i+1)结合的情况  i+2往后的去递归
            if (i + 1 < chars.length) {
                res += process(chars, i + 2);
            }
            return res;
        }
        //当i位置等于2的时候
        if (chars[i] == '2') {
            //只考虑 i位置的情况
            int res = process(chars, i + 1);
            //i和（i+1)的位置特殊考虑  因为当chars[i]==2的时候
            //chars[i+1]位置必须小于7 才有考虑的必要不然无需考虑
            if (i + 1 < chars.length && (chars[i + 1] > '0' && chars[i + 1] <= '7')) {
                res += process(chars, i + 2);
            }
            return res;
        }
        //最后一种情况 i位置是 大于2的情况 即{3....9}之间 就只有他自己一种情况
        return process(chars, i + 1);
    }

    //字符串方法的动态规划写法
    public static int dpway(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        //因为 index可以最大来到chars.length所以建立一个chars.length+1的dp表
        int n = str.length();
        int[] dp = new int[n + 1];
        //   if (i == chars.length)return 1;
        //   根据这个分支可以知道当 index来到了最后的地方 值为1
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {

            if (chars[i] == '0') {
                dp[i] = 0;
            } else if (chars[i] == '1') {
                //只考虑i位置的情况  i+1往后的去递归
                dp[i] = dp[i + 1];
                //考虑i和(i+1)结合的情况  i+2往后的去递归
                if (i + 1 < n) {
                    dp[i] += dp[i + 2];
                }
            } else if (chars[i] == '2') {
                //只考虑 i位置的情况
                dp[i] = dp[i + 1];
                //i和（i+1)的位置特殊考虑  因为当chars[i]==2的时候
                //chars[i+1]位置必须小于7 才有考虑的必要不然无需考虑
                if (i + 1 < n && (chars[i+1] >= '0' && chars[i+1] <= '7')) {
                    dp[i] += dp[i + 2];
                }
            }else{
                dp[i]=dp[i+1];
            }
        }

        return dp[0];
    }

}
