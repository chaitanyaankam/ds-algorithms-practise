package com.learning.array;

public class FindSquares {

    public static void main(String[] arg) {
        int[] a = {-9, -2, 0, 2, 3};
        int[] res = findSquares(a);
    }

    private static int[] findSquares(int[] a) {
        int l = 0, r = a.length - 1, n = a.length;
        int[] res = new int[n];

        for (int i = n - 1; i >= 0; i --) {
            if(Math.abs(l) > Math.abs(r)){
                res[i] = a[l] * a[l];
                l++;
            } else {
                res[i] = a[r] * a[r];
                r--;
            }
        }
        return res;
    }
}
