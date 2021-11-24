package com.flt.linkedquestion;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ListCopyDemo.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年07月27日 22:08:00
 */
public class ListCopyWithRandDemo {
    public static class Node {
        public int value;
        public Node rand;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node copyListWithRand1(Node head) {
        //构建一个Hash表
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            //先把原先的Node 和他的拷贝的Node存进map中
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            //再次遍历map 构建他的指针
            //cur老
            //map.get(cur)新
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        //构造一个新链表 1->2->3  ====1->1'->2->2'->3->3'
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur = next;
        }
        //处理新节点的range关系 成对成对的遍历链表
        cur = head;
        next = null;
        Node curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            //当前节点的拷贝节点
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        //然后拆分链表
        Node res = head.next;
        cur = head;
        while (cur != null) {
            //记录下次要分离的一对新老节点
            next = cur.next.next;
            //这个就是拷贝的新节点
            curCopy = cur.next;
            cur.next = next;
            //可能下一对节点到达了null所以要判断一下
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }

        return res;
    }
}
