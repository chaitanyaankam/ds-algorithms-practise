package com.learning.disjointset;

public class Test {

    public static void main(String arg[]){
        DisjointSet ds = new DisjointSetImpl();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.unionSet(1, 2);
        ds.unionSet(2, 3);
        ds.unionSet(4, 5);
        ds.unionSet(6, 7);
        ds.unionSet(5, 6);
        ds.unionSet(3, 7);

        System.out.println(ds.findSet(1).data);
        System.out.println(ds.findSet(3).data);
        System.out.println(ds.findSet(4).data);

    }
}
