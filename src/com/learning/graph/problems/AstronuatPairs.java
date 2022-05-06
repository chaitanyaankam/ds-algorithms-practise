package com.learning.graph.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Member States in UN are planning to send people to Moon.
 * They want to be from different countries.
 * List of pairs of Astronauts ids from same countries are given
 * Determine how many pairs of astronaut from different countries
 * can they choose.
 * * */
public class AstronuatPairs {

    private static  Map<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] arg) {
        int n = 5;
        int[][] pairs = {
            {0, 1},
            {2, 3},
            {0, 4}
        };
        for(int i = 0; i < n; i++) edges.put(i, new ArrayList<>());
        solve(n, pairs);
    }

    private static int solve(int n, int[][] pairs) {
        for(int[] pair: pairs) {
            edges.get(pair[0]).add(pair[1]);
            edges.get(pair[1]).add(pair[0]);
        }

        int group = 0;
        boolean[] v = new boolean[n];
        Map<Integer, List<Integer>> grouping = new HashMap<>();
        for(int i = 0; i < n; i++) {
            List<Integer> elements = grouping.getOrDefault(group, new ArrayList<>());
            dfs(i, v, elements);
            if(elements.isEmpty()) continue;
            grouping.put(group, elements);
            group++;
        }

        for(Map.Entry<Integer, List<Integer>> entry: grouping.entrySet()) {
            System.out.print("Group "+ entry.getKey()+" -> ");
            for (int i: entry.getValue()) System.out.print(i + " ");
            System.out.println();
        }
        return 0;
    }

    private static void dfs(int i, boolean[] v,  List<Integer> elements) {
        if(v[i]) return;
        v[i] = true;
        elements.add(i);
        for(int adj: edges.get(i))
            dfs(adj, v, elements);
    }
}
