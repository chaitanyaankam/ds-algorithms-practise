package com.learning.dp;

import java.util.Arrays;

/**
 * Count number of BST that can be formed from N nodes,
 * numbered from 1 to N
 * */
public class CountBST {
    
    public static void main(String[] arg) {
        int n = 5;
        int[] dp = new int[n + 1];
        int res = topdown(n, dp);
        System.out.println("top down dp "+ res);

        res = bottomUp(n);
        System.out.println("bottom down dp "+ res);
    }

    private static int bottomUp(int N) {
        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 1;
        for(int n = 2; n <= N; n++) {
            for(int i = 1; i <= n; i++)
                dp[n] += (dp[i - 1] * dp[n - i]);
        }
        return dp[N];
    }
    
    private static int topdown(int n, int[] dp) {
        if(n == 0 || n == 1) return 1;
        if(dp[n] != 0) return dp[n];

        int ans = 0;
        for(int i = 1; i <= n; i++) {
            int x = topdown(i - 1, dp);
            int y = topdown(n - i, dp);
            ans += (x * y);
        }
        return dp[n] = ans;
    }
}
