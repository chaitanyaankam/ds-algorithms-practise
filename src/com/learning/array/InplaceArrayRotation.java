package com.learning.array;

public class InplaceArrayRotation {

    public static void main(String arg[]) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
    }

    private static void solve(int[] a, int d) {
        int n = a.length;
        d = d % n;
    }

    private static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
