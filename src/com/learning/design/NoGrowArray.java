package com.learning.design;

import java.util.HashMap;
import java.util.Map;

public class NoGrowArray {

    public static void main(String[] arg) {
        NoGrowArray ng = new NoGrowArray(3);
        ng.add(1);
        ng.add(2);
        ng.add(3);
        ng.add(4);

        System.out.println(ng.check(1));
        System.out.println(ng.check(4));
    }

    int[] arr;
    int curr;
    Map<Integer, Integer> map;

    public NoGrowArray(int size) {
        this.arr = new int[size];
        this.map = new HashMap<>();
        this.curr = 0;
    }

    public void add(int a) {
        if(curr == arr.length) curr = 0;
        arr[curr] = a;
        map.computeIfPresent(a, (k,v) -> curr);
        map.computeIfAbsent(a, (k)-> curr);
        curr++;
    }

    public boolean check(int a) {
        return map.containsKey(a);
    }
}
