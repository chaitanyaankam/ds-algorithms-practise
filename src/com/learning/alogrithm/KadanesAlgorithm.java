package com.learning.alogrithm;

public class KadanesAlgorithm {

    public static void main(String arg[]) {
        int[] a = {1, -2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(largestContiguousSubArraySum(a));
    }

    public static int largestContiguousSubArraySum(int[] arr) {
        int max = Integer.MIN_VALUE, currMax = 0;
        for(int i = 0; i < arr.length; i++) {
            currMax += arr[i];
            if(currMax > max) max = currMax;
            if(currMax < 0) currMax = 0;
        }
        return max;
    }
}
