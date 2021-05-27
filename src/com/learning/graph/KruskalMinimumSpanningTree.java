package com.learning.graph;

import com.learning.disjointset.DisjointSet;
import com.learning.disjointset.DisjointSetImpl;
import com.learning.disjointset.Node;

import java.util.*;

/*
 * This algorithm addresses the problem statement for finding
 * minimum spanning tree in a undirected weighted graph
 * DirectedEdge direction is not important for this algorithm
 * */
public class KruskalMinimumSpanningTree<T> {

    /**Time Complexity is O(|E| log(|E|))*/
    public List<DirectedEdge<T>> mst(DirectedGraph<T> graph) {
        List<DirectedEdge<T>> resultEdges = new ArrayList<>();
        DisjointSet<T> disjointSet = new DisjointSetImpl();

        Comparator<DirectedEdge> smallestEdgeFirstComparator =  (DirectedEdge edge1, DirectedEdge edge2) ->
                (edge1.weight==edge2.weight) ? 1 : edge1.weight-edge2.weight;

        // sort edges
        Collections.sort(graph.edges, smallestEdgeFirstComparator);

        // created disjoint sets of vertices
        graph.vertices.forEach((Vertex<T> vertex) -> {
            disjointSet.makeSet(vertex.label);
        });
        graph.edges.forEach(directedEdge->{
            Node<T> startNode = disjointSet.findSet(directedEdge.start.label);
            Node<T> endNode = disjointSet.findSet(directedEdge.end.label);
            if(!startNode.equals(endNode)){
                resultEdges.add(directedEdge);
                disjointSet.unionSet(startNode.data, endNode.data);
            }
        });
        return resultEdges;
    }
}
