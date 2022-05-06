package com.learning.array;

//Asked to Pavan Kakani
//TO DO Minimize Maximum Value in an array using 2 operations line 17 & 18
public class MaximumValue {

    public static void main(String arg[]) {
        int[] a = {1, 5, 7, 6};
        int res = findMax(a);
        System.out.println(res);
    }

    public static int findMax(int[] a) {
        int max = Integer.MIN_VALUE;
        for(int index = 2; index <= a.length; index++) {
            int i = index - 1;
            for (int x = 1; x <= a[i]; x++) {
                a[i - 1] = a[i - 1] + x;
                a[i] = a[i] - x;
                max = Math.max(Math.max(a[i-1], a[i]), max);
            }
        }
        return max;
    }
}
