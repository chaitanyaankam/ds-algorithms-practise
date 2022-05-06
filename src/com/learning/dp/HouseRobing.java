package com.learning.dp;

import java.util.Arrays;

public class HouseRobing {

    public static void main(String[] arg) {
        int[] a ={ 1, 2, 3, 1 };
        int[] dp = new int[a.length];
        Arrays.fill(dp, -1);
        int res = topDown_dp(a, 0, dp);
        System.out.println("top down "+ res);

        res = bottomUp_dp(a);
        System.out.println("bottom up "+res);
    }

    public static int topDown_dp(int[] a, int index, int[] dp) {
        if(index == a.length) return 0;
        if(dp[index] != -1) return dp[index];
        int inc = a[index];
        if(index + 2 < a.length) inc += topDown_dp(a, index + 2, dp);
        int exc = topDown_dp(a, index + 1, dp);
        return dp[index] = Math.max(inc, exc);
    }

    public static int bottomUp_dp(int[] a) {
        int[] dp = new int[a.length];
        dp[0] = a[0];
        dp[1] = Math.max(a[1], a[0]);

        for(int i = 2; i < a.length; i++) {
            dp[i] = Math.max(a[i] + dp[i - 2], dp[i-1]);
        }
        return Math.max(dp[a.length - 1], dp[a.length - 2]);
    }
}
