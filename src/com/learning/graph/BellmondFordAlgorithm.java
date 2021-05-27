package com.learning.graph;

import java.util.*;

public class BellmondFordAlgorithm<T> {

    /**The time complexity is O(|E| |V|) ~ O(n^2)*/
    public void shortestPathUnweightedGraph(DirectedGraph<T> graph, Vertex<T> start) {
        Queue<Vertex<T>> queue = new ArrayDeque<>();
        Map<T, Integer> distance = new HashMap<>();
        Map<T, Vertex<T>> fromPath = new HashMap<>();
        graph.vertices.forEach(tVertex -> {
            distance.put(tVertex.label, Integer.MAX_VALUE);//Infinity as per algorithm
        });

        queue.offer(start);
        distance.put(start.label, 0);
        fromPath.put(start.label, start);

        while (!queue.isEmpty()){
            Vertex<T> currentVertex = queue.poll();
            currentVertex.edges.forEach(vertex->{
                int currentDistance = distance.get(currentVertex.label)+ vertex.weight;
                if(currentDistance < distance.get(vertex.end.label)){
                    distance.put((T) vertex.end.label, currentDistance);
                    fromPath.put((T) vertex.end.label, vertex.start);
                    queue.offer(vertex.end);
                }
            });
        }

        System.out.println("#Shortest Path from Weighted Graph:\n#Bellmand Ford Algorithm from "+start.label+"\nVertex\tDistance\tFrom Vertex");
        System.out.println("--------------------------------");
        distance.forEach((key, value)->{
            System.out.println(key+"\t\t"
                    +value+"\t\t\t"
                    +fromPath.get(key).label);
        });
    }
}
