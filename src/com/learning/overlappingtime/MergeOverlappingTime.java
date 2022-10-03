package com.learning.overlappingtime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Given a set of time intervals in any order,
 * merge all overlapping intervals into one and output the result
 * which should have only mutually exclusive intervals.
 * Input: Intervals = {{1,3},{2,4},{6,8},{9,10}}
 * Output: {{1, 4}, {6, 8}, {9, 10}}
 * */
public class MergeOverlappingTime {

    public static void main(String arg[]) {
        MergeOverlappingTime olt = new MergeOverlappingTime();
        int[][] overlappingTimes = {
                {1, 3},
                {2, 4},
                {6, 8},
                {9, 10}
        };
        List<int[]> result = solveUsingStack(overlappingTimes);
        result.forEach(arr -> System.out.println(arr[0]+" "+arr[1]));
    }

    private static List<int[]> solve(int[][] arr) {
        int start = arr[0][0], end = arr[0][1], len = arr.length;
        List<int[]> result = new ArrayList<>();

        for(int k = 1; k <= len; k++) {
            int curr_start = (k <= len) ? arr[k][0]: Integer.MAX_VALUE;
            int curr_end = (k <= len) ? arr[k][1]: Integer.MAX_VALUE;
            int[] curr = null;

            //non-overlapping
            if (end < curr_start) {
                result.add(new int[]{start, end});
                start = curr_start;
                end = curr_end;
            }
            //overlapping
            else
                end = Math.max(curr_end, end);
        }
        return result;
    }

    /**
     * Given the int[][] sorted by start times
     * the time complexity is O(n)
     * the space complexity is O(n)
     * */
    private static List<int[]> solveUsingStack(int[][] arr) {
        Stack<int[]> stack = new Stack<>();
        stack.push(arr[0]);

        for(int i = 0; i < arr.length; i++) {
            int[] prev = stack.peek();
            int[] curr = arr[i];

            if(prev[1] >= curr[0]) {
                prev[1] = Math.max(prev[1], curr[1]);
                stack.pop();
                stack.push(prev);
            }
            else stack.push(curr);
        }

        List<int[]> result = new ArrayList<>();
        while(!stack.isEmpty()) result.add(stack.pop());
        Collections.reverse(result);

        return result;
    }
}
