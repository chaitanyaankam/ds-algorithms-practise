package com.learning.disjointset;

import java.util.HashMap;
import java.util.Map;

public class DisjointSetImpl<T> implements DisjointSet<T> {
    Map<T, Node> map = new HashMap<>();

    @Override
    public void makeSet(T data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    @Override
    public void unionSet(T data1, T data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        if(parent1.data.equals(parent2.data))
            return;
        if(parent1.rank >= parent2.rank) {
            parent2.parent = parent1;
            parent1.rank = (parent1.rank == parent2.rank)? parent1.rank+1 : parent1.rank;
        } else
            parent1.parent = parent2;
    }

    @Override
    public Node findSet(T data) {
        return findSet(map.get(data));
    }

    @Override
    public Node findSet(Node node) {
        if(node.parent == node)
            return node;
        else {
            node.parent = findSet(node.parent); //path compression
            return node.parent;
        }
    }
}
