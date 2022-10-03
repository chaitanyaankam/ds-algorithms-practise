package com.learning.array;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithZeroSum {

    private static int solve(int[] arr) {
        int sum = 0, max = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i< arr.length; i++){
            sum += arr[i];
            if(sum == 0){
                max = i +1;
                continue;
            }
            if(map.containsKey(sum)) max = Math.max(max, i - map.get(sum));
            else map.put(sum, i);
        }
        return max;
    }

    public static void main(String arg[]) {
        int[] arr = {9, -3, 3, -1, 6, -5};
        int res = solve(arr);
    }
}
