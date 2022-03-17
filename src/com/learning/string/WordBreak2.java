package com.learning.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Word Break Problem: Given a string and a dictionary of words,
 * determine if the string can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * */
public class WordBreak2 {

    public static void main(String arg[]) {
        String s = "Wordbreakproblem";
        String[] dictionary = { "this", "th", "is", "famous", "Word",
                "break", "b", "r", "e", "a", "k", "br", "bre", "brea", "ak", "problem"};
        Set<String> d = Arrays.stream(dictionary).collect(Collectors.toSet());
        List<String> result = new ArrayList<>();
        int[] lookup = new int[s.length() + 1];
        solve(s, d, result);
        boolean canSolve = canSolve(s, d, lookup);
        System.out.println(canSolve);
    }

    public static boolean canSolve(String s, Set<String> d, int[] lookup) {
        int len = s.length();
        if(len == 0 || lookup[len] != 0) return true;
        for(int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if(d.contains(prefix) && canSolve(s.substring(i, s.length()), d, lookup)) {
                lookup[i] = 1;
                return true;
            }
        }
        return lookup[len] == 1;
    }

    public static void solve(String s, Set<String> d, List<String> res) {
        if(s.length() == 0) {
            System.out.println(res.stream().collect(Collectors.joining(" ")));
            return;
        }
        for(int i = 0; i <= s.length(); i++) {
            String curr = s.substring(0, i);
            if(d.contains(curr)) {
                res.add(curr);
                solve(s.substring(i, s.length()), d, res);
                res.remove(res.size() - 1);
            }
        }
    }
}
