package com.learning.dp2d;

import java.util.Arrays;

/**
 * You are given an integer array prices where prices[i]
 * is the price of a given stock on the ith day, and an integer k.
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 * Note: You may not engage in multiple transactions simultaneously
 *
 * (i.e., you must sell the stock before you buy again).
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * */
public class BuySellStock {

    private static int max = Integer.MIN_VALUE;

    public static void main(String[] arg) {
        int k = 2;
        int[] arr = {3, 2, 6, 5, 0, 3};
        int[][][] dp = new int[arr.length][2][k + 1];
        for(int[][] x: dp) for(int[] y: x) Arrays.fill(y, -1);
        int res = top_down(arr, k, 0, 1, dp);
        System.out.println("\ntop down result "+ res);
    }

    public static int top_down(int[] arr, int k, int i, int flag, int[][][] dp) {
        if(i == arr.length || k == 0) return 0;
        if(dp[i][flag][k] != -1) return dp[i][flag][k];
        int ans = ( flag == 1 )
                ? Math.max( -arr[i] + top_down(arr, k, i + 1, 0, dp), top_down(arr, k, i + 1, 1, dp))
                : Math.max(  arr[i] + top_down(arr, k - 1, i + 1, 1, dp), top_down(arr, k, i + 1, 0, dp));
        return dp[i][flag][k] = ans;
    }
}
