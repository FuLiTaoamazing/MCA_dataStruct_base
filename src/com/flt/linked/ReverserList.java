package com.flt.linked;

/**
 * @ClassName: RecoverList.java
 * @author: Cheems
 * @description:单链表以及双链表的反转
 * @createTime: 2021年07月19日 21:25:00
 */
public class ReverserList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode pre;
        public DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    public static Node reverserLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }


    public static Node removeValue(Node head, int value) {
        //这个循环是找到第一个不等于3的位置
        while (head != null) {
            if (head.value == value) {
                break;
            }
            head = head.next;
        }
        //pre指针只是为了记录前一个节点  即不等于3的位置的节点
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == value) {
                //找到等于value的节点
                pre.next = cur.next;
            } else {
                //没有找到等于value的节点就把pre提过来这里
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


}
