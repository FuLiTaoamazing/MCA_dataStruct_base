package com.flt.binarysearch;

import javax.xml.soap.Node;
import java.util.List;

/**
 * @ClassName: MaxHappyDemo.java
 * @author: Cheems
 * @description:派对最大快乐值
 * @createTime: 2021年08月05日 13:42:00
 */
public class MaxHappyDemo {


    public static int maxHappy(Employee boos) {
        Info info = process(boos);
        return Math.max(info.no, info.yes);
    }

    public static class Employee {
        //这个节点的快乐值
        public int happy;
        //他的直接下级
        public List<Employee> nexts;

        public Employee(int happy, List<Employee> nexts) {
            this.happy = happy;
            this.nexts = nexts;
        }
    }

    public static class Info {
        //来的时候的最大快乐值
        public int yes;
        //不来的时候的最大快乐值
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }


    public static Info process(Employee x) {
        if (x.nexts.isEmpty()) {
            return new Info(x.happy, 0);
        }
        //先初始化  节点 来的时候happy值是自己
        int yes = x.happy;
        //不来的时候是0
        int no = 0;
        for (Employee next : x.nexts) {
            Info info = process(next);
            //因爲X節點來 他的下屬就不能來
            yes += info.no;
            //X節點不來 就要考慮他的下屬來還是不來求最大值
            no += Math.max(info.no, info.yes);
        }
        return new Info(yes, no);
    }
}
