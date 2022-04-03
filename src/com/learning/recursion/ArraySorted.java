package com.learning.recursion;

public class ArraySorted {

    private static boolean isSorted(int[] a, int s, int e) {
        if(s == e) return true;
        if(a[s] < a[s + 1]) return isSorted(a, s + 1, e);
        else return false;
    }
}
