package com.learning.sorting;

public class Searching {

    public static void main(String arg[]) {
        int[] a = new int[]{10, 5, 2, 0, 7, 6, 4};// 0 2 4 5 6 7 10
        System.out.println(quickSelect(a, 0, a.length-1, 5));
        System.out.println(binarySearch(a, 5));
    }

    //@Param k - kth smallest element in array a
    public static int quickSelect(int[] a, int s, int e, int k) {
        int p  = Sorting.partition(a, s, e);
        if(p == k) return a[p];
        return (p < k)
                ? quickSelect(a, p+1,a.length-1, k)
                : quickSelect(a, 0, p-1, k);
    }

    //@Param k - element to be found in given array a
    public static int binarySearch(int[] a, int k) {
        int mid = 0, s = 0, e = a.length;
        while(s <= e){
            mid = s+(e-s)/2;
            if(a[mid] == k) return mid;
            else if(a[mid] < k) s = mid+1;
            else e = mid-1;
        }
        return -1;
    }
}
