package com.learning.disjointset;

/*
* Disjoint set using union by rank and path compression
* Disjoint set is a data-structure that supports three operations
* makeSet -- Its a an operation to create an set with only one element
* union -- Its is an operation to merge 2 different sets to one set
* findSet -- Its an operation to return an identity of a set which is usually an representative of the set
* Applications:
* Kruskal's algorithm
* Finding cycle in a undirected graph
* */
public interface DisjointSet<T> {

    void makeSet(T data);

    void unionSet(T data1, T data2);

    Node findSet(T data);

    Node findSet(Node node);
}
