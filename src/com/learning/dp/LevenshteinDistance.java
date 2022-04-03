package com.learning.dp;

/**
 * Write a function that takes in two strings and returns the minimum number of
 * edit operations that need to be performed on the first string to obtain the
 * second string.
 * There are three edit operations: insertion of a character, deletion of a
 * character, and substitution of a character for another.
 * */
public class LevenshteinDistance {

    public static void main(String[] arg) {
        String s1 = "abc";
        String s2 = "yabd";
        int res = topDown(s1, s2, s1.length(), s2.length());
        System.out.println(res);
    }

    public static int topDown(String s1, String s2, int i, int j) {
        if(i == 0) return j;
        if(j == 0) return i;

        if(s1.charAt(i - 1) == s2.charAt(i - 1))
            return topDown(s1, s2, i - 1, j - 1);
        else return 1 + min(
                topDown(s1, s2, i - 1, j),
                topDown(s1, s2, i , j - 1),
                topDown(s1, s2, i - 1, j - 1)
        );
    }

    public static int topDown_dp(String s1, String s2, int i, int j) {
        if(i == 0) return j;
        if(j == 0) return i;

        if(s1.charAt(i - 1) == s2.charAt(i - 1))
            return topDown_dp(s1, s2, i - 1, j - 1);
        else return 1 + min(
                topDown_dp(s1, s2, i - 1, j),
                topDown_dp(s1, s2, i , j - 1),
                topDown_dp(s1, s2, i - 1, j - 1)
        );
    }

    private static int min(int x, int y, int z) {
        return Math.min((Math.min(x, y)), z);
    }
}
