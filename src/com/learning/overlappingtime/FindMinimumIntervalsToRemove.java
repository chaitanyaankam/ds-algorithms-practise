package com.learning.overlappingtime;

import java.util.TreeSet;

/**
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * Intervals can "touch", such as [0, 1] and [1, 2], but they won't be considered overlapping.
 * For example, given the intervals (7, 9), (2, 4), (5, 8), return 1 as the last interval can be removed and the first two won't overlap.
 * The intervals are not necessarily sorted in any order.
 * */
public class FindMinimumIntervalsToRemove {

    /**
     * The complexity of the below solution is little less than O(2^n)
     * and requires sorted time line with respect to start date
     * */
    private static int solve(int[][] arr, int index, int prev, int count) {
        if(index == arr.length) return count;
        if(index > 0 && arr[prev][1] > arr[index][0]) {
            return solve(arr, index + 1, prev, count + 1);
        }

        int inc = solve(arr, index + 1, index, count);
        int exc = solve(arr, index + 1, prev, count + 1);
        return Math.min(inc, exc);
    }

    private static int solve(int[][] arr) {
        TreeSet<int[]> start = new TreeSet<>((int[] x, int[] y) -> (x[0] > y[0]) ? -1 : (x[0] < y[0]) ? 1 : 0);
        TreeSet<int[]> end = new TreeSet<>((int[] x, int[] y) -> (x[1] > y[1]) ? 1 : (x[1] < y[1]) ? -1 : 0);
        for(int[] item: arr) {
            start.add(item);
            end.add(item);
        }

        int[] curr = start.iterator().next();
        int count = 0;
        while(curr != null) {
            System.out.println(curr[0] + " "+curr[1]);
            curr = end.lower(new int[]{curr[0], 0});
            count ++;
        }

        return count;
    }

    public static void main(String[] arg) {
        int[][] arr = {
                {2, 7},
                {3, 11},
                {7, 9},
                {10, 15}
        };
        int result = solve(arr, 0, 0, 0);
        System.out.println("minimum intervals to remove "+ result);

        result = solve(arr);
    }
}
