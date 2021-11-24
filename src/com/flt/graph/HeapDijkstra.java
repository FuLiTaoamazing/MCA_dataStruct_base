package com.flt.graph;

import java.awt.image.SampleModel;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: HeapDijkstra.java
 * @author: Cheems
 * @description:用堆结构实现迪克斯拉算法
 * @createTime: 2021年08月25日 21:43:00
 */
public class HeapDijkstra {
    //因为系统提供的堆无法实现迪克斯拉算法
    //1、手写一个堆能满足更新某个节点还能更新成堆得结构;
    //2、判断一个Node类型的是否进过堆中
    public static class NodeHeap {
        //实际的堆结构
        public Node[] nodes;
        //节点在堆中位置的映射
        public Map<Node, Integer> heapIndexMap;
        //节点与距离的映射
        public Map<Node, Integer> distanceMap;
        public int size;

        //pop返回所使用的的结构
        public static class NodeRecord {
            public Node node;
            public int distance;

            public NodeRecord(Node node, int distance) {
                this.node = node;
                this.distance = distance;
            }
        }

        public NodeHeap(int size) {
            this.nodes = new Node[size];
            this.heapIndexMap = new HashMap<>();
            this.distanceMap = new HashMap<>();
            this.size = 0;
        }


        public void addOrUpdateOrIgnore(Node node, int distance) {
            //当堆中还存在这个Node的时候就开始考虑是否是最小的路径了
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapIfy(heapIndexMap.get(node));
            }
            //当堆中完全没有进过这个元素的时候就看是进行入堆操作
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapIfy(size++);
            }
        }

        //取出小跟堆堆顶的元素
        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            heapIndexMap.put(nodes[0], -1);
            distanceMap.remove(nodes[0]);
            swap(0, size - 1);
            nodes[size - 1] = null;
            heapIfy(0, --size);
            return nodeRecord;
        }


        private boolean isEmpty() {
            return size == 0;
        }

        //判断节点是否进过堆
        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        //判断节点是否还在堆上
        private boolean inHeap(Node node) {
            return heapIndexMap.containsKey(node) && heapIndexMap.get(node) != -1;
        }

        //从index位置向上重新组织成小根堆结构
        private void insertHeapIfy(int index) {
            //当前节点的 距离小于父节点的距离上移
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //从index的位置向下阻止成 小根堆结构
        private void heapIfy(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                //这里是选出到底是左儿子小还是右儿子小
                int smallest = (left + 1) < size && distanceMap.get(nodes[left]) > distanceMap.get(nodes[left + 1]) ? left + 1 : left;
                //这里是用于判断是否要交换
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = index * 2 + 1;
            }
        }


        //交换两个堆中的位置
        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = temp;
        }

    }


    public static Map<Node, Integer> dijkstra(Node from, int size) {
        NodeHeap heap = new NodeHeap(size);
        heap.addOrUpdateOrIgnore(from, 0);
        Map<Node, Integer> result = new HashMap<>();
        while (!heap.isEmpty()) {
            NodeHeap.NodeRecord record = heap.pop();
            //这里就是弹出来的最小节点
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                //因为这个方法 假如堆中不存在这个节点 就直接入堆,假如堆中存在过这个节点 他会判断这个节点是否是最短距离
                //要是是最短距离就更新
                //要是堆中这个节点已经出去过了就不会再次入堆了
                heap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }

        return result;
    }

}
