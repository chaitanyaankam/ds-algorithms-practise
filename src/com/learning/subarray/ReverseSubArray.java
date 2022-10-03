package com.learning.subarray;

public class ReverseSubArray {

    private static void reverse(int[] a, int l, int r) {
        l--; r--;
        int mid = (l + (r-1)/2);
        int elem = r - l + 1;
        if(elem % 2 != 0) mid--;

        for(;l <= mid; l++, r--)
            a[l] = a[l] + a[r] - (a[r] = a[l]);

        for(int i: a) System.out.print(i + " ");
    }

    public static void main(String[] arg) {
        //int[] a = {1, 2, 3, 4, 5, 6, 7};
        int[] a = {1, 6, 7, 4};
        reverse(a, 1, 4);
    }
}
