package com.learning.dp2d;

import java.util.*;

public class FindWords2D {

    private static Set<String> dictionary = null;
    private static int[] x = {1, 0, -1, 0};
    private static int[] y = {0, 1, 0, -1};

    static boolean solve(char[][] array, boolean[][] visited, int i, int j,
                     StringBuilder sb, Set<String> res) {
        if(i < 0 || j < 0 || i >= array.length || j >= array.length || visited[i][j])
            return false;

        if(dictionary.contains(sb.toString())) {
            res.add(sb.toString());
            System.out.println(sb.toString());
            return true;
        }

        sb.append(array[i][j]);
        visited[i][j] = true;
        boolean found = false;

        for(int k = 0; k < x.length; k++)
            found = found || solve(array, visited, i + x[k], j + y[k], sb, res);

        sb.deleteCharAt(sb.length() - 1);
        if(!found)
            visited[i][j] = false;

        return true;
    }

    public static void main(String[] args) {
        dictionary = new HashSet<>();
        dictionary.addAll(Arrays.asList(new String[]{"eat", "rain", "rat", "in"}));
        char[][] array = {{'e', 'a', 'n'},
                        {'t', 't', 'i'},
                        {'a', 'r', 'a'}};
        boolean[][] visited = new boolean[array.length][array.length];
        Set<String> res = new HashSet<>();
        for(int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                solve(array, visited, i, j, new StringBuilder(), res);
            }
        }
        System.out.println(res.size());
    }
}
