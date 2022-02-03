package com.learning.slidingwindow;

import java.util.Arrays;

public class UniqueSubStringImpl {

    //find Largest subString with Unique chracters
    public static void main(String[] arg) {
        System.out.println(findLargestSubStringUnique("prateekbhaiya"));
    }

    private static int findLargestSubStringUnique(String s) {
        int[] chars = new int[256];
        Arrays.fill(chars, -1);
        int i = 0, count = 0, max = Integer.MIN_VALUE, start = 0, start_index = 0;

        while (i < s.length()) {
            int val = (int) s.charAt(i);
            if(chars[val] > 0  && chars[val] >= start) {
                if(count > max) {
                    max = count;
                    start_index = start;
                }
                start = i;
                count = 0;
            }

            chars[val] = i;
            count++;
            i++;
        }

        if(count > max) {
            max = count;
            start_index = start;
        }

        System.out.println(s.substring(start_index, start_index + max));
        return max;
    }
}
