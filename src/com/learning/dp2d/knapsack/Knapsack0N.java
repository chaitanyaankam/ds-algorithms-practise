package com.learning.dp2d.knapsack;

import java.util.Arrays;

public class Knapsack0N {

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
            inc = value[index] + topDown_dp(size, value, n - size[index], index, dp);
        int exc = topDown_dp(size,value, n, index - 1, dp);
        return dp[index][n] = Math.max(inc, exc);
    }

}
