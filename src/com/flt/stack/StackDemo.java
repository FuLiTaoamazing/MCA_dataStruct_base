package com.flt.stack;

import java.util.Stack;

/**
 * @ClassName: StackDemo.java
 * @author: Cheems
 * @description:队列的相关算法题
 * @createTime: 2021年07月19日 21:40:00
 */
public class StackDemo {
    public Stack<Integer> push;
    public Stack<Integer> pop;

    public StackDemo() {
        this.push = new Stack<>();
        this.pop = new Stack<>();
    }

    public void push(Integer var1) {
        push.push(var1);
        pushToPop();

    }

    public Integer pop(){
        pushToPop();
        if(pop.isEmpty()){
            throw new RuntimeException("队列为空无法出队");
        }
        return pop.pop();
    }


    public void pushToPop() {
        if (this.pop.isEmpty()) {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
        }
    }
}
