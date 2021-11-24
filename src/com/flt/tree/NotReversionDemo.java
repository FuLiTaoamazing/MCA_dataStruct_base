package com.flt.tree;

import java.util.Stack;

/**
 * @ClassName: NotReversionDemo.java
 * @author: Cheems
 * @description:不使用递归遍历树
 * @createTime: 2021年08月03日 08:54:00
 */
public class NotReversionDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.left = node2;
        node1.right = node3;
        pre(node1);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //不使用递归的先序遍历
    public static void pre(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.value);
            if (root.right != null) {
                root = root.right;
            }

        }
    }
}
