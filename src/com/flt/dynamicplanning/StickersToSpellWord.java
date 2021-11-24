package com.flt.dynamicplanning;

import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: StickersToSpellWord.java
 * @author: FLT
 * @description:题目6贴纸问题
 * @createTime: 2021年09月09日 21:39:00
 */
public class StickersToSpellWord {
    public static void main(String[] args) {
        String str1 = "abcd123";
        String str2 = "e3f2u1jklmn";
        System.out.println(testMain(str1,str2));
    }

    public static int testMain(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if(str1.charAt(i)==str2.charAt(j)){
                    ans++;
                }
            }
        }
        return ans;
    }


    public static int miniStickers1(String[] stickers, String target) {
        int n = stickers.length;
        //这个二维数组的作用是用于记录每个位置帖子的字母出现的词频
        int[][] map = new int[n][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                map[i][c - 'a']++;
            }
        }
        //记忆化搜索所使用的缓存
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process1(dp, map, target);
    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: dp, 缓存结果所使用的的map
     * @param: map, 统计好的每个帖子的词频
     * @param: reset; 剩余的字符串
     * @Return: int
     * @Date: 2021/9/14 21:27
     **/
    public static int process1(Map<String, Integer> dp, int[][] map, String reset) {
        //假如命中缓存直接返回
        //因为在初始化缓存过程中已经把 ""这个情况处理了所以就不需要考虑这个情况了
        if (dp.containsKey(reset)) {
            return dp.get(reset);
        }
        //最后的结果 因为是求最小值所以先赋值给Integer类型的最大值
        int ans = Integer.MAX_VALUE;
        //获取贴子的长度
        int n = map.length;
        //对剩余的目标字符串求词频
        int[] tmap = new int[26];
        for (char c : reset.toCharArray()) {
            tmap[c - 'a']++;
        }
        //枚举每张贴纸 的使用情况
        for (int i = 0; i < n; i++) {
            System.out.println("当前剩余字符串：" + reset + "；这个贴纸是否满足的了:" + whetherUse(map, i, tmap));
            //这个方法是为了防止当前贴纸根本满足剩余字符串的需求而导致的OOM
            //例: reset=abcd ,sticker= hhh 直接跳过即可
            if (!whetherUse(map, i, tmap)) {
                continue;
            }

            //枚举目标字符串词频的情况
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                //这里的temp[j]就是 target所需要的字母
                if (tmap[j] > 0) {
                    //为什么这里需要取max值呢 因为当第n个贴子的使用情况已经不满足当前target所需要的
                    //就直接不使用了 这里的主要前提就是 拼接target和顺序无关 只要能拼的出来即可
                    //这里加了点贪心算法 因为就算他剪的时候是负数 其实说明这个贴纸也能使用的了得
                    //但是肯定不是最优解
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            //处理完之后 剩余的字符串样子
            String s = sb.toString();
            int temp = process1(dp, map, s);
            if (temp != -1) {
                //这里判断temp!=-1的情况下说明值是有效的
                // 加上当前的一张贴纸和底下的使用量就是当前这个流程的结果
                ans = Math.min(ans, 1 + temp);
            }
        }
        //这里就是无效值处理 就直接返回-1即可
        dp.put(reset, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(reset);
    }


    //判断第i个贴纸词频映射是否还能满足这个贴纸的使用需求 tmap是这个目标字符串的词频统计数组
    public static boolean whetherUse(int[][] map, int i, int[] tmap) {
        int[] words = map[i];
        for (int j = 0; j < words.length; j++) {
            if (words[j] > 0 && tmap[j] > 0) {
                return true;
            }
        }

        return false;
    }

}
