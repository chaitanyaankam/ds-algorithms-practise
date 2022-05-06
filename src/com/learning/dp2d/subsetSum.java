package com.learning.dp2d;

import java.util.Arrays;

public class subsetSum {

    public static void main(String[] arg) {
        int[] a = {2, 7, 4, 19};
        int sum = 12;
        int[][] dp = new int[a.length][sum + 1];
        for(int[] x: dp) Arrays.fill(x, -1);
        boolean res = topDown(a, 0, 12, 0, dp);

        System.out.println("top down "+ res);
    }

    private static boolean topDown(int[] a, int index, int x, int curr, int[][] dp) {
        if(index == a.length) return x == curr;
        if(dp[index][x] != -1) return true;

        boolean ans = false;
        if(x >= a[index]) ans |= topDown(a, index + 1, x, curr + a[index], dp);
        ans |= topDown(a, index + 1, x, curr, dp);

        if(ans) dp[index][x] = 1;
        return ans;
    }
}
