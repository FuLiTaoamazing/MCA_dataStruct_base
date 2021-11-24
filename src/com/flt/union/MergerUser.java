package com.flt.union;


import java.util.*;

/**
 * @ClassName: MergerUser.java
 * @author: Cheems
 * @description:并查集:假如User有三个字段只要其中对应的一个字段相等都代表是相同的用户
 * @createTime: 2021年08月23日 21:32:00
 */
public class MergerUser {
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

        public UnionSet(List<T> lists) {
            for (T t : lists) {
                Node<T> cur = new Node<>(t);
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
                    parents.put(value2Heard, value1Heard);
                    sizeMapping.put(value1Heard, valueHeaderSize + valueHeaderSize2);
                    sizeMapping.remove(value2Heard);
                } else {
                    parents.put(value1Heard, value2Heard);
                    sizeMapping.put(value2Heard, valueHeaderSize + valueHeaderSize2);
                    sizeMapping.remove(value1Heard);
                }
            }
        }

    }

    public static class User {
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static int mergerUsers(List<User> users) {
        UnionSet<User> unionSet = new UnionSet<>(users);
        //这三个字段就是用于来将这两个字段相同的对象通过并查集联通在一起的
        //1、假如这个字段的值已经存在这三个map中，就取出这个map中这个值的user和
        //当前这个对象进行并查集union操作最后返回 sizeMapping的大小就知道有多少人了
        Map<String, User> mapA = new HashMap<>();
        Map<String, User> mapB = new HashMap<>();
        Map<String, User> mapC = new HashMap<>();
        for (User user : users) {
            if (mapA.containsKey(user.a)) {
                unionSet.union(mapA.get(user.a), user);
            } else {
                mapA.put(user.a, user);
            }
            if (mapB.containsKey(user.b)) {
                unionSet.union(mapB.get(user.b), user);
            } else {
                mapB.put(user.b, user);
            }
            if (mapC.containsKey(user.c)) {
                unionSet.union(mapC.get(user.c), user);
            } else {
                mapC.put(user.c, user);
            }
        }
        return unionSet.sizeMapping.size();
    }


}
