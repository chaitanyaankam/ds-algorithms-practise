package com.learning.dp;

import java.util.Arrays;

/**
 * reduce N to 1 in minimal steps using following operations
 * n -> n-1
 * n -> n/2
 * n -> n/3
 * return minimal steps
 * */
public class ReduceTo1 {

    public static void main(String[] arg) {
        int n = 10;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int res = reduceTo1(n, dp);
        System.out.println("min steps needed "+ res);
    }

    public static int reduceTo1(int n, int[] dp) {
        if(n == 1) return 0;
        if(dp[n] != -1) return dp[n];
        int ans = Integer.MAX_VALUE;
        if(n % 2 == 0) ans = reduceTo1(n/2, dp);
        if(n % 3 == 0) ans = reduceTo1(n/3, dp);
        return dp[n] = 1 + Math.min(reduceTo1(n - 1, dp), ans);
    }
}
