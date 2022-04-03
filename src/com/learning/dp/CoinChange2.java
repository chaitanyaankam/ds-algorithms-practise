package com.learning.dp;

import java.util.Arrays;

/**
 * Given an array of coin denominations and target M.
 * Need to find no of combination ways to make change.
 * Here we need no of ways
 * */
public class CoinChange2 {

    public static void main(String arg[]) {
        int[] coins = {1, 5, 10 , 25};
        int x = 10;
        int res1 =  topdown(coins, x, 0);
        System.out.println("topDown "+ res1);

        int[][] dp = new int[coins.length][x + 1];
        for(int[] arr: dp) Arrays.fill(arr, -1);
        int res = topdown_dp(coins, x, 0, dp);
        System.out.println("topDown_dp "+res);
        for(int[] arr: dp) {
            for (int j : arr) System.out.print(j + " ");
            System.out.println();
        }

        int res3 = bottomUp_dp(coins, x);
        System.out.println("bottom up "+ res3);
    }

    public static int topdown(int[] coins, int x, int i) {
        if(x == 0) return 1;
        if(i == coins.length) return 0;

        int inc = 0, exc = 0;
        if(x - coins[i] >= 0) inc = topdown(coins, x - coins[i], i);
        exc = topdown(coins, x, i + 1);
        return inc + exc;
    }

    public static int topdown_dp(int[] coins, int x, int i, int[][] dp) {
        if(x == 0) return 0;
        if(i == coins.length) return 0;
        if(dp[i][x] != -1) return dp[i][x];

        int inc = 0, exc = 0;
        if(x - coins[i] >= 0) inc = topdown(coins, x - coins[i], i);
        exc = topdown(coins, x, i + 1);
        return dp[i][x] = inc + exc;
    }

    /**
     * for each coin see if sum 1 - x can be formed in how many ways
     * add existing sum*/
    public static int bottomUp_dp(int[] coins, int x) {
        int[] dp = new int[x + 1];
        dp[0] = 1;
        for(int coin: coins)
            for(int i = 1; i <= x; i++)
                if(i - coin >= 0) dp[i] += dp[i - coin];
        return dp[x];
    }
}
