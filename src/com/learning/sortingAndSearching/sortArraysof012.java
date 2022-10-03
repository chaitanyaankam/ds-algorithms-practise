package com.learning.sortingAndSearching;

import java.util.Arrays;

/**
 *  Given an array consisting of only 0s, 1s and 2s.
 *  Write a program to in-place sort the array without using inbuilt sort functions.
 *  ( Expected: Single pass-O(N) and constant space)
 * */
public class sortArraysof012 {

    private static int[] solve(int[] a) {
        int l = 0, r = a.length - 1;
        while(l < r) {
            if(a[l] > a[r]) a[r] = a[l] + a[r] -(a[l] = a[r]);
            else if(a[l] == a[r]) r --;
            else l++;
        }
        return a;
    }

    private static int[] solveUsingQuickSort(int[] a) {
        Sorting.quickSort(a, 0, a.length - 1);
        return a;
    }

    public static void main(String arg[]) {
        int[] a = {2, 0, 2, 1, 1, 0};
        solve(a);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }
}
