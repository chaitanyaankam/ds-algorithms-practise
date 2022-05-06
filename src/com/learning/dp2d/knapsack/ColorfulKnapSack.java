package com.learning.dp2d.knapsack;

/**
 * N stones labeld 1 - n, stone has weight w[i]
 * M colors labeld 1 - m, ith stone has color c[i].
 * The Knapsack has a weight of X.
 * You are required to select exactly M stones one
 * of each color. The sum of weights must not exceed X.
 * Find the best way to fill the Knapsack so that unused capacity is minimized.
 * */
public class ColorfulKnapSack {

    public static void main(String[] arg) {
        int n = 3;
        int m = 3;
        int x = 5;
        int[] w = {1, 1, 1};
        int[] c = {1, 2, 3};
        int res = topDown_dp(w, c, x, m, n);
    }

    public static int topDown_dp(int[] w, int[] c, int x, int m, int index) {
        if(m == 0 || x == 0 || index == 0) return 0;
        int inc = Integer.MIN_VALUE;
        if(x >= w[index])
            inc = topDown_dp(w, c, x - w[index], m - 1, index);
        int exc = topDown_dp(w, c, x, m, index - 1);
        return Math.max(inc, exc);
    }
}
