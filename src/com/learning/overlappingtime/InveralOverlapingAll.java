package com.learning.overlappingtime;

import java.util.List;

/**
 * Given a set of closed intervals, find the smallest set of numbers that covers all the intervals.
 * If there are multiple smallest sets, return any of them.
 * For example, given the intervals [0, 3], [3, 4], [2, 6], [6, 9],
 * one set of numbers that covers all these intervals is {3, 6}.
 *
 * {1, 4}, {4, 5}, {7, 9}, {9, 12}
 * */
public class InveralOverlapingAll {

    private static int[] findIntervalOverlapsAll(List<int[]> list) {
        //n long n sort by end time

        int[] curr = list.get(0);
        int start = curr[0], end = curr[1];

        for(int i = 1; i < list.size(); i++) {
            curr = list.get(i);
            if(curr[0] > start && curr[0] > end)
                return new int[]{start, end};

            if(curr[0] == end)
                continue;
            if(curr[0] >= start) {
                start = curr[0];
                end = Math.max(end, curr[1]);
            } else if (curr[0] < start){
                end = Math.max(end, curr[1]);
            }
        }

        return new int[]{start, end};
    }

    public static void main(String arg[]) {
        List<int[]> data = List.of(new int[]{0, 3}, new int[] {3, 4}, new int[]{2, 6}, new int[]{6, 9});
        //List<int[]> data = List.of(new int[]{1, 4}, new int[]{4, 5}, new int[] {7, 9}, new int[]{9, 12});
        int[] result = findIntervalOverlapsAll(data);
        System.out.println("final "+result[0]+" "+ result[1]);
    }
}
