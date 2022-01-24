package com.learning.slidingwindow;

import java.util.ArrayList;
import java.util.List;

/*
* https://www.geeksforgeeks.org/number-subarrays-sum-exactly-equal-k/
* */
public class SlidingWindow {

    public static void main(String arg[]) {
        int[] a = {1, 3, 2, 1, 4, 1, 3, 2, 1, 1, 2};
        int k = 8;
        List<int[]> lists = findWindows(a, k);
        lists.forEach(item->System.out.println(item[0]+" "+item[1]));
    }

    static List<int[]> findWindows(int[] a, int k) {
        List<int[]> result = new ArrayList<>();
        int count = 0, startIndex = 0, n = a.length, i = 0;
        while(i < n) {
            count += a[i];
            i++;

            while(count > k)
                count -= a[startIndex++];

            if(count == k) {
                int[] temp = new int[2];
                temp[0] = startIndex;
                temp[1] = i;
                result.add(temp);
            }
        }
        return result;
    }
}
