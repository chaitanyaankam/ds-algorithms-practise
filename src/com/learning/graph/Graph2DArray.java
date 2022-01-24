package com.learning.graph;

import java.util.PriorityQueue;
import java.util.Queue;

public class Graph2DArray {

    public static void main(String arg[]) {
        //need to reach to end
        int[][] cost = new int[][]{
                {31, 100, 65, 12, 18},
                {10, 13, 47, 157, 6},
                {100, 113, 174, 11, 33},
                {88, 124, 41, 20, 140},
                {99, 32, 111, 41, 20}
        };
    }

    public static void navigate(int[][] costs){
        G graph = new G(costs.length);
        graph.undirected = false;

        for(int i=0;i<costs.length;i++)
            graph.addVertex(i);
        V start = graph.findVertex(0);
        int i=0, j = 0, n = costs.length-1;
        start.d = costs[i][j];

        PriorityQueue<V> pq = new PriorityQueue<>(
                (v1, v2) -> v1.d > v2.d ? -1: (v1.d < v2.d) ? 1 : 0
        );

        pq.offer(start);
        while(!pq.isEmpty()) {
            V curr = pq.poll();
            int currDistance = curr.d;

            for(int k=1; k<=4;k++){
            }
        }
    }
}
