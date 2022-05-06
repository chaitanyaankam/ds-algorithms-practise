package com.learning.dp2d.knapsack;

import java.util.Arrays;

/**
 * Packing for a vacation on a sea side
 * your carrying bag with capacity S (1 <= S <= 2000)
 * you also have N (1 <= N <= 2000) items that you might want
 * to take with sea side. Unfortunately you can fit all of them
 * in the knapsack so you need to choose.
 * for each item your given its size and value.
 * maximize total value of knapsack you choose.
 * */
public class Knapsack01 {

    public static void main(String[] arg) {
        int n = 4;
        int items = 5;
        int[] size = {1, 2, 3, 2, 2};
        int[] value = {8, 4, 0, 5, 3};
        int[][] dp = new int[items + 1][n + 1];
        for(int[] itm: dp)
            Arrays.fill(itm, -1);
        int result = topDown_dp(size, value, n, n - 1, dp);
        System.out.println("max value "+ result);
    }

    private static int topDown_dp(int[] size, int[] value, int n, int index, int[][] dp) {
        if(index == -1 || n == 0) return 0;
        if(dp[index][n] != -1) return dp[index][n];
        int inc = Integer.MIN_VALUE;
        if(n >= size[index])
            inc = value[index] + topDown_dp(size, value, n - size[index], index - 1, dp);
        int exc = topDown_dp(size, value, n, index - 1, dp);
        return dp[index][n] = Math.max(inc, exc);
    }
}
