package com.learning.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public static void main(String[] arg) {
        int[] a = {4, 8, 23, 1, 2, 3, 42, 7};
        int k = 12;
        List<Integer> innerResult = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        //getAllCombinations(a, k, 0, innerResult, result);
        //getAllCombinations_withDuplicates(a, k, innerResult, result, 0);
        Arrays.sort(a);
        getAllUniqueCombinations(a, k, 0, innerResult, result);

        for(List<Integer> item: result) {
            item.stream().forEach(x -> System.out.print(x + " "));
            System.out.println("");
        }
    }

    public static void getAllCombinations(int[] a, int k, int index,
                                          List<Integer> innerResult,
                                          List<List<Integer>> result) {
        if(k == 0) {
            result.add(new ArrayList<>(innerResult));
            return;
        }

        if(k < 0 || index > a.length - 1) return;

        innerResult.add(a[index]);
        getAllCombinations(a, k - a[index], index + 1, innerResult, result);

        innerResult.remove(innerResult.size() - 1);
        getAllCombinations(a, k, index + 1, innerResult, result);
    }

    public static void getAllCombinations_withDuplicates(int[] a, int k,
                                                         List<Integer> innerResult,
                                                         List<List<Integer>> result, int index) {
        if(k < 0) return;
        if(k == 0) {
            result.add(new ArrayList<>(innerResult));
            return;
        }

        for( int i = index; i < a.length; i++) {
            innerResult.add(a[i]);
            getAllCombinations_withDuplicates(a, k - a[i], innerResult, result, i);
            innerResult.remove(innerResult.size() -  1);
        }
    }

    public static void getAllPermutations_withDuplicates(int[] a, int k,
                                                         List<Integer> innerResult,
                                                         List<List<Integer>> result) {
        if(k < 0) return;
        if(k == 0) {
            result.add(new ArrayList<>(innerResult));
            return;
        }

        for( int i = 0; i < a.length; i++) {
            innerResult.add(a[i]);
            getAllPermutations_withDuplicates(a, k - a[i], innerResult, result);
            innerResult.remove(innerResult.size() -  1);
        }
    }

    public static void getAllUniqueCombinations(int[] a, int k, int index,
                                                List<Integer> innerResult,
                                                List<List<Integer>> result) {

        if(k == 0){
            result.add(new ArrayList<>(innerResult));
            return;
        }

        if(k < 0) return;

        for (int i = index; i < a.length; i++) {
            if (i > index && a[i] == a[i - 1])  continue; // skipping iteration of duplicate element
            innerResult.add(a[i]);
            getAllUniqueCombinations(a, k - a[i], i + 1, innerResult, result);
            innerResult.remove(innerResult.size() - 1);
        }
    }
}
