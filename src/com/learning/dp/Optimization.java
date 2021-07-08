package com.learning.dp;

import java.util.*;
import java.lang.*;
import java.io.*;


class Optimization {

    public static void main (String[] args) throws java.lang.Exception {
        System.out.print(eggDrop(2, 10));
    }

    /* You are given N identical eggs and access to a building with k floors.
        Your task is to find the lowest floor that will cause an egg to break,
        if dropped from that floor. Once an egg breaks, it cannot be dropped again.
        If an egg breaks when dropped from the xth floor,
        you can assume it will also break when dropped from any floor greater than x.

        Write an algorithm that finds the minimum number of trial drops it will take,
        in the worst case, to identify this floor.

        For example, if N = 1 and k = 5, we will need to try dropping the egg at every
        floor, beginning with the first, until we reach the fifth floor,
        so our solution will be 5.
    */
    public static int eggDrop(int n, int k) {
        if(k==1 || k==0) return k;
        if(n==1) return k;

        int min = Integer.MAX_VALUE;
        int x = 0, res = 0;

        for(x=1; x<=k; x++) {
            res = Math.max(eggDrop(n-1, x-1), eggDrop(n, k-x));
            min = Math.min(res, min);
        }
        return min+1;
    }
}


