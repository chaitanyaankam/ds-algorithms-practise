package com.learning.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of numbers, find the length of the longest increasing subsequence in the array.
 * The subsequence does not necessarily have to be contiguous.
 *
 * For example, given the array
 * [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15],
 * the longest increasing subsequence has length 6:
 * it is 0, 2, 6, 9, 11, 15.
 * */
public class LongestIncreasingSubSequence {

    private static List<Integer> result = null;

    public static void main(String arg[]) {
        int[] a = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        result = new ArrayList<>();
        List<Integer> out = new ArrayList<>();
        solve(a, 0, out);
        System.out.println("result recursion "+ result.size());

        int[][] memo = new int[a.length + 1][a.length];
        for(int[] arr: memo) Arrays.fill(arr, -1);
        int res = solveCount(a, 0, -1, memo);
        System.out.println("result top-down "+ res);
        for(int[] x: memo) {
            System.out.println();
            for (int y : x) System.out.print(y + " ");
        }
    }

    /** Bottom-up Approach */
    public int longestIncreasingSubsequence(int[] inputArray) {
        int[] liss = new int[inputArray.length];
        Arrays.fill(liss, 1);
        for (int i = 1; i < inputArray.length; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (inputArray[i] > inputArray[j]) {
                    liss[i] = Math.max(liss[i], liss[j] + 1);
                }
            }
        }
        return liss[liss.length - 1];
    }

    /** Following methods are top-down*/
    public static int solveCount(int[] a, int curr, int prev, int[][] memo) {
        if(curr == a.length) return 0;
        if(memo[prev + 1][curr] >= 0) return memo[prev + 1][curr];
        int inc = (prev < 0 || a[curr] > a[prev])
                ? solveCount(a, curr + 1, curr, memo) + 1
                : 0;
        int exc = solveCount(a, curr + 1, prev, memo);
        return memo[prev + 1][curr] = Math.max(inc, exc);
    }

    private static void solve(int[] a, int i, List<Integer> out) {
        if(i == a.length) {
            if(out.size() >= result.size()) result = new ArrayList<>(out);
            return;
        }
        else if(!out.isEmpty() && out.get(out.size()-1) > a[i])
            solve(a, i + 1, out);
        else {
            out.add(a[i]);
            solve(a, i + 1, out);

            out.remove(out.size() - 1);
            solve(a, i + 1, out);
        }
    }
}
