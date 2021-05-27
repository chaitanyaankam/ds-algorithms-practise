package com.learning.graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class ShortestPathUnweightedGraph<T> {

    /**
     * Finding shortest Path in a Directed Unweighted Graph
     * Time Complexity is O(|E|+|V|)
     * @Param graph, start vertex
     * */
    public void shortestPath(DirectedGraph<T> graph, Vertex<T> start) {
        Map<T, Integer> distance = new HashMap<>();
        Map<T, Vertex<T>> fromPath = new HashMap<>();
        graph.vertices.forEach(tVertex -> {
            distance.put(tVertex.label, -1);
        });
        Queue<Vertex<T>> queue = new ArrayDeque<>();
        queue.offer(start);
        distance.put(start.label, 0);
        fromPath.put(start.label, start);

        while(!queue.isEmpty()){
            Vertex<T> vertex = queue.poll();
            vertex.edges.forEach(directedEdge -> {
                if(distance.get(directedEdge.end.label)==-1) {
                    distance.put((T) directedEdge.end.label, distance.get(directedEdge.start.label) + 1);
                    fromPath.put((T) directedEdge.end.label, directedEdge.start);
                    queue.offer(directedEdge.end);
                }
            });
        }

        System.out.println("#Shortest Path from "+start.label+"\nVertex\tDistance\tFrom Vertex");
        System.out.println("--------------------------------");
        distance.forEach((key, value)->{
            System.out.println(key+"\t\t"
                    +value+"\t\t\t"
                    +fromPath.get(key).label);
        });
    }


}
