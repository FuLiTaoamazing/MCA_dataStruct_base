package com.flt.binarysearch;

/**
 * @ClassName: MaxDistanceDemo.java
 * @author: Cheems
 * @description:求X节点最大的距离
 * @createTime: 2021年08月04日 21:03:00
 */
public class MaxDistanceDemo {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
    }

    public static class Info {
        public int height;
        public int distance;

        public Info(int height, int distance) {
            this.height = height;
            this.distance = distance;
        }
    }

    public static int maxDistance(Node x) {
        Info info = process(x);
        return Math.max(info.height, info.distance);
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        //最大深度 就是不过X节点                    最大高度就是过X节点
        int maxDistance = Math.max(Math.max(leftInfo.distance, rightInfo.distance), leftInfo.height + rightInfo.height + 1);
        return new Info(height, maxDistance);
    }


}
