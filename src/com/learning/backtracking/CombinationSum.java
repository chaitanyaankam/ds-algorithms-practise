package com.learning.backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class CombinationSum {

    public static void main(String[] arg) {
        int[] a = {10, 1, 2, 7, 6, 1, 5};
        Arrays.sort(a);
        int target = 8;
        List<List<Integer>> result = new ArrayList<>();
        Set<String> hash = new HashSet<>();
        getTargetCombination(a, target, 0, result, hash, "");

        result.stream().forEach(x -> {
            x.stream().forEach(y -> System.out.print(y+" "));
            System.out.println();
        });
    }

    private static void getTargetCombination(int[] a, int count, int i, List<List<Integer>> result, Set<String> hash, String temp) {
        if(i == a.length && count !=0 )
            return;

        if(count == 0) {
            if(hash.contains(temp)) return;
            List<Integer> innerResult = Arrays
                    .stream(temp.split("|"))
                    .filter(x -> isNumeric(x)).map(Integer::parseInt).collect(Collectors.toList());
            result.add(innerResult);
            hash.add(temp);
            return;
        }

        getTargetCombination(a,count - a[i], i + 1, result, hash,temp + "|" + a[i]);
        getTargetCombination(a, count, i + 1, result, hash, temp);
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
