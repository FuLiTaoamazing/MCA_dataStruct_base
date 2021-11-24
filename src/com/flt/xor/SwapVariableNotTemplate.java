package com.flt.xor;

/**
 * @ClassName: SwapVariableNotTemplate.java
 * @author: Cheems
 * @description:不申请变量交换两个变量的值
 * @createTime: 2021年07月19日 16:10:00
 */
public class SwapVariableNotTemplate {
    public static void main(String[] args) {
        int a = 6;
        int b = 10;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);
    }
}
