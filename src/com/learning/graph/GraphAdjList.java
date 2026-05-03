package com.learning.graph;

import java.util.*;

class G {
    int v;
    Map<Integer, V> vmap;
    Map<String, Integer> weights;
    boolean undirected = false;
    private static final String SEPARATOR = "->";

    public G(int v){
        this.v = v;
        vmap = new HashMap<>();
        weights = new HashMap<>();
    }

    public void addVertex(int data){
        V newV = new V();
        newV.data = data;
        newV.nbrs = new ArrayList<>();
        vmap.put(data, newV);
    }

    public V findVertex(int data) {
        return vmap.get(data);
    }

    public void addEdge(int s, int e) {
        V start = vmap.get(s);
        start.nbrs.add(e);
        if(undirected) {
            V end = vmap.get(e);
            end.nbrs.add(s);
        }
    }

    public void addEdges(int s, int e, int weight) {
        V start = vmap.get(s);
        start.nbrs.add(e);
        weights.put(s+ SEPARATOR +e, weight);

        if(undirected){
            V end = vmap.get(e);
            end.nbrs.add(s);
            weights.put(e+ SEPARATOR +s, weight);
        }
    }

    public void bfs(int s) {
        V start = vmap.get(s);
        start.d = 0;
        Queue<V> queue = new ArrayDeque<>();
        Map<Integer, V> parents = new HashMap<>();
        queue.offer(start);

        V curr = null;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            System.out.println(curr.data);
            for(int adj: curr.nbrs) {
                V adjV = vmap.get(adj);
                if(!adjV.visited) {
                    queue.offer(adjV);
                    adjV.visited = true;
                }
            }
        }
    }

    public void dfs(V s) {
        s.visited = true;
        System.out.println(s.data);
        for(int i: s.nbrs) {
            V curr = vmap.get(i);
            if(!curr.visited) {
                dfs(curr);
            }
        }
    }

    public void dfs(int s){
        V start = vmap.get(s);
        dfs(start);
    }

    public boolean hasCycle() {
        return false;
    }

    /**
     * The Shortest Path in an Undirected Graph uses BFS to find the shortest path.
     * The first time it visits a vertex, it is always the shortest path to that vertex.
     * because the vertex are at equal distances (unweighted graph).
     * 
     * Time Complexity is O(Vertices + Edges)
     * Space Complexity is O(Vertices)
     * @param s
     */
    public void shortestPathUndirected(int s) {
        V start = vmap.get(s);
        start.d = 0;
        start.visited = true;

        Map<Integer, V> parents = new HashMap<>();
        Queue<V> queue = new ArrayDeque<>();
        queue.offer(start);

        V curr = null;
        while(!queue.isEmpty()) {
            curr = queue.poll();
            for(int i: curr.nbrs) {
                V adj = vmap.get(i);
                if(!adj.visited) {
                    adj.d = curr.d + 1;
                    parents.put(adj.data, curr);
                    adj.visited = true;
                    queue.offer(adj);
                }
            }
        }

        V end = curr;
        while(curr != null){
            System.out.print(curr.data+"("+curr.d+")<-");
            curr = parents.get(curr.data);
        }

        System.out.println("\nend "+end.data+" "+end.d);
    }

    // shortest path in D A G
    /**
     * The Shortest Path in a Directed Graph uses Dijkstra's Algorithm.
     * It is a greedy algorithm that finds the shortest path from a starting vertex to all other vertices in the graph.
     * It uses a priority queue to store the vertices and their distances from the starting vertex.
     * 
     * Time complexity is O(Edges log(V))
     * Space complexity is O(Vertices)
     * @param s starting vertex
     */
    public void dijikstras(int s){
        V start = vmap.get(s);
        Map<Integer, V> parents = new HashMap<>();
        //PriorityQueue<V> mh = new PriorityQueue<V>(
        //        (v1, v2) -> v1.d > v2.d ? -1 : ((v1.d < v2.d) ? 1 : 0 )
        //);
        PriorityQueue<V> mh = new PriorityQueue<V>((v1, v2) -> v1.d - v2.d);
        V curr = null;
        mh.add(start);
        while(!mh.isEmpty()) {
            curr = mh.poll();
            if(curr.visited) 
                continue;

            int currDistance = curr.d;
            for (int adj: curr.nbrs){
                V adjV = vmap.get(adj);
                int newDistance = currDistance + weights.get(curr.data+ SEPARATOR + adj);

                if(!adjV.visited){
                    adjV.d = newDistance;
                    mh.add(adjV);
                    parents.put(adj, curr);
                    adjV.visited = true;
                } else if (adjV.d > newDistance) {
                    //mh.remove(adjV);
                    adjV.d = newDistance;
                    mh.offer(adjV);
                    parents.put(adj, curr);
                }
            }
        }

        V end = curr;
        while(curr != null){
            System.out.print(curr.data+"("+curr.d+")<-");
            curr = parents.get(curr.data);
        }

        System.out.println("start\nShortest Path "+end.d);

    }
}
class V {
    int data = 0;
    int d = 0;
    List<Integer> nbrs;
    boolean visited = false;
}

public class GraphAdjList {

    public static void main(String arg[]) {
        G graph = new G(5);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdges(1,2,4);
        graph.addEdges(1,3,1);
        graph.addEdges(2,5,4);
        graph.addEdges(3,2,2);
        graph.addEdges(3,4,4);
        graph.addEdges(4,5,4);

        //graph.dijikstras(1);

        graph.undirected = true;
        //graph.bfs(1);
        //graph.dfs(1);

        G graph2 = new G(6);
        graph2.addVertex(0);
        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addVertex(3);
        graph2.addVertex(4);
        graph2.addVertex(5);
        graph2.addVertex(6);

        graph2.addEdge(1,2);
        graph2.addEdge(1,0);
        graph2.addEdge(0,4);
        graph2.addEdge(2,3);
        graph2.addEdge(3,4);
        graph2.addEdge(3,5);
        graph2.addEdge(4,5);
        graph2.addEdge(5,6);

        graph2.shortestPathUndirected(1);
    }
}

