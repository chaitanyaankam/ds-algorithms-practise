package com.learning.implementation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Create a data structure that performs all the following operations in O(1) time:
 *
 * plus: Add a key with value 1. If the key already exists, increment its value by one.
 * minus: Decrement the value of a key. If the key's value is currently 1, remove it.
 * get_max: Return a key with the highest value.
 * get_min: Return a key with the lowest value.
 *
 * https://leetcode.com/problems/all-oone-data-structure/discuss/1975933/Java-Solution-using-HashMap-(counting)-and-TreeMap-(ordering-of-counts)
 * */
public class CustomDS1 {
    HashMap<String, Integer> map;
    TreeMap<String, Integer> treeMap;

    public CustomDS1() {
        map = new HashMap<>();
        treeMap = new TreeMap<>(Comparator.naturalOrder());
    }

    public void plus(String s){
        map.putIfAbsent(s, 1);
        map.computeIfPresent(s, (k, v) -> v + 1);
        treeMap.remove(s);
        treeMap.put(s, map.get(s));
    }

    public void minus(String s){
        if(!map.containsKey(s)) return;
        treeMap.remove(s);
        if(map.get(s) == 1)
            treeMap.remove(s);
        else {
            map.put(s, map.get(s) - 1);
            treeMap.put(s, map.get(s));
        }
    }

    public int get_max(){
        return treeMap.firstEntry().getValue();
    }

    public int get_min(){
        return treeMap.lastEntry().getValue();
    }
}
