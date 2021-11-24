package com.flt.binarysearch;

import java.util.logging.Level;

/**
 * @ClassName: MaxSizeBinarySearchTreeDemo.java
 * @author: Cheems
 * @description:求一个以X节点为头的树的最大搜索二叉树的头结点
 * @createTime: 2021年08月04日 21:31:00
 */
public class MaxSizeBinarySearchTreeDemo {
    public static void main(String[] args) {
        Node node0=new Node(0);
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node0.left=node3;
        node0.right=node2;
        node2.right=node5;
        node3.left=node1;
        node3.right=node4;
        Node node = maxSizeBinarySearchTree(node0);
        System.out.println();
    }
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        //需要的信息
        //是否是二叉搜索树
        public boolean isAuBst;
        //是二叉搜索数的头结点
        public Node head;
        //最大二叉树的节点数
        public int maxBstSize;
        //左子树最大值
        public int max;
        //右子树最小值
        public int min;

        public Info(boolean isAuBst, Node head, int maxBstSize, int max, int min) {
            this.isAuBst = isAuBst;
            this.head = head;
            this.maxBstSize = maxBstSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Node maxSizeBinarySearchTree(Node x) {
        return process(x).head;

    }


    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        //先把最大值最小值都是当前节点的值
        int max = x.value;
        int min = x.value;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        //默认整棵树的最大二叉树的节点是当前节点
        Node head = x;
        //最大搜索树的个数为0
        int maxAllBst = 0;
        if (leftInfo != null) {
            head = leftInfo.head;
            maxAllBst = leftInfo.maxBstSize;
        }
        if (rightInfo != null) {
            maxAllBst = Math.max(leftInfo!=null?leftInfo.maxBstSize:0, rightInfo.maxBstSize);
           if(leftInfo!=null){
               head = leftInfo.maxBstSize > rightInfo.maxBstSize ? leftInfo.head : rightInfo.head;
           }
        }
        //是否是二叉树
        boolean isAuBst = false;
        //等于 有关X节点条件成立  1、左树整体是二叉树 2、右树整体是二叉树  左子树的最大值小于X  右子树的最小值大于X
        if ((rightInfo == null || rightInfo.isAuBst) && (leftInfo == null || leftInfo.isAuBst) && (leftInfo == null || leftInfo.max < x.value) && (rightInfo == null || rightInfo.min > x.value)) {
            maxAllBst = (leftInfo == null ? 0 : leftInfo.maxBstSize) + (rightInfo == null ? 0 : rightInfo.maxBstSize) + 1;
            head = x;
            isAuBst = true;
        }

        return new Info(isAuBst, head, maxAllBst, max, min);
    }
}
