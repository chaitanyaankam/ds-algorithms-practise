package com.learning.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    public T label;
    public boolean visited;
    public List<DirectedEdge> edges;
    public Integer distanceFromSource;

    public Vertex(T label){
        this.label= label;
        this.visited=false;
        this.edges = new ArrayList<>();
    }
}