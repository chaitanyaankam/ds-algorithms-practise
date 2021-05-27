package com.learning.dp;

import java.util.Arrays;

public class GoldMining {

    public static void main(String arg[]) {
        int[][] grid = new int[][] {
            {0, 6, 0},
            {5, 8, 7},
            {0, 9, 0}
        };
        GoldMining goldMining = new GoldMining();
        System.out.println(" mining result "+goldMining.getMaximumGold(grid));
    }

    public int getMaximumGold(int[][] grid) {
        int n = grid.length, m =grid[0].length;
        boolean[][] visited = new boolean[n][m];

        int max = 0;
        for(int i=0; i<n ;i++)
            for(int j=0; j<m; j++)
                max = Math.max(max, getMaxGoldAtPos(i, j, grid, visited));

        return max;
    }

    public int getMaxGoldAtPos(int row, int col, int[][] grid, boolean[][] visited) {
        int n = grid.length, m =grid[0].length;
        if(col > m-1 || row > n-1 || visited[row][col] || grid[row][col] == 0)
            return 0;

        System.out.println(row+" "+col+" val " );
        visited[row][col] = true;

        int l=0, r=0, u=0, d=0;
        l = (col == 0) ? 0 : getMaxGoldAtPos(row, col - 1, grid, visited);
        r = (col + 1 > m - 1) ? 0 : getMaxGoldAtPos(row, col + 1, grid, visited);
        u = (row == 0) ? 0 : getMaxGoldAtPos(row - 1, col, grid, visited);
        d = (row + 1 > n - 1) ? 0 : getMaxGoldAtPos(row + 1, col, grid, visited);

        visited[row][col] = false;

        return grid[row][col]+ Math.max(Math.max(r, l), Math.max(u, d));
    }
}