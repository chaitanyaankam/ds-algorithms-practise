package com.learning.dp2d;

import java.util.Arrays;

/**
 * There are n houses, each house can be painted by one of 3 colors
 * Red, Green & Blue.
 * Cost of painting each house with certain color is different
 * n * 3 matrix cost[0][0] cost of house 0 painted with color 0
 * No 2 adj houses have same color
 * find minimum cost of painting all houses
 *
 * and its follow-up below
 * */
public class PaintingHouse {

    public static void main(String[] arg) {
        int n = 2, m = 3;
        int[][] cost = {{1, 2, 3},
                        {10, 11, 12}};
        int[][] dp = new int[n][m];
        for(int[] arr: dp)
            Arrays.fill(arr, -1);

        int ans = topDown_dp( n - 1, m, -1, cost, dp);
        System.out.println("mini cost "+ ans);
    }

    private static int topDown_dp(int houses, int m, int color, int[][] cost, int[][] dp) {
        if(houses == -1) return 0;
        if(color != -1 && dp[houses][color] != -1) return dp[houses][color];

        int ans = Integer.MAX_VALUE;
        for(int clr = 0; clr < m; clr++) {
            if(clr != color) ans = Math.min(ans, cost[houses][clr] + topDown_dp(houses - 1, m, clr, cost, dp));
        }

        if(color == -1) return ans;
        else return dp[houses][color] = ans;
    }
}
