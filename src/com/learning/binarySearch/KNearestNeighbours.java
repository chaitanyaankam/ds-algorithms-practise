package com.learning.binarySearch;

public class KNearestNeighbours {

    public static void main(String[] arg) {
        //int[] a = {0, 3, 5, 7, 9};
        int[] a = {-4, -1, 3, 6};
        //int k = 3;
        //int x = 4;
        int x = 1;
        int k = 1;
        findKNearestNeighbours(a, k ,x);//O(N)
        System.out.println();
        findKClosestLogN(a, k, x);
    }

    //-4, -1, 1, 3, 6    1
    public static void findKClosestLogN(int a[], int k, int x) {
        int l = 0, r = a.length - k;
        while(l < r) {
            int mid = l + (r - l)/ 2;
            if(x - a[mid] > a[mid + k] - x) l = mid + 1;
            else r = mid;
        }
        for (int i = l ; i < l+k; i++)
            System.out.print(a[i]+" ");
    }

    public static void findKNearestNeighbours(int[] a, int k, int x) {
        int s = 0;
        for(int i = k; i < a.length ; i++) {
            if(Math.abs(a[s]- x) > Math.abs(x - a[i])) s++;
        }
        for (int i = s ; i < s+k; i++)
            System.out.print(a[i]+" ");
    }

}
