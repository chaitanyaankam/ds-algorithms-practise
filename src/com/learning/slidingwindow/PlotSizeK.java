package com.learning.slidingwindow;

public class PlotSizeK {
    public static void main(String[] arg) {
        int[] a = {1, 3, 2, 1, 4, 1, 3, 2, 1, 1, 2};
        getAllPlots(a, 8);
    }

    public static void getAllPlots(int[] a, int k) {
        if(k == 0 || a.length == 0 || a == null) return;

        int start = 0,  count = 0, i = 0;
        while( i < a.length ) {
            count += a[i++];
            while (count > k && start < a.length)
                count -= a[start++]; //shrink window from beginning
            if(count == k) System.out.println(start +" "+ (i-1));
        }
    }
}
