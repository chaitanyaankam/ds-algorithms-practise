package com.learning.graph.problems;

import java.util.Map;

/**
 * Consider a matrix with rows and columns,
 * where each cell contains either a ‘0’ or a ‘1’ and any cell containing a 1 is called a filled cell.
 * Two cells are said to be connected if they are
 * adjacent to each other horizontally, vertically, or diagonally.
 * If one or more filled cells are also connected, they form a region.
 * find the length of the largest region.
 * */
public class LargestLand {

    private static int count = 0, result = Integer.MIN_VALUE, n = 0, m = 0;
    private static int[] rows = {1, -1, 0, 0, 1, -1, 1, -1};
    private static int[] cols = {0, 0, 1, -1, 1, -1, -1, 1};

    public static void main(String[] arg) {
        int[][] a = {
                {0, 0, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1}
        };
        n = a.length;
        m = a[0].length;

        boolean[][] visited = new boolean[m][n];
        int[][] dp = new int[m][n];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                count = 0;
                solve(a, i, j, visited);
            }
        }
        System.out.println("maximum land " + result);
    }

    private static void solve(int[][] a, int r, int c, boolean[][] v) {
        if(r < 0 || r >= m || c < 0 || c >= n || a[r][c] == 0 || v[r][c])
            return;
        v[r][c] = true;
        count++;

        for(int i = 0; i < rows.length; i++)
            solve(a, r + rows[i], c + cols[i], v);
        result = Math.max(result,count);

        v[r][c] = false;
        count--;
    }
}
