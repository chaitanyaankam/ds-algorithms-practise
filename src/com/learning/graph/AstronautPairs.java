package com.learning.graph;

import java.util.*;

public class AstronautPairs {

    private static Map<Integer, Set<Integer>> edges = new HashMap<>();;
    private static Map<Integer, Set<Integer>> group = new HashMap<>();

    public static void main(String[] arg) {
        int n = 4;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 3);
        map.put(1, 2);
        int res = combinations(map, n);
        System.out.println(res);
    }

    private static int combinations(Map<Integer, Integer> pairs, int n) {
        pairs.forEach((v1, v2) -> { //O(k)
            Set<Integer> nbrs = edges.getOrDefault(v1, new HashSet<>());
            nbrs.add(v2);
            edges.put(v1, nbrs);

            nbrs = edges.getOrDefault(v2, new HashSet<>());
            nbrs.add(v1);
            edges.put(v2, nbrs);
        });

        int group_id = 0;
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        for(int i = 0; i < n; i++) { // ~O(n)
            if(visited[i]) continue;
            Set<Integer> groupSet = edges.getOrDefault(group_id, new HashSet<>());
            groupSet.add(i);
            dfs(i, visited, groupSet);

            group.put(group_id, groupSet);
            group_id++;
        }

        //verification
        group.forEach((k, v) -> {
            System.out.print(k + " -> ");
            for(int i: v) System.out.print(i +" ");
            System.out.println();
        });

        int count = 1;
        for(Map.Entry<Integer, Set<Integer>> entry: group.entrySet())
            count = count * entry.getValue().size();

        return count; // O(k + n) ~= O(n)
    }

    //O(n) complexity; n could be vertex count
    private static void dfs(int i, boolean[] visited, Set<Integer> groupSet) {
        visited[i] = true;
        for(int nbr: edges.getOrDefault(i, new HashSet<>())) {
            if(visited[nbr]) continue;
            groupSet.add(nbr);
            dfs(nbr, visited, groupSet);
        }
    }
}
