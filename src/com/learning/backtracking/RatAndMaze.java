package com.learning.backtracking;

import java.util.ArrayList;
import java.util.List;

// 0 means the block is a dead end and
// 1 means the block can be used in the path from source to destination
//a more complex version can be that the rat can move in 4 directions
// and a more complex version can be with a limited number of moves.
public class RatAndMaze {

    public static void main(String arg[]) {
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 1, 0},
                {1, 1, 1, 1}
        };
        boolean[][] visited = new boolean [maze.length][maze[0].length];
        boolean isPossibleToReachEnd = isPathPossible(0 , 0, maze, visited);
        System.out.println(isPossibleToReachEnd);

        visited = new boolean [maze.length][maze[0].length];
        List<String> result = new ArrayList<>();
        getAllPaths(0, 0, maze, visited, "", result);
        result.stream().forEach(x -> System.out.println(x));
    }

    public static void getAllPaths(int row, int col, int[][] maze, boolean[][] visited, String path, List<String> result) {
        if(row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || visited[row][col] || maze[row][col] == 0)
            return;

        if(row == maze.length - 1 && col == maze[0].length - 1) {
            path += "(" + row + "," + col+")";
            result.add(path);
        }

        visited[row][col] = true;

        getAllPaths(row - 1, col, maze, visited, path +"(" + row + "," + col+")", result);
        getAllPaths(row + 1, col, maze, visited, path +"(" + row + "," + col+")", result);
        getAllPaths(row,  col + 1, maze, visited, path +"(" + row + "," + col+")", result);
        getAllPaths(row,  col - 1, maze, visited, path +"(" + row + "," + col+")", result);

        visited[row][col] = false;
    }

    public static boolean isPathPossible(int row, int col,int[][] maze, boolean[][] visited) {
        if(row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || visited[row][col] || maze[row][col] == 0)
            return false;

        if(row == maze.length - 1 && col == maze[0].length - 1)
            return true;

        visited[row][col] = true;

        boolean top = isPathPossible(row - 1, col, maze, visited);
        if(top) return true;

        boolean right = isPathPossible(row, col + 1, maze, visited);
        if(right) return true;

        boolean bottom = isPathPossible(row + 1, col, maze, visited);
        if(bottom) return true;

        boolean left = isPathPossible(row, col - 1, maze, visited);
        if(left) return true;

        visited[row][col] =  false;
        return false;
    }

}
