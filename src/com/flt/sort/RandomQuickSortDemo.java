package com.flt.sort;

import sun.management.VMOptionCompositeData;

import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName: RandomSelect.java
 * @author: Cheems
 * @description:随机快排
 * @createTime: 2021年07月20日 21:25:00
 */
public class RandomQuickSortDemo {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 5, 1, 8, 3};
        quickSort3(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void partition1(int[] arr, int num) {

        int i = 0;
        int minRangeIndex = -1;
        while (i < arr.length) {
            if (arr[i] <= num) {
                swap(arr, (++minRangeIndex), i);
            }
            i++;
        }
    }

    public static void partition2(int[] arr, int num) {
        int i = 0;
        int minRangeIndex = -1;
        int maxRangeIndex = arr.length;
        while (i != maxRangeIndex) {
            if (arr[i] > num) {
                swap(arr, i, maxRangeIndex - 1);
                maxRangeIndex--;
                continue;
            }
            if (arr[i] < num) {
                swap(arr, i, minRangeIndex + 1);
                minRangeIndex++;
            }
            i++;
        }
    }

    //要求在arr[l....r]的范围上将arr[r]作为分组的标记 要求:
    //1、小于arr[r]的在左边
    //2、大于arr[r]的在右边
    //3、相等的在中间
    //结果:返回等于arr[r]中间的数最左边的index以及最右边的index
    //例子:{3,1,2,4,5,3} 求0到arr.leng-1上的荷兰国旗
    //结果:{1,2,3,3,4,5}  返回坐标{2,3}
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;//这里先把arr[r]划在大于区里到最后在进行交换
        int index = l;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        //这里arr[r]还是等于区的数 这时候做个操作 将arr[r]和大于区的第一个数的下标进行交换
        //因为大于区的第一个数紧挨着等于区
        swap(arr, more, r);
        //返回arr[]等于区第一个数的坐标和第二数的坐标
        // 这里的more不用减去1  因为当前more这个下标的值已经和 arr[r]进行交换
        return new int[]{less + 1, more};
    }


    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process1(arr, 0, arr.length - 1);
    }


    public static void process1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = partition(arr, l, r);
        process1(arr, l, m - 1);
        process1(arr, m + 1, r);
    }

    public static int partition(int[] arr, int l, int r) {
        int index = l;
        int less = l - 1;
        int more = r;
        while (index < more) {
            if (arr[index] > arr[r]) {
                swap(arr, index, --more);
            } else {
                swap(arr, index++, ++less);
            }
        }
        swap(arr, more, r);
        return more;
    }

    public static void quickSort2(int[] arr) {
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int[] coords = partition2(arr, l, r);
        process2(arr, l, coords[0] - 1);
        process2(arr, coords[1] + 1, r);
    }

    public static int[] partition2(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int index = l;
        int less = l - 1;
        int more = r;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }


    public static void quickSort3(int[] arr) {
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int randomOffset = (int) (Math.random() * (r - l + 1));
        //加上這區話就能實現 O(N*logN)的效果
        swap(arr, l + randomOffset, r);
        int[] coords = partition3(arr, l, r);
        process3(arr, l, coords[0] - 1);
        process3(arr, coords[1] + 1, r);
    }

    public static int[] partition3(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int index = l;
        int less = l - 1;
        int more = r;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
