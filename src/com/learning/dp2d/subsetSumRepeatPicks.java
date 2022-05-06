package com.learning.dp2d;

import java.util.Arrays;

public class subsetSumRepeatPicks {

    public static void main(String[] arg) {
        int[] a = {2, 7, 4, 19}; // pick each element any number of times
        int sum = 12;
        int[][] dp = new int[a.length][sum + 1];
        for(int[] x: dp) Arrays.fill(x, -1);
        boolean res = topDown_dp(a, 0, sum, dp);

        System.out.println("top down "+ res);
    }

    private static boolean topDown_dp(int[] a, int index, int x, int[][] dp) {
        if(index == a.length) return x == 0;
        if(dp[index][x] != -1) return true;

        boolean ans = false;
        if(x >= a[index]) ans |= topDown_dp(a, index, x - a[index], dp);
        ans |= topDown_dp(a, index + 1, x, dp);

        if(ans) dp[index][x] = 1;
        return ans;
    }

    private static boolean dp_tracing(int[] a, int sum) {
        int n = a.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        dp[0][0] = true;
        for(int i = 1; i<=sum; i++) dp[0][i] = false;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= a[i]) dp[i][j] |= dp[i][j - a[i]];
            }
        }
        return dp[n][sum];
    }
}
