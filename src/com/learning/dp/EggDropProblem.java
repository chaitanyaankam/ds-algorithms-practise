package com.learning.dp;

import java.util.Arrays;

public class EggDropProblem {
    static final int MAX = 1000;
    static int[][] memo = new int[MAX][MAX];

    public static void main(String[] arg) {
        for(int[] arr: memo) Arrays.fill(arr, -1);
        int n = 2;
        int k  = 36;
        int result = solveEggDrop(n, k);
        System.out.println(result);
    }

    private static int solveEggDrop(int n, int k) {
        if (memo[n][k] != -1) return memo[n][k];
        if (k == 1 || k == 0 || n == 1) return k;
        int min = Integer.MAX_VALUE, x, res;

        for (x = 1; x <= k; x++) {
            res = Math.max(solveEggDrop(n - 1, x - 1), solveEggDrop(n, k - x));
            if (res < min)
                min = res;
        }
        memo[n][k] = min + 1;
        return min + 1;
    }
}
