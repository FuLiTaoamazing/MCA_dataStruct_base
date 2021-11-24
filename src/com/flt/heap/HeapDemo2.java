package com.flt.heap;

import java.util.*;

/**
 * @ClassName: HeapDemo2.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年07月21日 22:17:00
 */
public class HeapDemo2 {


    public static class MyHeap<T> {
        //因为只有动态数组才能接收 泛型T
        private ArrayList<T> heap;
        //记录每个泛型T处于堆中那个位置
        private Map<T, Integer> indexMap;
        private Integer heapSize;
        //这里的比较器适用于T泛型的对象进行比较
        private Comparator<T> comparator;

        public MyHeap(Comparator<T> comparator) {
            this.comparator = comparator;
            this.heap = new ArrayList<>();
            this.heapSize = 0;
            this.indexMap = new HashMap<>();
        }

        public boolean isEmpty() {
            return this.heapSize == 0;
        }

        public boolean contains(T o) {
            return indexMap.get(o) != null;
        }

        public void push(T o) {
            if (contains(o)) {
                //要是已经存在的话要求用户调用resign方法
                throw new RuntimeException("Object exists the  Heap,Please invoke Resign method");
            }
            this.heap.add(o);
            indexMap.put(o, heapSize);
            heapInsert(heapSize++);


        }

        public T pop() {
            T ans = heap.get(0);
            int end = heapSize - 1;
            heap.remove(end);
            indexMap.remove(ans);
            heapSize--;
            heapIfy(0);
            return ans;
        }


        public void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapIfy(int index) {
            int left = index * 2 + 1;
            int largest = -1;
            while (left < heapSize) {
                largest = (left + 1) < heapSize && comparator.compare(heap.get(left), heap.get(left + 1)) < 0 ? left + 1 : left;
                if (comparator.compare(heap.get(largest), heap.get(index)) > 0) {
                    swap(largest, index);
                }
                index = largest;
                left = index * 2 + 1;
            }
        }

        public void resign(T o) {
            if (!contains(o)) {
                throw new RuntimeException("Object is not exists the heap,please invoke push method");
            }
            Integer ansIndex = indexMap.get(o);
            heapInsert(ansIndex);
            heapIfy(ansIndex);
        }


        //这里的swap比较特别因为得保证 heap和indexMap里的信息同步更新
        public void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            heap.add(j, o1);
            heap.add(i, o2);
            indexMap.put(o1, j);
            indexMap.put(o2, i);

        }
    }
}
