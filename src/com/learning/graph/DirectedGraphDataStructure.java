package com.learning.graph;

import java.util.*;
import java.util.stream.Collectors;

class DirectedGraph<T> {
    public List<Vertex<T>> vertices;
    public List<DirectedEdge<T>> edges;

    public DirectedGraph(List<T> vertices) {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        vertices.stream().forEach(vertex->this.vertices.add(new Vertex(vertex)));
    }

    public void addEdge(T start, T end, int weight){
        //verify if vertices are valid O(2 times Vertices) = O(Vertices)
        Vertex startVertex = vertices.stream().filter(vertex -> vertex.label.equals(start)).findFirst().get();
        Vertex endVertex =  vertices.stream().filter(vertex -> vertex.label.equals(end)).findFirst().get();
        //create a new Edge and add it to the List
        DirectedEdge<T> edge = new DirectedEdge<>();
        edge.start=startVertex;
        edge.end=endVertex;
        edge.weight=weight;
        startVertex.edges.add(edge);

        DirectedEdge<T> edge02 = new DirectedEdge<>();
        edge02.end=startVertex;
        edge02.start=endVertex;
        edge02.weight=weight;
        endVertex.edges.add(edge02);

        edges.add(edge);
    }

    public void addEdge(T start, T end, int weight, boolean direction){
        //verify if vertices are valid O(2 times Vertices) = O(Vertices)
        Vertex startVertex = vertices.stream().filter(vertex -> vertex.label.equals(start)).findFirst().get();
        Vertex endVertex =  vertices.stream().filter(vertex -> vertex.label.equals(end)).findFirst().get();
        //create a new Edge and add it to the List
        DirectedEdge<T> edge = new DirectedEdge<>();
        edge.start=startVertex;
        edge.end=endVertex;
        edge.weight=weight;
        startVertex.edges.add(edge);

        if(!direction) {
            DirectedEdge<T> edge02 = new DirectedEdge<>();
            edge02.end=startVertex;
            edge02.start=endVertex;
            edge02.weight=weight;
            endVertex.edges.add(edge02);
        }

        edges.add(edge);
    }

    //In BFS traversal a vertex is considered visited if its added to Queue
    //Time complexity is O(Vertices+Edges)
    public void breadth_first_search(){
        Queue<Vertex<T>> queue = new ArrayDeque<>();
        queue.offer(this.vertices.get(0));
        queue.peek().visited=true;
        Vertex<T> vertex_under_visit = null;
        List<T> labels_visited = new ArrayList<>();

        while(!queue.isEmpty()){
            vertex_under_visit = queue.poll();
            vertex_under_visit.edges.stream().filter(directedEdge -> !directedEdge.end.visited)
                    .forEach(directedEdge -> {
                        directedEdge.end.visited=true;
                        queue.offer(directedEdge.end);
                    });
            labels_visited.add(vertex_under_visit.label);
        }
        System.out.println("#BFS Traversal "+
                labels_visited.stream().map(label->label+" ").collect(Collectors.joining(", ")));
        this.vertices.forEach(vertex -> vertex.visited=false);
    }


    public void print(){
        this.vertices.forEach(vertex ->
            System.out.println("Vertex: "+vertex.label+" Edges: "+vertex.edges.stream()
                    .map(edge->edge.end.label+" ("+edge.weight+") ")
                    .collect(Collectors.joining("->")))
        );
    }

    public void printAllEdges(){
        this.edges.forEach(directedEdge->
            System.out.println("Edge "+directedEdge.start.label+"->"+directedEdge.end.label+" distance "+directedEdge.weight)
        );
    }

}

public class DirectedGraphDataStructure {
    public static void main(String args[]){
       /*
       Comparator<DirectedEdge> smallestEdgeFirstComparator =  (DirectedEdge edge1, DirectedEdge edge2) ->
                (edge1.weight==edge2.weight) ? 1 : edge1.weight-edge2.weight;
        List<Integer> vertices = IntStream.range(0, 7).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        DirectedGraph graph = new DirectedGraph(vertices);
        graph.addEdge(0, 1, 8);
        graph.addEdge(0, 3, 2);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 11);
        graph.addEdge(3, 4, 5);
        graph.addEdge(4, 2, 6);
        graph.addEdge(2, 5, 7);
        graph.addEdge(1, 6, 10);

        graph.print();
        graph.printAllEdges();
        graph.breadth_first_search();


        KruskalMinimumSpanningTree<Integer> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree();
        List<DirectedEdge<Integer>> mstEdges = kruskalMinimumSpanningTree.mst(graph);
        System.out.println("#Kruskal's Algorithm Result");
        mstEdges.forEach(integerDirectedEdge -> System.out.println(integerDirectedEdge.start.label+" "+integerDirectedEdge.end.label));
        */

        /*List<String> cities = Arrays.asList(new String[]{"HYD", "BANG", "DHL", "CHN", "TRV", "MUM","KSH"});
        DirectedGraph<String> citiesGraph = new DirectedGraph(cities);
        citiesGraph.addEdge("HYD", "BANG", 30);
        citiesGraph.addEdge("BANG", "CHN", 40);
        citiesGraph.addEdge("TRV", "CHN", 65);
        citiesGraph.addEdge("HYD", "TRV", 70);
        citiesGraph.addEdge("DHL", "HYD", 120);
        citiesGraph.addEdge("DHL", "MUM", 80);
        citiesGraph.addEdge("MUM", "HYD", 20);
        citiesGraph.addEdge("DHL", "TRV", 150);
        citiesGraph.addEdge("KSH", "DHL", 60);
        citiesGraph.addEdge("DHL", "MUM", 210);

        System.out.println("## Path problem");
        citiesGraph.print();
        citiesGraph.breadth_first_search();
        citiesGraph.printAllEdges();

        KruskalMinimumSpanningTree<String> kruskalMinimumSpanningTree02 = new KruskalMinimumSpanningTree();
        List<DirectedEdge<String>> mstPath = kruskalMinimumSpanningTree02.mst(citiesGraph);
        mstPath.forEach(integerDirectedEdge -> System.out.println(integerDirectedEdge.start.label+" "+integerDirectedEdge.end.label));*/

        /*List<Character> unWeightedGraphVertices = new ArrayList<>();
        unWeightedGraphVertices.add('A');
        unWeightedGraphVertices.add('B');
        unWeightedGraphVertices.add('C');
        unWeightedGraphVertices.add('D');
        unWeightedGraphVertices.add('E');
        unWeightedGraphVertices.add('F');
        unWeightedGraphVertices.add('G');

        DirectedGraph unWeightedDirectedGraph = new DirectedGraph(unWeightedGraphVertices);
        unWeightedDirectedGraph.addEdge('C', 'A', 1);
        unWeightedDirectedGraph.addEdge('C', 'F', 1);
        unWeightedDirectedGraph.addEdge('A', 'D', 1);
        unWeightedDirectedGraph.addEdge('D', 'F', 1);
        unWeightedDirectedGraph.addEdge('A', 'B', 1);
        unWeightedDirectedGraph.addEdge('B', 'D', 1);
        unWeightedDirectedGraph.addEdge('B', 'E', 1);
        unWeightedDirectedGraph.addEdge('E', 'G', 1);
        unWeightedDirectedGraph.addEdge('D', 'G', 1);

        Vertex<Character> start = (Vertex<Character>) unWeightedDirectedGraph.vertices.get(2);
        ShortestPathUnweightedGraph<Character> shortestPathUnweightedGraph = new ShortestPathUnweightedGraph<>();
        shortestPathUnweightedGraph.shortestPath(unWeightedDirectedGraph, start);*/

       /* List<String> weightedDirectedGraphVertices = new ArrayList<>();
        weightedDirectedGraphVertices.add("A");
        weightedDirectedGraphVertices.add("B");
        weightedDirectedGraphVertices.add("C");
        weightedDirectedGraphVertices.add("D");
        weightedDirectedGraphVertices.add("E");
        weightedDirectedGraphVertices.add("F");
        weightedDirectedGraphVertices.add("G");
        weightedDirectedGraphVertices.add("H");

        DirectedGraph<String> weightedDirectedGraph = new DirectedGraph<>(weightedDirectedGraphVertices);
        weightedDirectedGraph.addEdge("A", "B", 4, true);
        weightedDirectedGraph.addEdge("A", "C", 1, true);
        weightedDirectedGraph.addEdge("A", "D", 1, true);
        weightedDirectedGraph.addEdge("C", "B", 2, true);
        weightedDirectedGraph.addEdge("B", "E", 4, true);
        weightedDirectedGraph.addEdge("C", "D", 4, true);
        weightedDirectedGraph.addEdge("D", "E", 4, true);
        weightedDirectedGraph.addEdge("E", "F", 3, true);
        weightedDirectedGraph.addEdge("C", "G", 1, true);
        weightedDirectedGraph.addEdge("G", "E", 1, true);
        weightedDirectedGraph.addEdge("B", "H", 4, true);
        weightedDirectedGraph.addEdge("G", "H", 2, true);

        DijkstrasShortestPathWeightedGraph<String> dijkstrasShortestPathWeightedGraph = new DijkstrasShortestPathWeightedGraph<>();

        Vertex<String> start = (Vertex<String>) weightedDirectedGraph.vertices.get(2);
        dijkstrasShortestPathWeightedGraph.shortestPathWeightedGraph(weightedDirectedGraph, start);*/

        List<Integer> weightedDirectedGraphVertices02 = new ArrayList<>();
        weightedDirectedGraphVertices02.add(1);
        weightedDirectedGraphVertices02.add(2);
        weightedDirectedGraphVertices02.add(3);
        weightedDirectedGraphVertices02.add(4);
        weightedDirectedGraphVertices02.add(5);
        weightedDirectedGraphVertices02.add(6);
        weightedDirectedGraphVertices02.add(7);

        DirectedGraph<Integer> directedWeightedGraph02 = new DirectedGraph<>(weightedDirectedGraphVertices02);
        directedWeightedGraph02.addEdge(1,2, 6, true);
        directedWeightedGraph02.addEdge(2,5, -1, true);
        directedWeightedGraph02.addEdge(1,3, 5, true);
        directedWeightedGraph02.addEdge(3,2, -2, true);
        directedWeightedGraph02.addEdge(3,5, 1, true);
        directedWeightedGraph02.addEdge(5,7, 3, true);
        directedWeightedGraph02.addEdge(1,4, 5, true);
        directedWeightedGraph02.addEdge(4,3, -2, true);
        directedWeightedGraph02.addEdge(4,6, -1, true);
        directedWeightedGraph02.addEdge(6,7, 3, true);

        BellmondFordAlgorithm<Integer> bellmondFordAlgorithm = new BellmondFordAlgorithm<>();
        Vertex<Integer> start = (Vertex<Integer>) directedWeightedGraph02.vertices.get(0);
        bellmondFordAlgorithm.shortestPathUnweightedGraph(directedWeightedGraph02, start);
    }

}
