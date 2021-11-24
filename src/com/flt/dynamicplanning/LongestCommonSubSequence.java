package com.flt.dynamicplanning;

/**
 * @ClassName: LongestcommonSubSequence.java
 * @author: FLT
 * @description:题目7：最长公共子序列
 * @createTime: 2021年09月14日 22:38:00
 */
public class LongestCommonSubSequence {
    public static void main(String[] args) {
        String str1 = "123abcd";
        String str2 = "aaatoml";
        System.out.println(commonSubSequence(str1, str2));
    }

    public static int commonSubSequence(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        //构建两个样本对应模型的样本表
        int[][] dp = new int[str1.length()][str2.length()];
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;

        //初始化第一列
        for (int i = 1; i < chars1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], chars1[i] == chars2[0] ? 1 : 0);
        }

        //初始化第一行
        for (int j = 1; j < chars2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], chars1[0] == chars2[j - 1] ? 1 : 0);
        }
        //循环这张表
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = Math.max(dp[i][j],dp[i - 1][j-1]+ 1);
                }
            }
        }
        return dp[str1.length()-1][str2.length()-1];
    }

}
