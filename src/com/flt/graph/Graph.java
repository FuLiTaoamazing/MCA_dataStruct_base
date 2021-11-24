package com.flt.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName: Graph.java
 * @author: Cheems
 * @description:图的表示
 * @createTime: 2021年08月24日 20:04:00
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    //通过 {{weight,from,to}, }这种数据结构转换成图
    public static Graph createGraphByMatrix(int[][] matrix) {
        Graph graph = new Graph();
        for (int[] ints : matrix) {
            int weight = ints[0];
            int from = ints[1];
            int to = ints[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from, 0, 0));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to, 0, 0));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            graph.edges.add(edge);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            fromNode.out++;
            toNode.in++;
        }
        return graph;

    }
}
