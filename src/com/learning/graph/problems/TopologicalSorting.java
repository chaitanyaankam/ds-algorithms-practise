package com.learning.graph.problems;

import java.util.*;

/**
 * We're given a hashmap associating each courseId key with a list of courseIds values,
 * which represents that the prerequisites of courseId are courseIds.
 * Return a sorted ordering of courses such that we can finish all courses.
 * Return null if there is no such ordering.
 * For example, given
 * {
     * 'CSC300': ['CSC100', 'CSC200'],
     * 'CSC200': ['CSC100'],
     * 'CSC100': []
 * },
 * should return ['CSC100', 'CSC200', 'CSCS300'].
 *
 * https://www.geeksforgeeks.org/topological-sorting/
 * */
public class TopologicalSorting {

    public static void main(String[] arg) {
        Map<String, List<String>> edges = new HashMap<>();
        edges.put("CSC300", Arrays.asList("CSC100", "CSC200"));
        edges.put("CSC200", Arrays.asList("CSC100"));
        edges.put("CSC100", Arrays.asList());

        Map<String, Boolean> visited = new HashMap<>();
        List<String> result = solve(edges, visited);
        result.forEach(str -> System.out.println(str));
    }

    private static List<String> solve(Map<String, List<String>> edges,
                                      Map<String, Boolean>      visited) {
        List<String> result = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: edges.entrySet()) {
            String key = entry.getKey();
            List<String> nbrs = entry.getValue();
            for(String nbr: nbrs) {
                if(!visited.getOrDefault(nbr, false)) {
                   visited.put(nbr, true);
                   result.add(nbr);
                }
            }
            if(!visited.getOrDefault(key, false)) {
                visited.put(key, true);
                result.add(key);
            }
        }
        return result;
    }
}
