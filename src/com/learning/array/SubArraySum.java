package com.learning.array;

public class SubArraySum {

    private static int[] solve(int[] a, int x) {
        int start = 0, sum = 0, n = a.length;
        for(int i = 0; i < n; i++) {
            sum += a[i];
            while(sum > x & start < i)
                sum -= a[start++];
            if(sum == x)
                return new int[] {start, i};
        }
        return new int[]{-1, -1};
    }

    public static void main(String arg[]) {
        //int[] a = {1, 2, 3, 7, 5};
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        int[] result = solve(a, 15);
        System.out.println(result[0] +" "+result[1]);
    }
}
