package com.learning.design;

import java.util.*;

/**
 * Design a data structure that supports
 * insert, delete, search and getRandom in constant time
 * */
public class CustomList {
    Map<Integer, List<Integer>> map;
    List<Integer> list;
    int size;

    public CustomList() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public void insert(int a) {
        list.add(a);
        map.computeIfPresent(a, (k, v)-> {
            v.add(size++);
            return v;
        });
        map.computeIfAbsent(a, (k)-> {
            List<Integer> indexes = new ArrayList<>();
            indexes.add(size++);
            return indexes;
        });
    }

    public void remove(int a) {
        if(!map.containsKey(a)) return;
        List<Integer> indexes = map.get(a);
        for(int index: indexes) {
            list.set(index ,list.get(size - 1));
            list.set(size - 1, Integer.MIN_VALUE);
            size--;
        }
        map.remove(a);
    }

    public Integer getRandom(int index) {
        if(index >= size) throw new RuntimeException("Index out-of-bounds");
        return list.get(index);
    }

    public static void main(String arg[]) {
        CustomList customList = new CustomList();
        customList.insert(1);
        customList.insert(1);
        customList.insert(2);
        customList.insert(3);
        customList.insert(5);
        customList.insert(6);
        customList.remove(6);
        customList.list.stream().forEach(x-> System.out.println(x+" "));
        System.out.println("Element at index 3 is "+customList.getRandom(4));
    }
}
