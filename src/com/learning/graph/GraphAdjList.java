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
        queue.offer(start);
        start.visited = true;

        V curr = null;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            System.out.println(curr.data);
            for(int adj: curr.nbrs) {
                V adjV = vmap.get(adj);
                if(!adjV.visited) {
                    adjV.visited = true;
                    queue.offer(adjV);
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


    /**
     * Khans Algorithm is a topological sort algorithm that is used to find the topological order of a directed graph.
     * It is a greedy algorithm that finds the shortest path from a starting vertex to all other vertices in the graph.
     * It uses a priority queue to store the vertices and their distances from the starting vertex.
     * 
     * Time complexity is O(Vertices + Edges)
     * Space complexity is O(Vertices)
     * @param s starting vertex
     */
    public void khansAlgorithm() {
        Map<Integer, Integer> inDegree = new HashMap<>();
        for(int key: vmap.keySet()) {
            inDegree.put(key, 0);
        }

        for(int key: vmap.keySet()) {
            for(int nbr: vmap.get(key).nbrs) {
                inDegree.put(nbr, inDegree.get(nbr) + 1);
            }

            Queue<Integer> queue = new ArrayDeque<>();
            for(int inDegreeKey: inDegree.keySet()) {
                if(inDegree.get(inDegreeKey) == 0) {
                    queue.offer(inDegreeKey);
                }
            }
        
            while(!queue.isEmpty()) {
                int curr = queue.poll();
                System.out.println(curr);
                for(int nbr: vmap.get(curr).nbrs) {
                    inDegree.put(nbr, inDegree.get(nbr) - 1);
                    if(inDegree.get(nbr) == 0) {
                        queue.offer(nbr);
                    }
                }
            }
        }
    }

    /**
     * Disjoint Set Union is a data structure that is used to find the connected components of a graph.
     * It is a data structure that supports three operations:
     * makeSet -- Its a an operation to create an set with only one element
     * union -- Its is an operation to merge 2 different sets to one set
     * findSet -- Its an operation to return an identity of a set which is usually an representative of the set
     * Applications:
     * Kruskal's algorithm
     * Finding cycle in a undirected graph
     * 
     * It is like working on tree with different levels of nodes so the time complexity is O(log(Vertices)).
     * But due to path compression and union by rank, the time complexity is O(alpha(Vertices)) ~ O(1) inverse ackermann function
     * 
     * "Amortized" = average cost per operation over MANY operations

        Expensive operation (first find on long chain):
        Pays O(N) once BUT compresses path for ALL future calls

        Like a restaurant:
        First visit: wait 30 mins (setup cost)
        All future visits: instant seating (no wait)

        Total cost for 100 visits = 30 + 99×0 = 30
        Amortized per visit = 30/100 = 0.3 mins ≈ O(1)
     * 
     * Time complexity is O(log(Vertices)) ~ O(alpha(Vertices)) ~ O(1) inverse ackermann function
     * Space complexity is O(Vertices)
     */
    class DSU {
        int[] parent;
        int[] rank;
    
        // Initialize — each node is its own group
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;     // self loop
                rank[i] = 0;
            }
        }
    
        // Find with path compression
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // compress path!
            }
            return parent[x];
        }
    
        // Union by rank
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
    
            if (rootX == rootY) return;  // same group already
    
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    
        // Are x and y in same group?
        boolean connected(int x, int y) {
            return find(x) == find(y);
        }
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

