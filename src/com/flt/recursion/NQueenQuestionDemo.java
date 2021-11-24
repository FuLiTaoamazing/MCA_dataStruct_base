package com.flt.recursion;

import javax.crypto.spec.RC2ParameterSpec;

/**
 * @ClassName: NQueenQuestionDemo.java
 * @author: FLT
 * @description:N皇后问题在N*N的棋盘上摆上皇后，要求他们不同行不同列不同斜线请问有多少种解法
 * @createTime: 2021年09月06日 20:59:00
 */
public class NQueenQuestionDemo {

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        System.out.println(nQueenQuestion1(15));
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 + "ms");

        long t3 = System.currentTimeMillis();
        System.out.println(nQueenQuestion2(15));
        long t4 = System.currentTimeMillis();
        System.out.println(t4-t3+"ms");
    }

    //主函数
    public static int nQueenQuestion1(int n) {
        if (n < 1) {
            return 0;
        }
        //用这个数组的下标表示行值表示为列就可以表示皇后摆在了哪里
        int[] record = new int[n];
        return process1(n, 0, record);
    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: n,一共有多少行
     * @param: i,来到了第几行
     * @param: record;记录前面考虑的状态，在这个数组的下标表示行值表示列既可以表示这行的皇后摆在了那里
     * @Return: int
     * @Date: 2021/9/6 21:04
     **/
    public static int process1(int n, int i, int[] record) {
        //base case
        //当i来到了最后一行的位置上表示这个record数组存的状态是有效的
        if (i == n) {
            return 1;
        }
        int res = 0;
        //考虑列的情况
        for (int j = 0; j < n; j++) {
            if (isValid(i, j, record)) {
                //把当前这个列和这个行记录下来
                // 这里的record为什么不需要重置现场呢 因为我们是直接改变record[i]=j的会直接刷掉以前的数据所以不需要重置现场
                record[i] = j;
                //开始考虑下一行的摆放结果
                res += process1(n, i + 1, record);
            }
        }
        return res;

    }

    //判断要放的皇后是否同列同斜线的情况
    public static boolean isValid(int i, int j, int[] record) {
        //k<i表示只考虑i往前的行的情况
        for (int k = 0; k < i; k++) {
            //当 k位置的列等于 j的时候表示同列情况
            //当列的距离等于行的距离的绝对值的时候表示处于同一个斜线情况
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


    //n皇后问题 通过位运算代替数组 加速常数项写法
    public static int nQueenQuestion2(int n) {
        //这里的limit是代表总的限制用limit来代表总的列数
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit,0,0,0);
    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: limit, 总的列数的限制
     * @param: columnLimit, 已经摆了皇后的限制
     * @param: leftLimit,  左对角线限制
     * @param: rightLimit; 又对脚线限制
     * @Return: int
     * @Date: 2021/9/6 21:46
     **/
    public static int process2(int limit, int columnLimit, int leftLimit, int rightLimit) {
        //代表能摆放皇后的列都摆放了
        if (columnLimit == limit) {
            return 1;
        }
        //这里得pos代表能摆放皇后列的位置
        // columnLimit | leftLimit | rightLimit  -->等于总的限制
        // ~(columnLimit | leftLimit | rightLimit) -->等于能摆放皇后的位置 但是左边的不需要的
        //位也变成了1  所以要与上limit去掉左边你的干扰
        int pos = limit & (~(columnLimit | leftLimit | rightLimit));
        //最右边的1
        int mostRightOne = 0;
        //最终的结果
        int res = 0;
        while (pos != 0) {
            //取出pos中最右边的1  然后从这里开始尝试
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            //选择了当前pos最右边的1作为摆放皇后的位置得更新限制
            res += process2(
                    //总的限制还是不变
                    limit,
                    //列的限制与上当前最右边的1 就得到下次要操作列的限制
                    (columnLimit | mostRightOne),
                    //左斜线限制与上当前最右边的1  左移一位 就得到下次要操作列的限制
                    ((leftLimit | mostRightOne) << 1),
                    //右斜线斜线限制与上当前最右边的1 右移一位 就得到下次要操作列的限制
                    ((rightLimit | mostRightOne) >> 1)
            );
        }

        return res;
    }


}
