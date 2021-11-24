package com.flt.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PrintAllSubSequences.java
 * @author: Cheems
 * @description:打印一个字符串的全部子串，子串中的每个字符串的相对次序不能颠倒
 * @createTime: 2021年08月31日 20:31:00
 */
public class PrintAllSubSequences {
    public static void main(String[] args) {
        System.out.println(subStr("abc"));
    }

    //递归实现打印字符串所有的子串
    public static List<String> subStr(String str) {
        //字符串的字符数组
        char[] chars = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process1(chars, 0, ans, "");
        return ans;
    }

    //递归实现就是考虑当前位置的字符串字符 使用或者使用
    public static void process1(char[] chars, int index, List<String> ans, String path) {
        //base case
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        //不使用的情况
        String no = path;
        process1(chars, index + 1, ans, no);
        //使用这个字符串的结果
        String yes = path + String.valueOf(chars[index]);
        process1(chars, index + 1, ans, yes);
    }

}
