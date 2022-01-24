package com.learning.backtracking;

import java.util.Arrays;

//Return array color of size V that has numbers from 1 to m.
//Note that color[i] represents the color assigned to the ith vertex.
//Return false if the graph cannot be colored with m colors
public class GraphColoring {

    public static void main(String arg[]) {
        int[][] graph = {
                { 0, 1, 1, 1 },
                { 1, 0, 1, 0 },
                { 1, 1, 0, 1 },
                { 1, 0, 1, 0 },
        };
        int m = 4;
        int[] vColors = new int[graph.length];
        boolean canBeColored = graphColoring(graph, 3,0, vColors);
        System.out.println(canBeColored);
        Arrays.stream(vColors).forEach(x -> System.out.print(x+" "));
    }

    private static boolean isSafeToColor(int[][] graph, int[] color) {
        for (int i = 0; i < graph.length; i++)
            for (int j = i + 1; j < graph.length; j++)
                if (graph[i][j] == 1 && color[j] == color[i])
                    return false;
        return true;
    }

    private static boolean graphColoring(int[][] graph, int m, int i, int[] color) {
        if (i == graph.length - 1)
            return isSafeToColor(graph, color);
        for (int j = 1; j <= m; j++) {
            color[i] = j;
            if (graphColoring(graph, m, i + 1, color))
                return true;
            color[i] = 0;
        }
        return false;
    }
}
