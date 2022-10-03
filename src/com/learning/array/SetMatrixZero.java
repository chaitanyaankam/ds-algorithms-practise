package com.learning.array;

import java.util.Arrays;

/*
* Given a matrix if an element in the matrix is 0 then you will have to
*  set its entire column and row to 0 and then return the matrix.
* */
public class SetMatrixZero {

    private static int[][] solve(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[] temp_row = new int[row];
        int[] temp_col = new int[col];
        Arrays.fill(temp_col,-1);
        Arrays.fill(temp_row,-1);

        for(int i = 0; i < row; i++) {
            for(int k = 0; k < col; k++) {
                if(arr[i][k] == 0) {
                    temp_row[i] = 0;
                    temp_col[k] = 0;
                }
            }
        }

        for(int i = 0; i< row; i++) {
            for(int k = 0; k < col; k++) {
                if(temp_row[i] == 0 || temp_col[k] == 0)
                    arr[i][k] = 0;
            }
        }

        return arr;
    }

    public static void main(String arg[]) {
        int arr[][] = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        arr = solve(arr);
        for(int[] a: arr) {
            System.out.println();
            Arrays.stream(a).forEach(i -> System.out.print(i + " "));
        }
    }
}
