package com.learning.dp;

/**
 * maximize profit, cant play more than k matches in a row
 * SUPW and IPL are related
 * */
public class IPL {

    public static void main(String[] arg) {
        int n = 10, k = 3;
        int[] a = {3, 2, 1, 1, 2, 3, 1, 3, 2, 1};
        int res = bottomUp_dp(a, k);
        System.out.println("max profit "+ res);
    }

    public static int bottomUp_dp(int[] a, int k) {
        int n = a.length, ans = 0;
        int[] dp = new int[n];
        dp[0] = a[0];
        dp[1] = a[1];
        for(int i = k; i < n; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + a[i];
        return Math.max(dp[n - 1], dp[n - 2]);
    }
}
