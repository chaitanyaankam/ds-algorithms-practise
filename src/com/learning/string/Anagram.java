package com.learning.string;

import java.util.HashSet;
import java.util.Set;

/*
* Given a word W and a string S, find all starting indices in S which are anagrams of W
* Example: given that W is "ab", and S is "abxaba", return 0, 3, and 4
* */
public class Anagram {

    public static void main(String arg[]) {
        Set<Integer> result = new HashSet<Integer>();
        findAnagramStrings("abxaba", "ab", result);
        result.stream().forEach(x -> System.out.println(x+" "));
    }

    public static void findAnagramStrings(String s, String w, Set<Integer> result) {
        int i = 0, m = w.length(), n = s.length();
        Set<Character> word = new HashSet<>();
        for(char c: w.toCharArray()) word.add(c);

        for(i = 0; i <= n - m; i++) {
            int j = 0;
            while(j < m && word.contains(s.charAt(i + j)))
                j++;
            if(j == m)
                result.add(i);
        }
    }
}
