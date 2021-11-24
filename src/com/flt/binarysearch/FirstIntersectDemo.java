package com.flt.binarysearch;

import java.util.*;

/**
 * @ClassName: FirstIntersectDemo.java
 * @author: Cheems
 * @description:给定一颗树的头结点head，和另外两个节点a和b返回a和b的最低公共祖先
 * @createTime: 2021年08月10日 22:00:00
 */
public class FirstIntersectDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.left = node2;
        node1.right = node3;
        Node o = firstIntersect1(node1, node2, node3);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value &&
                    Objects.equals(left, node.left) &&
                    Objects.equals(right, node.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, left, right);
        }
    }

    public static Node firstIntersect1(Node head, Node o1, Node o2) {
        if (head == null || o1 == null || o2 == null) {
            return null;
        }
        //构建父节点的Mapping  key是当前节点 value是当前节点的父节点
        Map<Node, Node> parentMapping = new HashMap<>();
        //根节点的父节点为null;
        parentMapping.put(head, null);
        //构建parentMapping 每个节点
        processMapping(head, parentMapping);
        Set<Node> o1Set = new HashSet<>();
        Node cur = o1;
        //这个遍历就处理好o1节点的所有父节点了
        while (parentMapping.get(cur) != null) {
            cur = parentMapping.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMapping.get(cur);
        }
        return cur;
    }


    public static void processMapping(Node head, Map<Node, Node> parentMapping) {
        if (head.left != null) {
            parentMapping.put(head.left, head);
            processMapping(head.left, parentMapping);
        }
        if (head.right != null) {
            parentMapping.put(head.right, head);
            processMapping(head.right, parentMapping);
        }

    }
}
