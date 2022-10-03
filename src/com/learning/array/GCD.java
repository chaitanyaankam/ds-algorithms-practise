package com.learning.array;

public class GCD {

    private static int solve(int[] a) {
        int result = a[0];
        for(int element: a) {
            result = gcd(result, element);
            if(result == 1) return 1;
        }
        return result;
    }

    private static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    public static void main(String arg[]) {

    }
}
