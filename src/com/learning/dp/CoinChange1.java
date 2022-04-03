package com.learning.dp;

import java.util.Arrays;

/**
 * Given an array of coin denominations and target M.
 * Need to find minimum number of coins to form target sum.
 * */
public class CoinChange1 {

    public static void main(String[] arg) {
        int[] coins = {1, 2, 5};
        int x = 11;
        int res = bottomUp(coins, x);
        System.out.println("\nBottom Up "+ res);

        int[] dp = new int[x + 1];
        Arrays.fill(dp, -1);
        res = topDown(coins, x, dp);
        System.out.println("Top Down "+ res);
    }

    public static int topDown(int[] coins, int x, int[] dp) {
        if(x == 0) return 0;
        int min = Integer.MAX_VALUE, curr = 0;
        if(dp[x] != -1) return dp[x];

        for(int coin: coins) {
            if(x - coin >= 0) curr = topDown(coins, x - coin, dp) + 1;
            if(curr < min) min = curr;
        }
        return dp[x] = (min == Integer.MAX_VALUE) ? -1 : min;
    }

    public static int bottomUp(int[] coins, int x) {
        int[] dp = new int[x + 1];
        dp[0] = 0;
        for (int i = 1; i <= x; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins){
                int compare = (dp[i - coin] == Integer.MAX_VALUE) ? dp[i - coin] : dp[i - coin] + 1;
                dp[i] = Math.min(dp[i], compare);
            }
        }
        for(int j : dp) System.out.print(j+" ");
        return dp[x] == Integer.MAX_VALUE ? -1 : dp[x];
    }
}
