package com.learning.string;

import java.util.ArrayList;
import java.util.List;

public class StringSplitK {

    public static void main(String[] arg) {
        String s = "the quick brown fox jumps over the lazy dog";
        int k = 10;
        boolean res = solve(s, k);
        System.out.println(res);
    }

    public static boolean solve(String s,int k) {
       int i = 0, j = 0;
       List<String> result = new ArrayList<>();
       for(i = 0, j = k; j < s.length(); i = i + k, j = j + k) {
           if(!isValid(s, j - 1)) return false;
           result.add(s.substring(i, j).trim());
       }
       result.add(s.substring(i,s.length()).trim());
       result.stream().forEach(System.out::println);
       return true;
    }

    public static boolean isValid(String s, int index) {
        return ( s.charAt(index) == ' ' || (index + 1 < s.length() && s.charAt(index + 1) == ' ') ) ? true : false;
    }
}
