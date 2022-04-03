package com.learning.array;

/**
 * You have N stones in a row, and would like to create from them a pyramid.
 * This pyramid should be constructed such that the height of each stone increases
 * by one until reaching the tallest stone, after which the heights decrease by one.
 *
 * In addition, the start and end stones of the pyramid should each be one stone high.
 * You can change the height of any stone by paying a cost of 1 unit to lower its height by 1,
 * as many times as necessary. Given this information,
 * determine the lowest cost method to produce this pyramid.
 *
 * For example, given the stones [1, 1, 3, 3, 2, 1],
 * the optimal solution is to pay 2 to create [0, 1, 2, 3, 2, 1].
 * */
public class Pyramid {

    public static void main(String[] arg) {
        int[] a = {1, 1, 3, 3, 4, 4, 2, 1};
        int res = solve(a);
        System.out.println(res);
    }

    public static int solve(int[] a) {
        int r = a.length - 1, l = 1, mid = l + (r - l)/2, count = 0;
        if(a[0] == a[r]) count++;
        while(l < mid) {
            if(a[l] != a[r]) count++;
            l++;
            r--;
        }
        return count;
    }
}
