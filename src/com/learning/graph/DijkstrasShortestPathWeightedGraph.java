package com.learning.graph;

import java.util.*;

public class DijkstrasShortestPathWeightedGraph<T extends Comparable> {

    /** Time Complexity is O(|E| log(|V|))*/
    public void shortestPathWeightedGraph(DirectedGraph<T> directedWeightedGraph, Vertex<T> start) {
        Queue<Vertex<T>> heap = new PriorityQueue<Vertex<T>>((o1, o2) -> o1.distanceFromSource.compareTo(o2.distanceFromSource));
        Map<T, Integer> distance = new HashMap<>();
        Map<T, Vertex<T>> fromPath = new HashMap<>();

        directedWeightedGraph.vertices.stream().forEach(tVertex -> {
            distance.put(tVertex.label, -1);
        });

        start.distanceFromSource = 0;
        heap.offer(start);
        distance.put(start.label, 0);
        fromPath.put(start.label, start);

        while (!heap.isEmpty()){
            Vertex<T> vertex = heap.poll();
            vertex.edges.forEach(directedEdge -> {
                int currentDistance = distance.get(directedEdge.start.label)+directedEdge.weight;
                if(distance.get(directedEdge.end.label)==-1) {
                    distance.put((T) directedEdge.end.label, currentDistance);
                    fromPath.put((T) directedEdge.end.label, directedEdge.start);
                    directedEdge.end.distanceFromSource = currentDistance;
                    heap.offer(directedEdge.end);
                } else if(currentDistance < distance.get(directedEdge.end.label)) {
                    distance.put((T) directedEdge.end.label, currentDistance);
                    fromPath.put((T) directedEdge.end.label, directedEdge.start);
                    //modifying the priority of the vertex in heap
                    //This can be done only by removing from heap and adding new vertex in heap
                    /*Optional<Vertex<T>> vertexFromHeap = heap.stream()
                            .filter(avertex-> (avertex.label.equals(directedEdge.end.label) || avertex.label==directedEdge.end.label))
                            .findAny();
                    if(vertexFromHeap.isPresent())
                        heap.remove(vertexFromHeap);*/
                    directedEdge.end.distanceFromSource = currentDistance;
                    heap.offer(directedEdge.end);
                }
            });
        }

        System.out.println("#Dijkastra's Shortest Path from "+start.label+"\nVertex\tDistance\tFrom Vertex");
        System.out.println("--------------------------------");
        distance.forEach((key, value)->{
            System.out.println(key+"\t\t"
                    +value+"\t\t\t"
                    +(fromPath.get(key)!=null? fromPath.get(key).label: "NO EDGE"));
        });
    }
}
