package com.learning.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    public static void main(String[] arg) {
        int[] a = { 1, 2, 3, 4 };
        int k = 2;
        List<List<Integer>> result = new ArrayList<>();

        getAllCombinations(a, k, k, 0, result, "");
        result.stream().forEach(x -> {
            x.stream().forEach(y -> System.out.print(y+" "));
            System.out.println();
        });
    }

    public static void getAllCombinations(int[] a, int size, int k, int i, List<List<Integer>> result, String temp) {
        if(k == 0 || i == a.length) {
            String[] str_arr = temp.split("|");
            List<Integer> innerResult = new ArrayList<>();
            for(String str: str_arr) {
                if(isNumeric(str)) innerResult.add(Integer.parseInt(str));
            }
            if(innerResult.size() == size) result.add(innerResult);
            return;
        }

        getAllCombinations(a, size, k, i + 1, result, temp);
        getAllCombinations(a, size, k - 1, i + 1, result, temp + "|"+ a[i]);
    }

    private static boolean isNumeric(String s) {
        if(s.isEmpty() || s.isBlank())
            return false;
        char[] arr = s.toCharArray();
        for(char c: arr) {
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
