package com.flt.graph;

import java.util.Objects;

/**
 * @ClassName: Edge.java
 * @author: Cheems
 * @description:图中 边的表示
 * @createTime: 2021年08月24日 20:00:00
 */
public class Edge {
    //表示边的权重
    public int weight;
    //从哪个节点出发
    public Node from;
    //到哪个节点去
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight &&
                Objects.equals(from, edge.from) &&
                Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, from, to);
    }
}
