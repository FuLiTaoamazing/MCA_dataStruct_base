package com.flt.binarysearch;

/**
 * @ClassName: SuccessNodeDemo.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年08月03日 21:47:00
 */
public class SuccessNodeDemo {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;//父亲节点指针
    }

    public static Node getSuccessNode(Node head) {
        if (head == null) {
            return null;
        }
        if (head.right != null) {
            //右孩子不为空的情况找到右孩子最左的节点
            return getLeftMore(head.right);
        } else {
            Node parent = head.parent;
            while (parent != null && parent.left != head) {
                head = parent;
                parent = parent.parent;
            }

            return parent;
        }
    }

    //获取这个节点最左的节点
    public static Node getLeftMore(Node head) {
        if (head == null) {
            return null;
        }
        while (head.left != null) {
            head = head.left;
        }
        return head;
    }
}
