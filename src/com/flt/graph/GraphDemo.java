package com.flt.graph;

import java.util.*;

/**
 * @ClassName: GraphDemo.java
 * @author: Cheems
 * @description:图的各种算法
 * @createTime: 2021年08月24日 20:25:00
 */
public class GraphDemo {

    //图的宽度优先便利相比较二叉树的 的多个set
    //因为二叉树不可能有还但是图会有
    public static void Bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new PriorityQueue<>();
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        Node cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.println(cur.value);
            ArrayList<Node> nexts = cur.nexts;
            for (Node next : nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    //图的深度优先遍历和树的先序遍历一样
    //但是 也得加个set表示已经打印过的节点树
    public static void Dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        //这里的Set是用于登记使用过的节点
        Set<Node> set = new HashSet<>();
        //一入栈就打印
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    //因为要做回溯所以得把他的上级节点重新压回栈中
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                }
            }
        }
    }

    //拓扑排序前提条件：这张图是一张有向 无环图
    public static List<Node> sortTopology(Graph graph) {
        //key 是一个节点 , value是这个节点的入度
        Map<Node, Integer> inMapping = new HashMap<>();
        //只有入度为0的节点才能进这个队列
        Queue<Node> zeroQueue = new PriorityQueue<>();
        for (Node node : graph.nodes.values()) {
            //所有节点的入度都映射起来方便操作
            inMapping.put(node, node.in);
            //找到入度为0的节点
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }
        //把结果加在这个result中
        List<Node> result = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            Node cur = zeroQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                //这里就是消除这个入度为零的边影响的点的入度
                inMapping.put(next, inMapping.get(next) - 1);
                if (inMapping.get(next) == 0) {
                    zeroQueue.add(next);
                }
            }
        }

        return result;
    }

    //图的最小生成树所使用的的并查集
    public static class UnionFind {
        public Map<Node, Node> fatherMap;
        public Map<Node, Integer> sizeMap;

        public UnionFind() {
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        //通过图的所有点构建并查集
        public void makeSet(Collection<Node> nodes) {
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node node) {
            if (!fatherMap.containsKey(node)) {
                return null;
            }
            Node cur = node;
            //做路径压缩所使用的的 栈
            Stack<Node> path = new Stack<>();
            while (cur != fatherMap.get(cur)) {
                path.add(cur);
                cur = fatherMap.get(cur);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(Node n1, Node n2) {
            return findFather(n1) == findFather(n2);
        }

        public void union(Node n1, Node n2) {
            if (n1 == null || n2 == null) {
                return;
            }
            Node n1Father = findFather(n1);
            Node n2Father = findFather(n2);
            if (n1Father != n2Father) {
                Node less = sizeMap.get(n1Father) <= sizeMap.get(n2Father) ? n1Father : n2Father;
                Node larger = less == n1Father ? n2Father : n1Father;
                fatherMap.put(less, larger);
                sizeMap.put(larger, sizeMap.get(less) + sizeMap.get(larger));
                sizeMap.remove(less);
            }
        }
    }

    //图最小生成树 的k算法 由边解锁点
    public static Set<Edge> kruskalMST(Graph graph) {
        //构建一个并查集
        UnionFind unionFind = new UnionFind();
        unionFind.makeSet(graph.nodes.values());
        //构建一个小根堆 对土的所有边进行一个排序
        Queue<Edge> queue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        //结果存在这个result中
        Set<Edge> result = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            //当这个边的两个点不连通的话就使用这条边
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }

        return result;
    }

    //p算法 由点解锁边
    public static Set<Edge> primMST(Graph graph) {
        //这个是存储边的小根堆
        Queue<Edge> edgeQueue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        //这个set集合是存着已解锁的点
        Set<Node> nodeSet = new HashSet<>();
        //这个set集合是存着已考虑过的边
        Set<Edge> edgeSet = new HashSet<>();
        //这里就是存着 结果
        Set<Edge> result = new HashSet<>();
        //这个for循环其实就是用于拿一个入口点所使用的的
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {
                //通过点解锁边
                //第一个点不用考虑是否使用过的问题
                for (Edge edge : node.edges) {
                    edgeQueue.add(edge);
                    edgeSet.add(edge);
                }
                //这里 遍历 这条边的to点
                while (!edgeQueue.isEmpty()) {
                    //拿到点的最小边
                    Edge edge = edgeQueue.poll();
                    //他的to 点
                    Node nodeTo = edge.to;
                    if (!nodeSet.contains(nodeTo)) {
                        nodeSet.add(nodeTo);
                        result.add(edge);
                        for (Edge nextEdge : nodeTo.edges) {
                            //没有使用的边才在入这个队列
                            if (!edgeSet.contains(nextEdge)) {
                                edgeSet.add(nextEdge);
                                edgeQueue.add(nextEdge);
                            }
                        }
                    }
                }
            }


            break;
        }

        return result;
    }

    //迪克斯拉算法 求From节点到图中各个节点的最小距离
    public static Map<Node, Integer> dijkstra1(Node from) {
        if (from == null) {
            return null;
        }
        //from节点到各个节点的距离Map
        Map<Node, Integer> distanceMap = new HashMap<>();
        //已经选择 的节点不在做选择
        Set<Node> selectNode = new HashSet<>();
        distanceMap.put(from, 0);
        Node miniNode = selectMinNodeAndUnselect(distanceMap, selectNode);
        while (miniNode != null) {
            int distance = distanceMap.get(miniNode);
            //这里就是当前这个最小的节点能到达的所有节点
            for (Edge edge : miniNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), (distance + edge.weight)));
                }
            }
            selectNode.add(miniNode);
            miniNode = selectMinNodeAndUnselect(distanceMap, selectNode);
        }

        return distanceMap;
    }


    public static Node selectMinNodeAndUnselect(Map<Node, Integer> distanceMap, Set<Node> selectNode) {
        Node miniNode = null;
        for (Node node : distanceMap.keySet()) {
            if (!selectNode.contains(node) && (distanceMap.get(miniNode) == null ? 0 : distanceMap.get(miniNode)) > distanceMap.get(node)) {
                miniNode = node;
            }
        }

        return miniNode;
    }

    public static void main(String[] args) {
        int index=2;
        System.out.println((index-1)/2);
    }
}
