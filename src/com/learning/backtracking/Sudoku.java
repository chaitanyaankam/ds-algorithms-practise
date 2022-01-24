package com.learning.backtracking;

public class Sudoku {

    public static void main(String[] args) {
        int[][] grid = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        boolean canSolve = canSolve(grid, 0, 0);
        System.out.println("can solve Sudoku: "+ canSolve);

        for (int[] a : grid) {
            for (int b : a) System.out.print(b+" ");
            System.out.println();
        }
    }

    public static boolean canSolve(int[][] grid, int row, int col) {
        if(row == grid.length) return true;
        if(col == grid[0].length) return canSolve(grid, row + 1, 0);
        if(grid[row][col] != 0 ) return canSolve(grid, row, col + 1);

        for(int i = 1; i <= 9; i++) {
            if(isSafe(grid, row, col, i)) {
                grid[row][col] = i;
                boolean next = canSolve(grid, row, col + 1);
                if(next) return true;
                grid[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isSafe(int[][] grid, int row, int col, int num) {
        //in 3 * 3 grid that this (row,col) belongs to there should not be any number equals to given num
        int box_row = row / 3;
        int box_col = col / 3;

        int row_start = box_row * 3, row_end = row_start + 3;
        int col_start = box_col * 3, col_end = col_start + 3;

        for(int i = row_start; i < row_end; i++)
            for(int j = col_start; j < col_end; j++)
                if(grid[i][j] == num) return false;

        //in the entire row there should not be given num
        for(int i = 0; i < grid.length; i++)
            if(grid[i][col] == num) return false;

        //in the entire col there should not be given col
        for(int i = 0; i < grid[0].length; i++)
            if(grid[row][i] == num) return false;

        return true;
    }
}
