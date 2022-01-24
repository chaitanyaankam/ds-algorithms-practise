package com.learning.linkedlist;

import java.util.List;

public class LinkedListTest {

    public static void main (String[] args) throws java.lang.Exception {
        /**int[] a = new int[] {-5, -1, 2, 5};
        int k = 2;
        int x = 0;
        int firstClosest = findClosest(a, x, k);
        System.out.println("first closest "+firstClosest);**/

    }

    static int findClosest(int[] a, int x, int k) {
        int l = 0, r = a.length - 1, n = a.length - 1;
        while (l < r) {
            int mid = l+(r-1)/2;
            int dMid = Math.abs(a[mid] - x);
            int dK = (mid + k > n) ? Integer.MAX_VALUE : Math.abs(a[mid + k] - x);

            if(dK > dMid) r = mid;
            else return  mid;
        }
        return r;
    }

    static boolean binarySearch(int[] a, int l, int r, int target) {
        if(l > r) return false;
        int mid = (l + r)/2;

        if(a[mid] == target)
            return true;
        else if(target > a[mid])
            return binarySearch(a, mid+1, r, target);
        else
            return binarySearch(a, l, mid-1, target);
    }
}
