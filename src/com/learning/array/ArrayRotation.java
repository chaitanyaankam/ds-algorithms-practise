package com.learning.array;

public class ArrayRotation {

    public static void main(String arg[]) {
        int[] arr = {1, 2, 3, 4, 5};
        /*System.out.println("pivot of the rotated array "+findPivotOfRotation(arr, 0 , arr.length-1));
        System.out.println("number of left rotations "+(arr.length- findPivotOfRotation(arr, 0 ,arr.length-1)));
        System.out.println("number of right right rotations "+ findPivotOfRotation(arr, 0, arr.length-1));
        System.out.println("find Element in Rotated Array "+ findElementInRotatedArray(arr, 7));
        System.out.println("find Element in Rotated Array "+ findElementInRotatedArray(arr, 2));*/
        findMinimumElementInSortedAndRotatedArray(arr);
    }

    public static int findMinimumElementInSortedAndRotatedArray(int[] a){ // 5 6 7 8 1 2 3 4
        int n = a.length, l=0, r= n-1, p = 0, temp = 0;
        while(l<r && l < n-1 && r >= 0) {
            p = l+(r-l)/2;
            if(p < r && a[p] > a[p+1]) {
                temp = p+1;
                break;
            }
            if(p > l && a[p-1] > a[p]) {
                temp = p;
                break;
            }
            if(a[l]>=a[p]) r = p-1;
            else l = p+1;
            p = 0;
        }
        System.out.println("p "+temp+" a[temp] "+a[temp]);
        return a[temp];
    }

    public static int findPivotOfRotation(int[] a, int l, int r) {
        if(r < l) return -1;
        if(r == l) return l;

        int pos = l+(r-l)/2;
        if(pos < r && a[pos] > a[pos+1]) return pos+1;
        else if(pos > l && a[pos] < a[pos-1]) return pos;
        return (a[l] >= a[pos]) ? findPivotOfRotation(a, l, pos-1) : findPivotOfRotation(a, pos+1, r);
    }

    public static int findElementInRotatedArray(int[] a, int element) {
        int n = a.length-1;
        int pivot = findPivotOfRotation(a, 0, n);
        if(element == a[n]) return n-1;
        return (element < a[n]) ? binarySearch(a, pivot, n, element) : binarySearch(a, 0, pivot, element);
    }

    public static int binarySearch(int[] a, int l, int r, int elem){
        if(r>=l){
            int pos = l+(r-l)/2;
            if(a[pos] == elem) return pos;
            return elem < a[pos] ? binarySearch(a, l , pos-1, elem ) : binarySearch(a, pos+1, r, elem);
        }
        return -1;
    }

}
