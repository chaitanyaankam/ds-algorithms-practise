package com.learning.backtracking;

public class NQueen {

    public static void main(String[] arg) {
        int[][] board = new int[4][4];
        boolean canPlace = nQueen(board, 0);

        System.out.println("can place n queens: " + canPlace);
        for (int[] a : board) {
            for (int b : a) System.out.print(b+" ");
            System.out.println();
        }

        int[][] board2 = new int[4][4];
        int noOfWays = countNQueenWays(board2, 0);
        System.out.println("no of ways "+ noOfWays);
    }

    /**
     * o(N^N) is the complexity
     * */
    public static boolean nQueen(int[][] board, int row) {
        if(row == board.length)
            return true;
        for(int i = 0; i < board[0].length; i++) {
            if(isSafe(board, row, i)) {
                board[row][i] = 1;
                if(nQueen(board, row + 1)) return true;
                board[row][i] = 0;
            }
        }
        return false;
    }

    public static int countNQueenWays(int[][] board, int row) {
        if(row == board.length)
            return 1;
        int ways = 0;
        for(int i = 0; i < board[0].length; i++) {
            if(isSafe(board, row, i)) {
                board[row][i] = 1;
                ways += countNQueenWays(board, row + 1);
                board[row][i] = 0;
            }
        }
        return ways;
    }

    public static boolean isSafe(int[][] board, int row, int col) {
        int  r = row, c = col;
        while (r < board.length && c < board[0].length) {
            if(board[r][c] == 1) return false;
            r++;
            c++;
        }

        r = row; c = col;
        while (r >= 0 && c >= 0) {
            if(board[r][c] == 1) return false;
            r--;
            c--;
        }

        r = row; c = col;
        while (r < board.length && c >= 0) {
            if(board[r][c] == 1) return false;
            r++;
            c--;
        }

        r = row; c = col;
        while (r >= 0 && c < board[0].length ) {
            if(board[r][c] == 1) return false;
            r--;
            c++;
        }

        for(int i = 0; i <= board.length - 1; i++)
            if(board[i][col] == 1) return false;

        return true;
    }
}
