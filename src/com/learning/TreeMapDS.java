package com.learning;

import java.util.TreeMap;

public class TreeMapDS {
    public static void main(String arg[]) {
        TreeMap<Integer,String> tmap = new TreeMap<>();
        tmap.put(1, "I");
        tmap.put(4, "IV");
        tmap.put(5, "V");
        tmap.put(9, "IX");

        System.out.println(tmap.floorKey(3));
    }
}
