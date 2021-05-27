package com.learning.array;

import java.util.*;

public class Other {

    public static void main(String arg[]){
        //fizzBuzz(15).forEach(System.out::println);
        /*int[] a = {0, 0, 0, 0};
        findTriplets(a, 0).forEach(arr->{
            arr.forEach(e->System.out.print(e+" "));
            System.out.println();
        });*/

        //int[] a = {1, 2, 5, 1};
        /*int[] a = {0 , 5, 3, 2, 0};
        int res = findPeak(a, 0, a.length-1);
        System.out.println(res+ " "+a[res]);
        System.out.println(elementInMountain(a, 0));*/

        int[] a = {5, 6, 1, 2, 3, 4, 5, 4, 3, 2, 0, 1, 2, 3, -2, 4};
        int res = longestMountain(a);
        System.out.println(res);
    }

    public static int longestMountain(int[] a) {
        int n = a.length;
        if(n < 3)
            return 0;
        int s = 0, e = 0, max = 0;
        for(int i=1; i<n-1; i++) {
            if(a[i] < a[i-1] && a[i] < a[i+1])
                s = i;

            if(a[i] > a[i-1] && a[i] > a[i+1]) {
                System.out.println(" peak "+i);
                e = i+1;
                while(e<n && a[e] > a[e+1]) {
                    e++;
                    i = s = e;
                }
                System.out.println(" start "+s+" end "+e);
                max = Math.max(e-s+1 , max);
            }
        }
        return max;
    }

    public static int elementInMountain(int[] a, int t) {
        int p = findPeak(a, 0, a.length-1);
        if(a[p] == t) return p;
        int leftPos = binarySearch(a, 0, p-1, t, false);
        return (leftPos != -1) ? leftPos: binarySearch(a, p+1, a.length-1, t, true);
    }

    public static int findPeak(int[]a, int l, int r) { // O(longN)
        if(l>r) return -1;
        if(r-l <= 2) return (a[l] > a[r]) ? l: r;
        int p = l+(r-l)/2;
        if(a[p] > a[p-1] && a[p] > a[p+1]) return p;
        return (a[p] > a[p+1]) ? findPeak(a, l, p-1) : findPeak(a, p+1, r);
    }

    public static int binarySearch(int a[], int l, int r, int t, boolean rev){
        if(l>r) return -1;
        int p = l+(r-l)/2;
        if(a[p]==t) return p;
        return (t < a[p] && !rev)? binarySearch(a, l , p-1, t, rev)
                : binarySearch(a, p+1, r, t, rev);
    }

    public static List<String> fizzBuzz(int n) {
        List<String> a = new ArrayList<>(n);
        for(int i=1;i<=n;i++){
            if(i%3==0 && i%5==0) a.add("FizzBuzz");
            else if(i%5==0) a.add("Buzz");
            else if(i%3==0) a.add("Fizz");
            else a.add(Integer.toString(i));
        }
        return a;
    }

    public static List<List<Integer>> findTriplets(int[] a, int t) {
        int n = a.length;
        if (n< 3) {
            return Collections.emptyList();
        }
        t = 0;
        Arrays.sort(a);

        List<List<Integer>> result = new ArrayList<>();
        int j = 0, k = 0;
        for(int i=0; i<=n-3 && a[i] <= 0; i++) {
            j = i+1;
            k = n-1;
            if (i > 0 && a[i] == a[i - 1]) {
                continue;
            }
            while(j < k){
                if(a[j]+a[k] == t-a[i])
                    result.add(Arrays.asList(a[i], a[j], a[k--]));
                else if(a[j]+a[k]> t-a[i])
                    k--;
                else j++;
            }
        }
        return result;
    }

    public static List<int[]> twoSum(int[] a, int l, int t){
        int n = a.length, i =l, j = n-1;
        List<int[]> result = new ArrayList<>();
        while(i<j){
            if(a[i]+a[j]==t)
                result.add(new int[]{a[i], a[j--]});
            else if(a[i]+a[j]>t)
                j--;
            else i++;
        }
        return result;
    }
}
