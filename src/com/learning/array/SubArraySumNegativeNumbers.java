package com.learning.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Hint: calculate prefix sum
 * */
public class SubArraySumNegativeNumbers {

    private static int[] solve(int[] a, int x) {
        int sum = 0, n = a.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            sum += a[i];
            int key = x - sum;
            if(key == 0) return new int[] {0, i};
            else if(map.containsKey(key)) return new int[] {map.get(key) + 1, i};
            else map.put(sum, i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String arg[]) {
        int[] a = {10, 2, -2, -20, 10};
        int[] result = solve(a, -10);
        System.out.println(result[0] +" "+result[1]);
    }
}
