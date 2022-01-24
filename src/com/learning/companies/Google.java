package com.learning.companies;

import com.learning.tree.Tree;

import java.util.*;

public class Google {

    /**
     * arr = {1, 5, 10, 20}
     * n = 11
     * assuming array is sorted and values of in below range
     * 0 < n < 1000
     * 0 < a[i] < 1000
     *
     * for max = 5 (1, 2, 3, 4 and 5) o/p : {1,1,1,1,5}
     * */
    public static void main(String[] arg) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //int[] arr = {1, 5, 10, 20};
        //int n = 8;
        //makeAllCoins(arr, n);

        int[] toys =  { 2,  2, 3, 32, 5};
        int[] shops = {-2, -1, 2, 3,  4};
        int k = 7; // expected output: 34 output: 37
        int pos = 0;
        getMaxToys(toys,shops,k);
    }

    private static void getMaxToys(int[] toys, int[] shops, int k) {
        int currentMax = 0;
        int finalMax = 0;
        for(int i=0; i < toys.length; i++){
            if(Math.abs(shops[i]) > k){
                continue;
            } else {
                int start = shops[i];
                int next = i + 1;
                currentMax = toys[i];

                while(next <= toys.length-1 && shops[next] <= k && shops[next] <= (start < 0 ? 2 * start : start)+ k ) {
                    currentMax += toys[next];
                    next++;
                }
            }
            if(currentMax > finalMax) {
                finalMax =currentMax;
            }

        }
        System.out.println(finalMax);
    }

    static void makeAllCoins(int[] arr, int n) {
        if(n < arr[0] || arr[0] != 1)
            return;
        Set<Integer> set = new HashSet<>();
        for(int i: arr) set.add(i);

        int count = 1, m = arr[0];
        List<Integer> result = new ArrayList<>();
        result.add(arr[0]);

        while(m <= n) {
            if( m == 2 || m == 3 || !isSubsetSum(m, count, result) ) {
                int val = set.contains(m) ? m : arr[0];
                result.add(val);
                count++;
            }
            m++;
        }

        System.out.println("count of coins "+count);
        for (Integer i : result)  System.out.print(i+" ");
    }

    static boolean isSubsetSum(int x, int n, List<Integer> result) { // 2^n
        if(x == 0) return true;
        if(n == 0) return false;
        if(result.get(n-1) > x) return isSubsetSum(x - 1, n - 1, result);
        return isSubsetSum(x - result.get(n-1), n - 1, result) || isSubsetSum(x, n - 1, result);
    }
}
