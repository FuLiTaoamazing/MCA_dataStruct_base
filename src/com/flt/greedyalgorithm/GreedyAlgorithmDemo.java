package com.flt.greedyalgorithm;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: GreedyAlgorithmDemo.java
 * @author: Cheems
 * @description:贪心算法的题目Demo
 * @createTime: 2021年08月19日 22:55:00
 */
public class GreedyAlgorithmDemo {
    public static void main(String[] args) throws ParseException {
        String str = "X..X.X....";
        System.out.println(lightMini1(str, new HashSet<>(), 0));
        System.out.println(lightMini2(str));
        int[] arr = {10, 20, 50};
        System.out.println(lessMoney(arr));
    }

    //会议问题
    public static class Program {
        //会议开始时间
        public long start;
        //会议结束时间
        public long end;

        public Program(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

    //使用暴力穷举法来解这道题
    public static int bestArrProgram(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }

        return process(programs, 0, 0);
    }

    //还剩多少会议都放在 programs里
    //done代表已经安排了多少会议
    //timeLimit代表了时间来到了哪里
    //返回已经安排了多少的会议
    //这个递归回溯算法为什么不需要清理现场呢 因为他每次删除会议的时候都生成了一个新的数组
    //不会影响到以前还未使用的数据
    public static int process(Program[] programs, int done, long timeLimit) {
        if (programs.length == 0) {
            return done;
        }
        int max = done;
        //这里的for循环就是用于考虑所有会议的安排情况
        for (int i = 0; i < programs.length; i++) {
            //会议的开始时间晚于这个时间节点说明他就是可以安排的
            if (programs[i].start > timeLimit) {
                //把找到的会议拿出来 代表这个会议已经安排了nextSelects就是剩下的下次要
                //安排会议集合
                Program[] nextSelects = copyButExcept(programs, i);
                //这里代表这个会议已经安排了所以取done+1,再把当前会议的结束时间作为timeLimit继续下去找安排的可能性
                max = Math.max(process(nextSelects, done + 1, programs[i].end), max);
            }
        }

        return max;
    }

    //删除i节点的会议
    public static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
    }

    //会议问题的贪心解法
    //只要按结束时间来进行排序 在遍历数组求能安排的会议数就是最大的会议数
    public static int greedyProgram(Program[] programs) {
        long timeLimit = 0;
        int count = 0;
        Arrays.sort(programs, (o1, o2) -> {
            return (int) (o1.end - o2.end);
        });
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLimit) {
                timeLimit = programs[i].end;
                count++;
            }
        }
        return count;
    }

    //放灯解法
    //暴力递归法

    public static int lightMini1(String str, HashSet<Integer> lights, int index) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process1(str, 0, lights);
    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: str, 放灯的字符串
     * @param: lights,已经放过灯的下表
     * @param: index;下标来到了哪个位置
     * @Return: int
     * @Date: 2021/8/19 23:37
     **/
    public static int process1(String str, int index, HashSet<Integer> lights) {
        if (index == str.length()) {
            //Base Case 当index来到了str的limit这结束这个递归
            //这个for循环是来验证这次的递归所生成的结果是否正确
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '.') {
                    if (!(lights.contains(i - 1)) && !(lights.contains(i)) && !(lights.contains(i + 1))) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            //返回灯数
            return lights.size();
        } else {
            int yes = Integer.MAX_VALUE;
            int no = process1(str, index + 1, lights);
            //当他是点的话才考虑放灯还是不放灯
            if (str.charAt(index) == '.') {
                lights.add(index);
                yes = process1(str, index + 1, lights);
                lights.remove(index);
            }

            return Math.min(yes, no);
        }
    }


    //贪心算法解路灯

    public static int lightMini2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process2(str.toCharArray());
    }


    public static int process2(char[] str) {
        int lights = 0;
        int index = 0;
        while (index < str.length) {
            if (str[index] == 'X') {
                index++;
            } else {
                lights++;
                if (index + 1 == str.length) {
                    break;
                } else {
                    if (str[index + 1] == 'X') {
                        index = index + 2;
                    } else {
                        index = index + 3;
                    }
                }

            }
        }

        return lights;
    }

    //分金快问题，arr就是要分的金块大小结果，result就是所花费的金额
    //利用小根堆构建 哈夫曼树即可
    public static int lessMoney(int[] arr) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            //构建小根堆
            queue.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }

        return sum;
    }


    //最大项目问题
    public static class Project {
        //本金
        public int cost;
        //收益
        public int profits;

        public Project(int cost, int profits) {
            this.cost = cost;
            this.profits = profits;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Project project = (Project) o;
            return cost == project.cost &&
                    profits == project.profits;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cost, profits);
        }
    }


    public static int maxMunProfits(Project[] projects, int k, int m) {
        Queue<Project> minCostQueue = new PriorityQueue<>(new MinCostComparator());

        Queue<Project> maxProfitsQueue = new PriorityQueue<>(new MaxProfitsComparator());

        minCostQueue.addAll(Arrays.asList(projects));
        for (int i = 0; i < k; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost < m) {
                maxProfitsQueue.add(minCostQueue.poll());
            }
            if (maxProfitsQueue.isEmpty()) {
                return m;
            }
            m += maxProfitsQueue.poll().profits;
        }
        return m;
    }

    //最小成本比较器
    public static class MinCostComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }
    }

    //最大收益比较器
    public static class MaxProfitsComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profits - o1.profits;
        }
    }

}
