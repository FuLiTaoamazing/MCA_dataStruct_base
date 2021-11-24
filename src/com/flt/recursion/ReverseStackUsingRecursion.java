package com.flt.recursion;

import java.util.Stack;

/**
 * @ClassName: ReverseStackUsingRecursion.java
 * @author: Cheems
 * @description:使用递归逆转栈 不使用新的数据结构
 * @createTime: 2021年08月26日 21:18:00
 */
public class ReverseStackUsingRecursion {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack);
        reverseStack(stack);
        System.out.println(stack);
    }


    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        //拿到最后一个元素
        int i = function(stack);
        reverseStack(stack);
        stack.push(i);
    }


    //这个方法就是用于把栈底元素取出来并返回
    public static int function(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = function(stack);
            stack.push(result);
            return last;
        }
    }
}
