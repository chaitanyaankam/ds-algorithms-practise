package com.learning.array;

public class ContainerWithWater {

    public static void main(String[] arg) {
        int[] a = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(containerWithWater(a));
    }

    public static int containerWithWater(int[] a) {
        int l = 0, r = a.length - 1;
        int maxArea = Integer.MIN_VALUE;

        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(a[l], a[r]) * (r-l) );
            if(a[l] > a[r]) r--;
            else l++;
        }
        return maxArea;
    }
}
