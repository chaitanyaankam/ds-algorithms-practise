package com.learning.dp2d;

import java.util.ArrayList;
import java.util.List;

public class PalindromicPartitioning {

    List<List> ans = new ArrayList<>();
    public static List<List<String>> res = new ArrayList<>();

    public static void main(String[] arg) {
        String s = "racecarannabhge1e";
        List<List<String>> result = topDown(s);
        for (List<String> strings : result) {
            for (String string : strings) {
                System.out.println(string);
            }
        }
    }

    public static List<List<String>> topDown(String s) {
        List<List<String>> res = new ArrayList();
        List<String> part = new ArrayList();
        dfs(s,res,part,0);
        return res;
    }

    public static void dfs(String s, List<List<String>> res, List<String> part, int i) {
        if(i>=s.length()) {
            res.add(new ArrayList<>(part));
            return;
        }
        for(int j = i; j < s.length(); j++) {
            if(isPalindrome(s,i,j)) {
                part.add(s.substring(i,j+1));
                dfs(s,res,part,j+1);
                part.remove(part.size()-1);
            }
        }
    }

    public static boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
