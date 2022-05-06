package com.learning.heap.problems;

import java.util.*;

public class CartersianDistance {

    public static void main(String[] arg) {
        Cab c1 = new Cab("C1", 1, 0);
        Cab c2 = new Cab("C2", 1, 2);
        Cab c3 = new Cab("C3", 2, 2);
        Cab c4 = new Cab("C4", 1, 1);

        solve(Arrays.asList(new Cab[]{c1, c2, c3, c4}), 0, 0, 3);
    }

    private static void solve(List<Cab> cabs, int x, int y, int k) {
        Comparator<Cab> compareByDistance = (c1, c2) -> c1.d > c2.d ? 1 : c1.d < c2.d ? -1 : 0;
        PriorityQueue<Cab> nearestCabs = new PriorityQueue<>(compareByDistance);
        for(Cab c: cabs) {
            c.d = Math.sqrt(square(c.x - x) + square(c.y - y));
            nearestCabs.offer(c);
        }

        while(k > 0) {
            System.out.println(nearestCabs.poll().name);
            k--;
        }
    }

    private static int square(int x) {
        return x * x;
    }
}

class Cab {
    String name;
    int x;
    int y;
    double d;

    public Cab( String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}