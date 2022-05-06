package com.learning.heap.problems;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianInInfinite {

    public static void main(String[] arg) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        double res = solve(a);
        System.out.println("median "+ res);
    }

    private static double solve(int[] a) {
        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.naturalOrder());

        for(int i: a) {
            if(max.size() < min.size()) max.offer(min.poll());
            min.offer(i);
        }
        if(max.size() < min.size()) max.offer(min.poll());
        return max.size() > min.size() ? max.poll() : (max.poll() + min.poll())/2.0;
    }
}
