package com.learning.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * We can determine how "out of order" an array A is by counting the number of inversions it has.
 * Two elements A[i] and A[j] form an inversion if A[i] > A[j] but i < j.
 * That is, a smaller element appears after a larger element.
 * Given an array, count the number of inversions it has.
 * Do this faster than O(N^2) time.
 *
 * You may assume each element in the array is distinct.
 * For example, a sorted list has zero inversions.
 * The array [2, 4, 1, 3, 5] has three inversions: (2, 1), (4, 1), and (4, 3).
 * The array [5, 4, 3, 2, 1] has ten inversions: every distinct pair forms an inversion.
 * */
public class Inversions {

    private static HashSet<String> set = new HashSet<>();

    public static void main(String[] arg) {
        int[] a = new int[] { 2, 4, 1, 3, 5 };
        a = mergeSort(a, a.length - 1);
        for(String i: set) System.out.println(i+" ");
    }

    public static int countInversions(int[] a) {
        return 0;
    }

    public static int[] mergeSort(int[] a, int n) {
        if(n == 2)
            return a;
        int mid = (n/2);
        int[] l_arr = Arrays.copyOfRange(a, 0, mid);
        int[] r_arr = Arrays.copyOfRange(a, mid, n);

        mergeSort(l_arr, mid);
        mergeSort(r_arr, n - mid);
        merge(a, l_arr, r_arr, mid);

        return a;
    }

    public static int merge(int[] arr, int[] l, int[] r, int mid) {
        int n = l.length, m = r.length, i = 0, j = 0, k = 0, swaps = 0;

        while(i < n && j < m) {
            if(l[i] <= r[j]) arr[k++] = l[i++];
            else {
                arr[k++] = r[j++];
                swaps += (mid) - (i);
            }
        }
        while(i < n) arr[k++] = l[i++];
        while(j < m) arr[k++] = r[j++];

        return swaps;
    }
}
