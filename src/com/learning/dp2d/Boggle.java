package com.learning.dp2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Boggle is a game played on a 4 x 4 grid of letters.
 * The goal is to find as many words as possible that
 * can be formed by a sequence of adjacent letters in the grid, using each cell at most once.
 * Given a game board and a dictionary of valid words, implement a Boggle solver.
 * */
public class Boggle {

    public static void main(String[] arg) {
        String dictionaryWords[] = {"GEEKS", "FOR", "QUIZ", "GO"};
        char boggle[][]   = {{'G', 'I', 'Z'},
                             {'U', 'E', 'K'},
                             {'Q', 'S', 'E'}};
        boolean[][] visited = new boolean[boggle.length][boggle.length];
        Set<String> dictionary = Arrays.stream(dictionaryWords).collect(Collectors.toSet());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < boggle.length; i++)
            for (int j = 0; j < boggle.length; j++)
                solve(dictionary, boggle, i , j, result, visited);
    }

    public static void solve(Set<String> dictionary, char[][] boggle, int r, int c, StringBuilder result, boolean[][] visited) {
        if(r < 0 || c < 0 || r >= boggle.length || c >= boggle[0].length || visited[r][c])
            return;
        visited[r][c] = true;
        result.append(boggle[r][c]);

        if(dictionary.contains(result.toString()))
            System.out.println(result);

        //8 possible ways
        for (int row = r - 1; row <= r + 1; row++)
            for (int col = c - 1; col <= c + 1; col++)
            solve(dictionary, boggle, row, col, result, visited);

        result.deleteCharAt(result.length() - 1);
        visited[r][c] = false;

        return;
    }
}
