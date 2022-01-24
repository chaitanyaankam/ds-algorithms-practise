package com.learning.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking {

    public static void main(String[] arg) {
        /*int m[][] = {{1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}};
        int n = 4;
        findPathFrom(m, n, 0, 0, "", new boolean[n][n]);*/

        /*ArrayList<String> result = genIp("50396");
        result.stream().forEach(System.out::println);*/

        //System.out.println(decode("3[b2[ca]]", 0, ""));

        String str ="TEST a TESTEST";
        String pattern = "TEST";
        boolean[] v = new boolean[str.length()];
        int result = patternMatching(str.toCharArray(), pattern.toCharArray(), 0 , 0, 0, v);
        System.out.println(result);
    }

    static int patternMatching(char[] c, char[] p, int i, int j, int count, boolean[] v) {
        if(i > c.length-1 || j > p.length-1 || v[i])
            return count;

        if(j == p.length-1) {
            System.out.println("Pattern found at start index "+ (i-p.length+1)+" end index "+ i);
            return count + 1;
        }

       return (c[i] == p[j]) ? patternMatching(c, p,i+1, j+1, count, v) : patternMatching(c, p, i+1, 0, count, v);
    }

    boolean check(int N, int M, ArrayList<ArrayList<Integer>> Edges) {
        Map<Integer, ArrayList<Integer>> eMap = new HashMap<>();
        Map<Integer, Boolean> vMap = new HashMap<>();

        for(ArrayList<Integer> edge: Edges) {
            eMap.put(edge.get(0), eMap.getOrDefault(0, new ArrayList<Integer>()) );
            eMap.put(edge.get(1), eMap.getOrDefault(1, new ArrayList<Integer>()) );
            eMap.get(0).add(edge.get(1));
            eMap.get(1).add(edge.get(0));
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        return checkPath(eMap, vMap, result, 0);
    }

    static boolean checkPath(
            Map<Integer, ArrayList<Integer>> eMap,
            Map<Integer, Boolean> vMap,
            ArrayList<Integer> result, int index) {

        if(result.size() == vMap.size()-1)
            return true;

        if(vMap.get(index))
            return false;
        vMap.put(index, true);

        List<Integer> edges = eMap.getOrDefault(index, null);
        if(edges == null || edges.isEmpty())
            return false;

        for(Integer e: edges) {
            result.add(e);
            if(checkPath(eMap, vMap, result, index+1))
                return true;
            else
                result.remove(result.size()-1);
        }

        vMap.put(index, false);
        return false;
    }

    static String decode(String s, int i, String temp) {
        if(i == s.length()) return temp;
        if(s.charAt(i) == ']' || s.charAt(i) == '[') return decode(s, i+1, temp);
        boolean isDigit = Character.isDigit(s.charAt(i));
        if(isDigit) {
            int count = s.charAt(i)-'0';
            temp = decode(s, i+1, temp);
            StringBuilder sb = new StringBuilder();
            for(int k=0; k<count; k++)
                sb.append(temp);
            return sb.toString();
        }
        return s.charAt(i) + decode(s, i+1, temp);
    }


    static ArrayList<String> genIp(String s) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> innerResult = new ArrayList<>();
        getIP(s.toCharArray(), -1,  result, innerResult);
        return result;
    }

    static void getIP(char[] c, int index, ArrayList<String> result, ArrayList<String> innerResult) {
        if(isValidIP(innerResult, c)) {
            result.add(String.join(".", innerResult));
            return;
        }

        if(index == c.length)
            return;

        String temp = "";
        for(int j=1; (j<=3 && index+j < c.length); j++) {
            temp += c[index+j];
            innerResult.add(temp);
            getIP(c, index+j, result, innerResult);
            if(!innerResult.isEmpty())
                innerResult.remove(innerResult.size()-1);
        }
    }

    static boolean isValidIP(ArrayList<String> innerResult, char[] c) {
        if(innerResult.isEmpty()) return false;
        if(innerResult.size() != 4) return false;
        int total = 0;
        for(String part: innerResult) {
            total += part.length();
            int i= Integer.parseInt(part);
            if(i<0 || i > 255)
                return false;
        }
        return total == c.length;
    }

    //maze problem
    static void findPathFrom(int[][]m, int n, int row, int col, String path, boolean[][] dp) {
        if(row < 0 || row > n-1 || col < 0 || col > n-1 || dp[row][col] || m[row][col] == 0)
            return;

        if(row == n-1 && col == n-1) {
            System.out.println(path);
            dp[row][col] = false;
            return;
        }

        dp[row][col] = true;

        findPathFrom(m, n, row-1, col, path+"U", dp);
        findPathFrom(m, n, row+1, col, path+"D", dp);
        findPathFrom(m, n, row, col+1, path+"R", dp);
        findPathFrom(m, n, row, col-1, path+"L", dp);

        dp[row][col] = false;
    }
}
