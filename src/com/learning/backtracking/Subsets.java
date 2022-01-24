package com.learning.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] arg) {
        int[] a = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        getAllSubsets(a, 0, result, "");
        result.stream().forEach(x -> {
            x.stream().forEach(y -> System.out.print(y+" "));
            System.out.println();
        });
    }

    public static void getAllSubsets(int[] a, int i, List<List<Integer>> result, String temp) {
        if(i == a.length) {
            String[] str_arr = temp.split("|");
            List<Integer> innerResult = new ArrayList<>();
            for(String str: str_arr) {
                if(isNumeric(str)) innerResult.add(Integer.parseInt(str));
            }
            result.add(innerResult);
            return;
        }
        getAllSubsets(a, i + 1, result, temp);
        getAllSubsets(a, i+1, result, temp+"|"+a[i]);
    }

    public static boolean isNumeric(String s) {
        if(s.isEmpty() || s.isBlank())
            return false;
        char[] arr = s.toCharArray();
        for(char c: arr) {
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }
}
