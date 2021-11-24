package com.flt.union;

import java.util.*;

/**
 * @ClassName: UnionDemo.java
 * @author: Cheems
 * @description:并查集实现
 * @createTime: 2021年08月23日 20:57:00
 */
public class UnionDemo {
    public static class Node<T> {
        public T value;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class UnionSet<T> {
        //记录所有样本的 点的对应关系的表
        public Map<T, Node<T>> nodes = new HashMap<>();
        //记录这个点的 父节点的表
        public Map<Node<T>, Node<T>> parents = new HashMap<>();

        //记录头结点一共有多少个节点的表
        public Map<Node<T>, Integer> sizeMapping = new HashMap<>();

        public UnionSet(List<Node<T>> lists) {
            for (Node<T> cur : lists) {
                nodes.put(cur.value, cur);
                //当前节点的头结点就是他自己
                parents.put(cur, cur);
                //记录大小
                sizeMapping.put(cur, 1);
            }
        }

        //通过给定的值找到的父节点
        public Node<T> findFather(Node<T> cur) {
            //这个栈是用于做路径压缩所使用的
            Stack<Node<T>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            //这里就是做路径压缩
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }


        public boolean isSameSet(T value1, T value2) {
            if (!nodes.containsKey(value1) || !nodes.containsKey(value2)) {
                return false;
            }
            return findFather(nodes.get(value1)) == findFather(nodes.get(value2));
        }


        public void union(T value1, T value2) {
            if (!nodes.containsKey(value1) || !nodes.containsKey(value2)) {
                return;
            }
            Node<T> value1Heard = findFather(nodes.get(value1));
            Node<T> value2Heard = findFather(nodes.get(value2));

            if (value1Heard != value2Heard) {
                int valueHeaderSize = sizeMapping.get(value1Heard);
                int valueHeaderSize2 = sizeMapping.get(value2Heard);
                if (valueHeaderSize >= valueHeaderSize2) {
                    parents.put(value2Heard,value1Heard);
                    sizeMapping.put(value1Heard,valueHeaderSize+valueHeaderSize2);
                    sizeMapping.remove(value2Heard);
                } else {
                    parents.put(value1Heard,value2Heard);
                    sizeMapping.put(value2Heard,valueHeaderSize+valueHeaderSize2);
                    sizeMapping.remove(value1Heard);
                }
            }
        }

    }
}
