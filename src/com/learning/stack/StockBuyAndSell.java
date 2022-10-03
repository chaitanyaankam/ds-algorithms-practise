package com.learning.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of prices where prices[i] is the price of a given stock on an ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Input:  prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * */
public class StockBuyAndSell {

    private static int slove(int[] arr) {
        int maxPro = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            minPrice = Math.min(minPrice, arr[i]);
            maxPro = Math.max(maxPro, arr[i] - minPrice);
        }
        return maxPro;
    }

    private static List<int[]> solve(int[] a) {
        List<int[]> result = new ArrayList<>();
        int min = 0, n = a.length - 1;
        for(int i = 1; i < n -1 ; i++) {
           if(a[i + 1] < a[i]) {
               result.add(new int[] {min, i});
               min = i + 1;
           }
        }
        if(a[min] != a[n - 1])
            result.add(new int[]{min, n - 1});
        return result;
    }

    public static void main(String arg[]) {
        //int[] a = {7, 1, 5, 3, 6, 4};
        int[] a = {100,180,260,310,40,535, 800, 525, 695};
        List<int[]> res = solve(a);
        for(int i = 0; i < res.size(); i++)
            System.out.println(res.get(i)[0]+" "+res.get(i)[1]);
    }
}
