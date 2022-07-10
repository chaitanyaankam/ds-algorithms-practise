package com.learning.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class StirngWindowMinLen {

    private static String findSmallerWindow(String s, Set<Character> items) {
        int start = 0, startIndex = 0, minLen = Integer.MAX_VALUE, index = 0;
        Set<Character> tempSet = new HashSet<>();
        while(index <= s.length()) {
            if(tempSet.size() == items.size()) {
                if(minLen <= index - start) {
                    startIndex = start;
                    minLen = index - start;
                    start = start;
                    tempSet.clear();
                }
            } else if(items.contains(s.charAt(index)))
                tempSet.add(s.charAt(index));
            index++;
        }
        return s.substring(startIndex, minLen + startIndex + 1);
    }
}
