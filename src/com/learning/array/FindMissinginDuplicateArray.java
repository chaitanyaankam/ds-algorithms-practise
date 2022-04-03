package com.learning.array;

/**
 * Given an array of integers, find the first missing positive integer in linear time and constant space.
 * In other words, find the lowest positive integer that does not exist in the array.
 * The array can contain duplicates and negative numbers as well.
 * For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
 *
 * limitation  -inifiniy < a[i] < n
 * */
public class FindMissinginDuplicateArray {

    public static void main(String[] arg) {
        int[] a = {1, 2, 0};
        int result = findMissing(a);
        System.out.println("\n"+result);
    }

    public static int findMissing(int[] a) {
        int i = 0, n = a.length, pos = 0;
        while (i < n) {
            if(a[i] < 1) {
                i++;
                continue;
            }
            pos = a[i] - 1;
            if(pos == i) i++;
            else if(a[pos] == a[i]) {
                a[i] = -1;
                i++;
            } else a[pos] = a[i] + a[pos] - (a[i] = a[pos]);
        }

        for(int x: a) System.out.print(x+" ");

        int missing = -1;
        for(int j = 0; j < n; j++)
            if(j != a[j] - 1)
                return missing = j + 1;

        return missing;
    }
}
