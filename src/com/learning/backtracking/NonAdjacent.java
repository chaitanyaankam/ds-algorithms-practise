package com.learning.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of integers, write a function that returns the largest sum of non-adjacent numbers.
 * Numbers can be 0 or negative.
 * For example,
 * [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5.
 * [5, 1, 1, 5] should return 10, since we pick 5 and 5.
 *
 * Follow-up: Can you do this in O(N) time and constant space?
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 * */
public class NonAdjacent {

    private static int max = Integer.MIN_VALUE;
    private static ArrayList<Integer> out = null;

    /*
    * The time complexity is 2 pow n
    * */
    private static void solve(int[] arr, int index, int curr, ArrayList<Integer> in) {
        if(index >= arr.length) {
            if(curr > max) {
                max = curr;
                out = new ArrayList<>(in);
            }
            return;
        }

        in.add(arr[index]);
        solve(arr, index + 2, curr + arr[index], in);

        in.remove(in.size() - 1);
        solve(arr, index + 1, curr, in);
    }

    public static void main(String arg[]) {
        int[] arr = {2, 4, 6, 2, 5};
        ArrayList<Integer> in = new ArrayList<>();
        solve(arr, 0, 0, in);
        System.out.println("max count is "+max);
        out.forEach(i -> System.out.print(i+" "));
    }
}
