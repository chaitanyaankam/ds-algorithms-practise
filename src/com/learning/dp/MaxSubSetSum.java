package com.learning.dp;

import java.util.Arrays;

/**
 * write a function that takes in an array of positive integers
 * and returns the maximum sum of non-adjacent elements in array
 * */
public class MaxSubSetSum {

    public static void main(String arg[]) {
        int[] a = { 75, 105, 120, 75, 90, 135 };
        int res = maxSubSetSumNoAdj_topDown(a, 0);
        System.out.println("top-down "+ res);

        int[] memo = new int[a.length];
        Arrays.fill(memo, -1);
        int res2 = maxSubSetSumNoAdj_topDown_memoisation(a, 0, memo);
        System.out.println("top-down memoisation "+ res2);
        for(int x: memo) System.out.print(x+" ");

        int res3 = maxSubSetSumNoAdj_bottomUp(a);
        System.out.println("\nbottom up "+ res3);
    }

    public static int maxSubSetSumNoAdj_topDown(int[] arr, int index) {
        if(index >= arr.length) return 0;
        int with = arr[index] + maxSubSetSumNoAdj_topDown(arr, index + 2);
        int withOut = maxSubSetSumNoAdj_topDown(arr, index + 1);
        return Math.max(with, withOut);
    }

    public static int maxSubSetSumNoAdj_topDown_memoisation(int[] arr, int index, int[] dp) {
        if(index >= arr.length) return 0;
        if(dp[index] != -1) return dp[index];
        int with = arr[index] + maxSubSetSumNoAdj_topDown_memoisation(arr, index + 2, dp);
        int withOut = maxSubSetSumNoAdj_topDown_memoisation(arr, index + 1, dp);
        return dp[index] = Math.max(with, withOut);
    }

    public static int maxSubSetSumNoAdj_bottomUp(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int max = -1;
        for(int i = 0; i < arr.length; i++) {
            int a = (i - 1 < 0) ? 0 : arr[i - 1];
            int b = (i - 2 < 0) ? 0 : arr[i - 2];
            arr[i] = Math.max(a , arr[i] + b);
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}
