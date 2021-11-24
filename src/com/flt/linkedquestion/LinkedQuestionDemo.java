package com.flt.linkedquestion;

import java.nio.file.StandardWatchEventKinds;
import java.util.Stack;

/**
 * @ClassName: LinkedQuestionDemo.java
 * @author: Cheems
 * @description:链表相关面试题
 * @createTime: 2021年07月25日 21:55:00
 */
public class LinkedQuestionDemo {
    public static void main(String[] args) {
//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(3);
//        Node node4 = new Node(3);
//        Node node5 = new Node(2);
//        Node node6 = new Node(1);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        System.out.println(midOrUpMidPreNode(node1));
//        System.out.println(isPalindrome3(node1));
        Node node1 = new Node(3);
        Node node2 = new Node(2);
        Node node3 = new Node(5);
        Node node4 = new Node(1);
        Node node5 = new Node(8);
        Node node6 = new Node(7);
        Node node7 = new Node(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        Node head = sexParameterPartition(node1, 2);
        System.out.println(head);

    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    //奇数个链表的时候返回中点，偶数个链表的时候返回上中点
    public static Node midOrUpMidNode(Node head) {
        //这个if判断隔离了 0,1,2个点的情况
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        //假如fast要跳的下两个节点不为空就进循环
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    //    2、链表为奇数个的时候返回中点，偶数个的时候返回下中点
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node fast = head.next;
        Node slow = head.next;
        while (fast.next != null && head.next.next != null) {
            fast = fast.next.next;
            slow = slow.next.next;
        }

        return slow;
    }


    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null && fast.next.next.next != null) {
            slow = slow.next.next;
            fast = fast.next.next.next;
        }

        return slow;
    }

    //判断一个链表是否是回文结构 使用栈
    public static boolean isPalindrome1(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        Node node = head;
        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(head);
            node = node.next;
        }
        while (head != null && !stack.isEmpty()) {
            Node temp = stack.pop();
            if (head.value != temp.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node cur = slow.next;
        Stack<Node> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    public static boolean isPalindrome3(Node head) {
        boolean res = true;
        while (head == null || head.next == null) {
            return true;
        }
        //找到中点
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //开始反转中点往下的链表
        Node pre = slow;
        Node next = null;
        Node temp = slow.next;
        slow.next = null;
        while (temp != null) {
            //先把下一个元素记录下来以防止调整指正丢失信息
            next = temp.next;
            //记录当前指针
            //交换指针
            temp.next = pre;
            pre = temp;
            temp = next;
        }
        //右指针
        Node right = pre;
        //左指正指针
        Node left = head;
        while (left != null && right != null) {
            if (right.value != left.value) {
                res = false;
                break;
            }
            left = left.next;
            right = right.next;
        }
        //这部就是把链表还原回来
        next = null;
        temp = pre;
        pre = null;
        while (temp != null) {
            next = temp.next;
            temp.next = pre;
            pre = temp;
            temp = next;
        }
        return res;
    }


    public static Node listPartition1(Node head, int provide) {
        if (head == null || head.next == null) {
            return head;
        }
        //第一步记录链表的长度好申请一样长度的数组空间
        int size = 0;
        Node cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        Node[] nodes = new Node[size];
        cur = head;
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        partition(nodes, provide);
        int i;
        for (i = 1; i < nodes.length - 1; i++) {
            nodes[i - 1].next = nodes[i];
        }
        nodes[i - 1].next = null;

        return nodes[0];
    }


    public static void partition(Node[] nodes, int provide) {
        int indexMin = -1;
        int indexMax = nodes.length;
        int index = 0;
        while (index != indexMax) {
            if (nodes[index].value < provide) {
                swap(nodes, ++indexMin, index);
            }
            if (nodes[index].value > provide) {
                swap(nodes, --indexMax, index);
                continue;
            }
            index++;
        }

    }


    public static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //使用六个变量即可完成partition的操作不用多余的容器
    public static Node sexParameterPartition(Node head, int provide) {
        if (head == null || head.next == null) {
            return head;
        }
        //小于区的头指正
        Node sH = null;
        //小于区的头指正
        Node sT = null;
        //等于区的头指正
        Node eH = null;
        //等于区的尾指针
        Node eT = null;
        //大于区的头指针
        Node bH = null;
        //大于区的尾指针
        Node bT = null;

        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < provide) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value > provide) {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            } else {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            }
            head = next;
        }

        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? bH : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : (eH == null ? bH : eH);
    }

}
