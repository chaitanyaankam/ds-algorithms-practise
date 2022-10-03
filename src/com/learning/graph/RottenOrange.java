package com.learning.graph;

import java.util.Arrays;
import java.util.function.Supplier;

/**
* Problem Statement: You will be given an m x n grid,
* where each cell has the following values :
2  –  represents a rotten orange
1  –  represents a Fresh orange
0  –  represents an Empty Cell
* Every minute, if a Fresh Orange is adjacent
* to a Rotten Orange in 4-direction ( upward, downwards, right, and left ) it becomes Rotten.
* Return the minimum number of minutes required such that none of the cells has a Fresh Orange.
* If it’s not possible, return -1.
* */
public class RottenOrange {

    static int row =0 , col = 0, count;
    static int[] r = {1, -1, 0, 0};
    static int[] c = {0, 0, -1, 1};

    private static int findTimeInMin(int[][] a) {
        row = a.length;
        col = a[0].length;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(a[i][j] == 1 || a[i][j] == 0) continue;
                solve(a, i, j);
            }
        }
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(a[i][j] == 1) return -1;
            }
        }
        return count;
    }

    private static void solve(int[][] a, int ri, int ci) {
        boolean rotted = false;
        for(int i = 0; i < 4; i++) {
            if(isValid(a,ri + r[i], ci + c[i])) {
                a[ri + r[i]][ci + c[i]] = 2;
                rotted = true;
            }
        }
        if(rotted) count ++;
    }

    private static boolean isValid(int[][] arr, int ri, int ci) {
        return ri >= 0 && ci >= 0 && ri < row && ci < col && arr[ri][ci] != 0 && arr[ri][ci] != 2;
    }

    public static void main(String arg[]) {
        int[][] oranges = {
                {2,1,1},
                {0,1,1},
                {1,0,1}
        };
        int res = findTimeInMin(oranges);
        System.out.println(" result " + res);
    }
}
