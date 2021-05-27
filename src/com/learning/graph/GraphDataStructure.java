package com.learning.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SimpleVertex {
    public String label;
    public boolean visited;
    public LinkedList<SimpleVertex> edges;

    public SimpleVertex(String label){
        this.label= label;
        this.visited=false;
        this.edges = new LinkedList<>();
    }
}

class Graph{
    private static int max_vertices = 20; //not used
    private int verticesCount=0;
    public List<SimpleVertex> vertices;

    public Graph(int verticesCount) {
        this.verticesCount=verticesCount;
        vertices = new ArrayList<>(verticesCount);
        IntStream.range(0,verticesCount).forEach(i-> vertices.add(new SimpleVertex(Integer.toString(i))));
    }

    public void addEdge(int start, int end){
        String startLabel = Integer.toString(start), endLabel = Integer.toString(end);
        //verify if vertices are valid O(2 times Vertices) = O(Vertices)
        SimpleVertex startVertex = vertices.stream().filter(vertex -> vertex.label.equals(startLabel)).findFirst().get();
        SimpleVertex endVertex =  vertices.stream().filter(vertex -> vertex.label.equals(endLabel)).findFirst().get();
        //create edge using Linked List associated to each vertex
        startVertex.edges.addFirst(endVertex);
        endVertex.edges.addFirst(startVertex);
    }

    public void print(){
        this.vertices.forEach(vertex -> {
            System.out.println("Vertex: "+vertex.label+" Edges: "+vertex.edges.stream().map(edge->edge.label).collect(Collectors.joining("->")));
        });
    }

    //In BFS traversal a vertex is considered visited if its added to Queue
    //Time complexity is O(Vertices+Edges)
    public void breadth_first_search(){
        Queue<SimpleVertex> queue = new ArrayDeque<>();
        queue.offer(this.vertices.get(0));
        queue.peek().visited=true;
        SimpleVertex vertex_under_visit = null;
        List<String> labels_visited = new ArrayList<>();

        while(!queue.isEmpty()){
            vertex_under_visit = queue.poll();
            vertex_under_visit.edges.stream().filter(vertex -> !vertex.visited)
                    .forEach(vertex -> {
                        vertex.visited=true;
                        queue.offer(vertex);
                    });
            labels_visited.add(vertex_under_visit.label);
        }
        System.out.println("#BFS Traversal "+
                labels_visited.stream().collect(Collectors.joining(", ")));
        this.vertices.forEach(vertex -> vertex.visited=false);
    }

    //In DFS traversal a vertex is considered visited if its added to a stack
    //Time complexity is O(Vertices+Edges)
    public void depth_first_search(){
        SimpleVertex visited_vertex = null;
        Stack<SimpleVertex> stack = new Stack<>();
        List<String> traversal = new ArrayList<>();
        stack.push(this.vertices.get(0));
        visited_vertex = stack.peek();
        visited_vertex.visited=true;
        traversal.add(visited_vertex.label);

        while (!stack.isEmpty()){
            visited_vertex = stack.peek();
            Optional<SimpleVertex> vertex_under_visit = visited_vertex.edges.stream()
                    .filter(v->!v.visited).findFirst();
            //its not a dead block
            if(vertex_under_visit.isPresent()){
                vertex_under_visit.get().visited=true;
                stack.push(vertex_under_visit.get());
                traversal.add(vertex_under_visit.get().label);
            } else //dead-block taking a U-turn, back tracking
                stack.pop();
        }

        System.out.println("#DFS Traversal "+
                traversal.stream().collect(Collectors.joining(", ")));
        this.vertices.forEach(vertex -> vertex.visited=false);
    }

    /**
     * MST:
     * Graph (V, E), Spanning tree (V`,E`)
     * then V`=V and E`=|V|-1
     * Spanning tree shouldn't have a cycle
     * Spanning tree cannot be disconnected
     * A Graph can have more than one spanning tree
     * */
    public void minimumSpanningTree() {

    }

    public void topologicalSorting(){

    }
}

public class GraphDataStructure {
    public static void main(String args[]){
        Graph graph = new Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        graph.addEdge(2, 5);
        graph.addEdge(1, 6);

        graph.print();
        graph.breadth_first_search();
        graph.depth_first_search();
    }
}
