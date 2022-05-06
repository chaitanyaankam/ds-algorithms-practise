package com.learning.dp;

import java.util.Arrays;

public class SUPW {

    public static void main(String[] arg) {
        int n = 10, k = 3;
        int[] supw = {3, 2, 1, 1, 2, 3, 1, 3, 2, 1};
        int[] dp = new int[supw.length];
        Arrays.fill(dp, -1);

        int ans = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++) {
            Arrays.fill(dp, -1);
            ans = Math.min(ans, topdown_dp(supw, k, n - i, dp));
        }
        System.out.println("min SUPW work "+ ans);

        int[] supw2 = {3, 2, 1, 1, 2, 3, 1, 3, 2, 1};
        int res = bottomUp_dp(supw2, k);
        System.out.println("min SUPW work "+ res);
    }

    public static int topdown_dp(int[] a, int k, int i, int[] dp) {
        if(i < 0) return 0;
        if(dp[i] != -1) return dp[i];

        int ans = Integer.MAX_VALUE;
        for(int j = 1; j <= k; j++) {
            ans = Math.min(ans, topdown_dp(a, k, i - j, dp));
        }
        return dp[i] = (ans == Integer.MAX_VALUE) ? a[i] : a[i] + ans;
    }

    public static int bottomUp_dp(int[] a, int k) {
        int n = a.length;
        int[] dp = new int[n];
        for(int i = 0; i < k; i++) dp[i] = a[i];

        for(int i = k; i < a.length; i++) {
            int ans = Integer.MAX_VALUE;
            for(int j = 1; j <= k; j++) ans = Math.min(ans, dp[i - j]);
            dp[i] = a[i] + ans;
        }

        int ans = Integer.MAX_VALUE;
        for(int j = 1; j <= k; j++) ans = Math.min(ans, dp[n - j]);

        return ans;
    }

}
