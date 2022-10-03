package com.learning.string;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given a string s and an integer k, break up the string into multiple lines
 * such that each line has a length of k or less. You must break it up so
 * that words don't break across lines. Each line has to have the maximum possible amount of words.
 * If there's no way to break the text up, then return null.
 * */
public class WordBreak1 {

    private static String[] solve(String str, int k) {
        StringBuilder sb = new StringBuilder();
        String delimiter = ";";
        int start = 0, end = 0, count = 0, n = str.length(), space = -1;

        for(int i = 0; i < n; i++) {
            count++;
            if(str.charAt(i) == ' ') {
                space = i;
                continue;
            }
            if(count <= k)
                continue;
            if (space == -1 || space < start)
                return null;

            end = space;
            end++;

            sb.append(str.substring(start, end).trim()).append(delimiter);
            count = i - end + 1;
            start = end;
        }
        sb.append(str.substring(start, n));
        return sb.toString().split(delimiter);
    }


    public static void main(String arg[]) {
        String str = "the quick brown fox jumps over the lazy dog";
        String[] expectedAnswer =  {"the quick", "brown fox", "jumps over", "the lazy", "dog"};
        int k = 10;
        String[] res = solve(str, k);
        if(res == null)
            System.out.println(res);
        else {
            for(String s: res) System.out.println(s);
        }

    }
}
