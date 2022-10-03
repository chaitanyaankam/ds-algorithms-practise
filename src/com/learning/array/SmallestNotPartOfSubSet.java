package com.learning.array;

public class SmallestNotPartOfSubSet {

    private static int solve(int[] a) {
        int res = 1;
        for (int i = 0; i < a.length && a[i]<= res; i++) {
            res += a[i];
        }
        return res;
    }
}
