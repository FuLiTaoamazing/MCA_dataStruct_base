package com.flt.binarysearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: IsCBTDemo.java
 * @author: Cheems
 * @description:是否是完全二叉树
 * @createTime: 2021年08月10日 21:21:00
 */
public class IsCBTDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.left = node2;
        node1.right = node3;
//        node3.left = node4;
        System.out.println(isCBT(node1).isCBT);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        //这个是判断是否找到非全节点的变量
        boolean leaf = false;
        Node l = null;
        Node r = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!(queue.isEmpty())) {
            Node node = queue.poll();
            l = node.left;
            r = node.right;
            if (

                //这个条件是判断非全节点 他往后的节点都必须是叶子节点
                    leaf && !(l == null && r == null)
                            //有右节点没左节点就是false
                            || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            //这个就是找到非全节点 左节点可能不为空右节点为空
            //左右节点都为空
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }


    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static Info isCBT(Node head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = isCBT(head.left);
        Info rightInfo = isCBT(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if (isFull) {
            //都是满二叉树的话且高度相等 就代表这个也是二叉树了
            isCBT = true;
        } else {
            //只有两棵树都是完全二叉树才有讨论的必要
            if (leftInfo.isCBT && rightInfo.isCBT) {
                //这个分支代表可能左树比右树高1  左树是不满的 但是比右树高一层
                if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                //这个分支代表左树是满的右树是满的但是 右树比左树矮一层
                if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                //这个代表是左树是满的 但是右树有节点但还不是满的完全二叉树的情况
                if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
            }

        }
        return new Info(isFull, isCBT, height);
    }
}
