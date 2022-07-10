package com.learning.array;

import java.util.HashMap;
import java.util.Map;

public class PairwiseRearrangement {

    public static void main(String[] arg) {
        int[] arr = {3, 6, 7, 5, 1, 4};
        int[][] pairs = {
                {3, 5},
                {1, 6},
                {4, 7}
        };
        Map<Integer, Integer> pairs_map = new HashMap<>();
        for(int[] pair: pairs) {
            pairs_map.put(pair[0], pair[1]);
            pairs_map.put(pair[1], pair[0]);
        }
        Map<Integer, Integer> pos = new HashMap<>();
        for(int i = 0; i < arr.length; i++)
            pos.put(arr[i], i);

        int res = rearrange(arr, pairs_map, 0, pos);
        System.out.println("min swaps "+ res);
    }

    private static int rearrange(int[] arr, Map<Integer, Integer> pairs,
                                 int index, Map<Integer, Integer> pos) {
        if(index == arr.length)
            return 0;

        int a = pairs.get(arr[index]);
        int b = arr[index + 1];
        int i = pos.get(a);
        int j = pos.get(b);
        pos.put(a, j);
        pos.put(b, i);
        arr[i] = arr[i] + arr[j] - (arr[j] = arr[i]);
        int res1 = rearrange(arr, pairs, index + 2, pos);

        //back tracking
        pos.put(a, i);
        pos.put(b, j);
        arr[i] = arr[i] + arr[j] - (arr[j] = arr[i]);

        a = pairs.get(arr[index + 1]);
        b = arr[index];
        i = pos.get(a);
        j = pos.get(b);
        pos.put(a, j);
        pos.put(b, i);
        arr[i] = arr[i] + arr[j] - (arr[j] = arr[i]);
        int res2 = rearrange(arr, pairs, index + 2, pos);

        return 1 + Math.min(res1, res2);
    }
}
