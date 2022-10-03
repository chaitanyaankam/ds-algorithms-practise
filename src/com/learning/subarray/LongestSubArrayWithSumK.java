package com.learning.subarray;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithSumK {

    private static int solve(int[] a, int k) {
        int max = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < a.length; i++) {
            sum += a[i];
            map.put(sum, i);

            int key = sum - k;
            if(key == 0)
                max = Math.max(i + 1, max);
            else if(key > 0 && map.containsKey(key))
                max = Math.max(max, i - map.get(key));
        }

        return max;
    }

    public static void main(String arg[]) {
        //int[] a = {10, 5, 2, 7, 1, 9};
        int[] a = {-1, 2, 3};
        int result = solve(a, 15);
        System.out.println(result);
    }
}
