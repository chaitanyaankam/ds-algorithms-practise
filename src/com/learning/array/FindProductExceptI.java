package com.learning.array;

/**
 * Good morning! Here's your coding interview problem for today.
 * This problem was asked by Uber.
 * Given an array of integers,
 * return a new array such that each element at index i of
 * the new array is the product of all the numbers in the original array except the one at i.
 *
 * For example, if our input was [1, 2, 3, 4, 5],
 * the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1],
 * the expected output would be [2, 3, 6]
 *
 * is it possible with out division?
 * */
public class FindProductExceptI {

    public static void main(String[] arg) {
        int[] a = {1, 2, 3, 4, 5};
        solve(a);
    }

    public static int[] solve(int[] a) {
        int[] l2r = new int[a.length];
        int[] r2l = new int[a.length];

        //left -> right
        int curr = a[0];
        l2r[0] = 1;
        for(int i = 1; i < a.length; i++) {
            l2r[i] = curr;
            curr = curr * a[i];
        }

        //right -> left
        int n = a.length - 1;
        curr = a[n];
        r2l[n] = 1;

        for(int i = n - 1; i >= 0; i --) {
            r2l[i] = curr;
            curr = curr * a[i];
        }

        for(int i = 0; i < a.length; i++)
            a[i] = l2r[i] * r2l[i];

        return a;
    }
}
