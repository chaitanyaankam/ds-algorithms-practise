package com.learning.tree;

import java.util.TreeMap;
import java.util.TreeSet;

public class TreeSetTraversal {

    public static void treeNavigation(){
        TreeSet<Integer> set=new TreeSet<Integer>();
        set.add(24);
        set.add(66);
        set.add(12);
        set.add(15);

        System.out.println(set.floor(20));
        System.out.println(set.ceiling(20));
        System.out.println(set.headSet(24, true));
        System.out.println(set.tailSet(24, true));
    }

    public static void treeMapNavigation() {
        TreeMap<Integer,String> map=new TreeMap<Integer,String>();
        map.put(66,"Amit");
        map.put(24,"Ravi");
        map.put(12,"Vijay");
        map.put(15,"Rahul");

        System.out.println(map.lowerEntry(20));
        System.out.println(map.higherEntry(20));
        System.out.println(map.headMap(24, true));
        System.out.println(map.tailMap(24, true));
    }
}
