package com.learning.dp;

import java.util.Arrays;

/*
* Given a matrix of 1s and 0s, return the number of "islands" in the matrix.
*  A 1 represents land and 0 represents water, so an island is a group of 1s
* that are neighboring whose perimeter is surrounded by water.
* */
public class Island {

    static int row = 0;
    static int col = 0;
    static int[] r = {1, -1, 1, -1, 0, 1, -1, 0};
    static int[] c = {1, -1, -1, 1, 1, 0, 0, -1};
    static int max = -1;
    static int count = 0;

    private static void findMax(int[][] arr) {
        row = arr.length;
        col = arr[0].length;
        boolean[][] visited = new boolean[row][col];
        for(boolean[] bol_arr: visited)
            Arrays.fill(bol_arr, false);

        for(int i = 0; i < row; i++) {
            for( int j = 0; j < col; j++) {
                if(arr[i][j] == 0) continue;
                int res = solve(arr, visited, i, j, 1);
                max = Math.max(res, max);
            }
        }
        System.out.println(max);
    }

    private static int solve(int[][] arr, boolean[][] visited, int ri, int ci, int count) {
        if(ri< 0 || ri >= row || ci < 0 || ci >= col || visited[ri][ci] || arr[ri][ci] == 0)
            return 0;
        visited[ri][ci] = true;
        for(int i = 0; i < 8; i++) {
            count += solve(arr, visited, ri + r[i], ci + c[i], count);
        }
        return count;
    }

    public static void main(String arg[]) {
        int[][] arr = {
                {1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {1, 1, 0, 0, 1}
        };
        findMax(arr);
    }
}
