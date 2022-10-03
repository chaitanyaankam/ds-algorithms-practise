package com.learning.array;

public class SprialTraversal {

    private static void solve(int[][] a) {
        int row = a.length - 1, col = a[0].length - 1, r = 0, c = 0;

        while(row > 0 && col > 0) {
            int curr_c = col + c;
            while(c <= curr_c) {
                System.out.print(a[r][c] + " ");
                c++;
            }
            c--;
            r++;
            row--;

            int curr_r = row + r;
            while(r <= curr_r) {
                System.out.print(a[r][c] + " ");
                r++;
            }
            r--;
            c--;
            col--;

            curr_c = c - col;
            while(c >= curr_c) {
                System.out.print(a[r][c]+" ");
                c--;
            }
            c++;
            r--;
            row--;

            curr_r = r - row;
            while(r >= curr_r){
                System.out.print(a[r][c]+" ");
                r--;
            }
            r++;
            c++;
            col--;
        }
    }

    public static void main(String[] arg) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        solve(arr);
    }
}
