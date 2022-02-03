package com.learning.slidingwindow;

/**
 * https://stackoverflow.com/questions/14948258/given-an-input-array-find-all-subarrays-with-given-sum-k
 * */
public class CountSubArraysWithSumK {

    public static void main(String arg[]) {
        int[] a = {10, 2, -2, -20, 10};
        int k = -10;
        System.out.println(countSubArraysWithSumK(a, k));
    }

    private static int countSubArraysWithSumK(int[] a, int k) {
        int count = 0, start = 0, subArrayCount = 0;

        for(int i = 0; i < a.length; i++) {
            count += a[i];

            while (count < k && start > 0) {
                count += a[start];
                start--;
            }

            if(count == k) {
                System.out.println(start+" "+i);
                subArrayCount++;
                i = ++start;
                count = 0;
            }
        }
        return subArrayCount;
    }
}
