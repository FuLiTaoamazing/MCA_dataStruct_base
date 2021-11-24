package com.flt.binarysearch;

/**
 * @ClassName: BalancedTreeDemo.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年08月03日 22:32:00
 */
public class BalancedTreeDemo {
    public static void main(String[] args) {

    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;
    }


    public static class Info {
        //是否平衡
        public boolean isBalanced;
        //高度
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalanced(Node x) {
        if (x == null) {
            return true;
        }
        return process(x).isBalanced;
    }

    //找左右子树信息已经结合起来拼成整棵树的信息
    public static Info process(Node x) {
        if (x == null) {
            return new Info(true, 0);
        } else {
            //左子树的信息
            Info leftInfo = process(x.left);
            //右子树的信息
            Info rightInfo = process(x.right);
            //绘制这个节点的信息
            int height = Math.max(leftInfo.height, rightInfo.height);
            boolean isBalanced = true;
            if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
                //这个if语句代表他的左子树或右子树有一个不平衡  或者是他们的高度差大于1
                isBalanced = false;
            }
            return new Info(isBalanced, height);
        }
    }
}
