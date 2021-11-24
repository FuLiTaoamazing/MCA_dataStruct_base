package com.flt.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.StreamSupport;

/**
 * @ClassName: StringFullPermutationDemo.java
 * @author: Cheems
 * @description:给定一个字符串数组，返回他全部拼接的全排列递归回溯算法实现
 * @createTime: 2021年08月19日 22:37:00
 */
public class StringFullPermutationDemo {

    public static void main(String[] args) {
        String[] strs = {"a", "b", "c"};
        HashSet<Integer> use = new HashSet<>();
        ArrayList<String> all = new ArrayList<>();
        stringFullPermutation(strs, use, "", all);
        System.out.println(all);
    }


    //在strs中放着所有要拼接的字符串
    //在use中使用过的字符串的数组下标在use登记
    //之前使用过的字符串拼接成为path
    //用all收集所有拼接结果
    public static void stringFullPermutation(String[] strs, HashSet<Integer> use, String path, ArrayList<String> all) {
        if (strs.length == use.size()) {
            all.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!use.contains(i)) {
                    use.add(i);
                    stringFullPermutation(strs, use, path + strs[i], all);
                    use.remove(i);
                }
            }
        }
    }
}
