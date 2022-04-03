package com.learning.dp;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Given a ladder of Size N, Integer K, write a function
 * to compute number of ways to climb the ladder if you can
 * take a jump of at most K at every step
 * */
public class NKLadders {

    public static void main(String[] arg) {
        int res = solve_topDown(4, 3);
        int[] memo = new int[4];
        Arrays.fill(memo, -1);
        int res2 = solve_topDown_Memoisation(4, 3, memo);
        int res3 = solve_bottomUp(4, 3);
        System.out.println("top-down "+res);
        System.out.println("top-down with memoisation "+res2);
        System.out.println("bottom-up iterative "+res3);
    }

    /* O(n) = k^n max*/
    public static int solve_topDown(int n, int k) {
        if(n < 0 ) return 0;
        if(n == 0) return 1;
        int count = 0;
        for(int i = 1; i <= k; i++) count += solve_topDown(n - i, k);
        return count;
    }

    /*O(n) is n*k times max*/
    public static int solve_topDown_Memoisation(int n, int k, int[] memo) {
        if(n < 0 ) return 0;
        if(n == 0) return 1;
        if(memo[n - 1] != -1) return memo[n];

        int count = 0;
        for(int i = 1; i <= k; i++) count += solve_topDown(n - i, k);
        return memo[n - 1] = count;
    }

    public static int solve_bottomUp(int n, int k) {
        int[] solved = new int[n + 1];
        solved[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++)
                if(i - j >= 0) solved[i] += solved[i - j];
        }
        return solved[n];
    }
}
