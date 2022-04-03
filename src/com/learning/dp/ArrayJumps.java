package com.learning.dp;

import java.util.Arrays;

/**
 * minimum no. of jumps required to reach end of array
 * */
public class ArrayJumps {

    public static void main(String[] arg) {
        int[] a = {3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 2, 5};
        int[] dp = new int[a.length];
        Arrays.fill(dp, -1);
        int res = topDown(a, 0, dp);
        System.out.println("top down dp "+ res);

        res = bottomUp(a);
        System.out.println("bottom up dp "+ res);
    }

    public static int bottomUp(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for(int i = 1; i < n; i++) {
            for(int k = 0; k < i; k++) {
                if(a[k] >= i - k) dp[i] = Math.min(dp[i], dp[k] + 1);
            }
        }
        //3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 2, 5
        //0, 1, 2,
        return dp[n - 1] == Integer.MAX_VALUE ? -1 : dp [n - 1];
    }

    public static int topDown(int[] a, int i, int[] dp) {
        if(i == a.length - 1) return 0;
        if(dp[i] != -1) return dp[i];

        int ans = Integer.MAX_VALUE, curr = 0;
        for(int j = 1; j <= a[i]; j++) {
            if(i + j > a.length - 1) continue;
            curr = 1 + topDown(a, i + j, dp);
            if(curr < ans) ans = curr;
        }
        return dp[i] = ans;
    }
}
