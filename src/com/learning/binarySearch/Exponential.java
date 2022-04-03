package com.learning.binarySearch;

/**
 * Implement integer exponentiation.
 * That is, implement the pow(x, y) function, where x and y are integers and returns x^y.
 * Do this faster than the naive method of repeated multiplication.
 * For example, pow(2, 10) should return 1024.
 * */
public class Exponential {

    public static void main(String[] arg) {
        System.out.println(solve(2, 10));
    }

    /**
     * even odd cases needs to be handled
     * even case: 2^10 = 2^5 * 2^5 we need to solve only 2^5
     * odd case: 2^5 = 2^1 * 2^2 * 2^2
     * */
    public static int solve(int a, int b) {
        if(b == 0) return 1;
        int temp = solve(a, b/2);
        return (b % 2 == 1) ? a * temp * temp : temp * temp;
    }
}
