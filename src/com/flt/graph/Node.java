package com.flt.graph;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @ClassName: Node.java
 * @author: Cheems
 * @description:描述图中的点
 * @createTime: 2021年08月24日 19:57:00
 */
public class Node {
    public int value;
    //代表点的直接入度
    public int in;
    //代表点的直接出度
    public int out;
    //代表点的直接连接  即出度的点
    public ArrayList<Node> nexts;
    //从他出发的所有边
    public ArrayList<Edge> edges;

    public Node(int value, int in, int out ) {
        this.value = value;
        this.in = in;
        this.out = out;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value &&
                in == node.in &&
                out == node.out &&
                Objects.equals(nexts, node.nexts) &&
                Objects.equals(edges, node.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, in, out, nexts, edges);
    }
}
