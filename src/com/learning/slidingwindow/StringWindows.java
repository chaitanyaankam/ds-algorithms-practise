package com.learning.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class StringWindows {

    public static void main(String[] arg) {
        System.out.println(findSmallestWindow("helllll_worldll0", "llo"));
    }

    public static int findSmallestWindow(String s1, String s2) {
        Map<Character, Integer> p = new HashMap<>();
        for(char c: s2.toCharArray())
            p.put(c, p.getOrDefault(c,0)+1);

        int count = 0, start = 0, min = Integer.MAX_VALUE, i = 0, k = s2.length(), start_index = 0;
        Map<Character, Integer> s = new HashMap<>();

        while( i < s1.length()) {
            char curr = s1.charAt(i);
            s.put(curr, s.getOrDefault(curr, 0) + 1);

            if(p.containsKey(curr) && s.getOrDefault(curr, 0) <= p.get(curr)) count++;
            i++;

            if(count == k) {
                while ( !p.containsKey(s1.charAt(start)) || s.getOrDefault(s1.charAt(start), 0) > p.getOrDefault(s1.charAt(start), 0) ) {
                    if(s.getOrDefault(start, 0) > 0)
                        s.put(s1.charAt(start), s.getOrDefault(s1.charAt(start), 0) - 1);
                    start++;
                }
                if((i - start) < min) {
                    min = i - start;
                    start_index = start;
                }
                start = i++;
                count = 0;
                s.clear();
            }
        }
        System.out.println(s1.substring(start_index, start_index + min));
        return min;
    }
}
