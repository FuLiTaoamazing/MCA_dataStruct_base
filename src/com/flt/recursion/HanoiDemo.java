package com.flt.recursion;

/**
 * @ClassName: HanoiDemo.java
 * @author: Cheems
 * @description:汉诺塔问题:汉诺塔问题假如最左边的盘子有N个小盘如何全部移动到右边去最简小盘子只能压到大盘子上 n层汉诺塔最小的移动距离就是2的n次方-1
 * @createTime: 2021年08月26日 21:01:00
 */
public class HanoiDemo {
    public static void main(String[] args) {
        hanoi(3);
    }


    public static void hanoi(int n) {
        process(n, "left", "right", "mid");
    }


    public static void process(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            process(n - 1, from, other, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            process(n - 1, other, to, from);
        }

    }
}
