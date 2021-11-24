package com.flt.queue;

import org.omg.SendingContext.RunTime;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName: QueueDemo.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年07月19日 22:05:00
 */
public class QueueDemo {
    public Queue<Integer> data;
    public Queue<Integer> help;

    public QueueDemo() {
        this.data = new ArrayDeque<>();
        this.help = new ArrayDeque<>();
    }

    public void push(int value) {
        this.data.add(value);
    }

    public void pop() {
        if (data.size() == 0) {
            throw new RuntimeException("当前栈为空");
        }
        for (int i = 0; i < data.size() - 1; i++) {
            help.add(data.poll());
        }
        int value = data.poll();
        Queue<Integer> temp = help;
        help = data;
        data = temp;
    }
}
