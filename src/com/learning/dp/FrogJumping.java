package com.learning.dp;

import java.util.Arrays;

/**
 * Given N stones, find minimum cost for a frog to jump from
 * 1 stone to reach last stone. It will repeat following actions.
 * i -> i + 1
 * i -> i + 2
 * */
public class FrogJumping {

    public static void main(String[] arg) {
        int[] a = {30, 10, 60, 10, 60, 50};
        int n = 6;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int res = topDown(a, n, 0, dp);
        System.out.println("top down dp "+res);

        res = bottomUp(a, n);
        System.out.println("bottom up dp "+res);
    }

    public static int bottomUp(int[] a, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = Math.abs(a[0] - a[1]);

        for(int i = 2; i < n; i++) {
            int op1 = dp[i - 1] + Math.abs(a[i] - a[i - 1]);
            int op2 = dp[i - 2] + Math.abs(a[i] - a[i - 2]);
            dp[i] = Math.min(op1, op2);
        }
        return dp[n-1];
    }

    public static int topDown(int[] a, int n, int i, int[] dp) {
        if(i == n - 1) return 0;
        if(dp[i] != -1) return dp[i];

        int x = Integer.MAX_VALUE, y = Integer.MAX_VALUE;
        if(i + 1 < n) x = Math.abs(a[i] - a[i + 1]) + topDown(a, n, i + 1, dp);
        if(i + 2 < n) y = Math.abs(a[i] - a[i + 2]) + topDown(a, n, i + 2, dp);

        return dp[i] = Math.min(x, y);
    }
}
