package com.flt.recursion;

/**
 * @ClassName: CardsInLine.java
 * @author: FLT
 * @description: 给定一个数组arr，代表数值不同的牌排成一条线，
 * 玩家A和玩家B依次拿走每张牌，规定玩家A先拿，玩家B后拿，
 * 但是每个玩家只能拿走最左或最右的牌，
 * 玩家A和玩家B都聪明绝顶，请返回最后获胜者的分数
 * @createTime: 2021年08月31日 22:18:00
 */
public class CardsInLine {
    public static void main(String[] args) {
        System.out.println(win(new int[]{1, 3, 9,7,100}));
        System.out.println(dpWay(new int[]{1, 3, 9,7,100}));
    }

    public static int win(int[] arr) {

        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }


    //先手函数
    //baseCase在最后一张牌 的情况下直接拿走这个牌
    //在l~r范围上拿到对自己最好 以及对对手最差的结果
    public static int first(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        } else {
            //后手函数就是在l 或 r这里拿到最好的结果后 在加上他后面的后手结果
            return Math.max((arr[l] + second(arr, l + 1, r)), (arr[r] + second(arr, l, r - 1)));
        }
    }

    //后手函数
    //base case当l==r代表剩最后一张牌的时候拿不了只能return0
    //在l+1~r 或 l~r-1范围上拿到最好的结果 但是：
    // 因为你这个范围是对手给你的他会给你在这个范围上对你收益最小的
    public static int second(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        } else {
            //为啥这里去吊先手函数呢
            //因为 虽然范围是对手给你的 但是你也得在这个范围上拿到最好的结果所以就去调先手函数
            return Math.min(first(arr, l + 1, r), first(arr, l, r - 1));
        }
    }


    public static int dpWay(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int n = arr.length;
        //构建两张dp表分别是 first[][] 和second[][]
        int[][] first = new int[n][n];
        int[][] second = new int[n][n];
        //进行初始化操作
        // 初始化first
        for (int i = 0; i < n; i++) {
            first[i][i] = arr[i];
        }
        //初始化second 因为java的特性所以second这个对角线上都是0所以不需要初始化

        //开始进行dp操作

        //这个while循环还包含了个隐含条件 l<n
        for (int i = 1; i < n; i++) {
            int r = i;
            int l = 0;
            while (l < n && r < n) {
                first[l][r] =  Math.max(arr[l] +second[l + 1][r], arr[r] + second[l][r - 1]);
                second[l][r] = Math.min(first[l + 1][r], first[l][r - 1]);
                l++;
                r++;
            }
        }

        return Math.max(first[0][n - 1], second[0][n - 1]);
    }
}
