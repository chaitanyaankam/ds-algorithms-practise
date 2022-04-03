package com.learning.dp;

import java.util.Arrays;

/**
 * Give rod of length N
 * on dividing the road to certain pieces, respective prices
 * are below, maximize total profit
 * */
public class RodCuttingProblem {

    public static void main(String[] arg) {
        int len = 8;
        int[] length = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        int[] dp = new int[len + 1];
        Arrays.fill(dp, -1);
        int res = topDown(length, prices, len, 0, dp);
        System.out.println("top down dp 1 "+ res);
        res = topDown_dp(prices, len);
        System.out.println("top down dp 2 "+ res);
        res = bottomUp(prices, len);
        System.out.println("Bottom Up "+ res);
    }

    private static int topDown(int[] l, int[] p, int len, int i, int[] dp) {
        if(len == 0 || i == l.length) return 0;
        if(dp[len] != -1) return -1;
        int inc = 0;
        if(len - l[i] >= 0) inc = topDown(l, p, len - l[i], i + 1, dp) + p[i];
        int exc = topDown(l, p, len, i + 1, dp);
        return dp[len] = Math.max(inc , exc);
    }

    private static int topDown_dp(int[] p, int n) {
        if(n <= 0) return 0;
        int ans = Integer.MIN_VALUE, curr = 0;
        for(int i = 0; i < n; i++) {
            int cut = i + 1;
            curr = p[i] + topDown_dp(p ,n - cut);
            ans = Math.max(curr, ans);
        }
        return ans;
    }

    private static int bottomUp(int[] p, int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        int ans = 0;
        for(int len = 1; len <= n; len++) {
            for(int i = 0; i < len; i++) {
                int cut = i + 1;
                int curr = p[i] + dp[len - cut];
                ans = Math.max(curr, ans);
            }
            dp[len] = ans;
        }
        return dp[n];
    }
}
