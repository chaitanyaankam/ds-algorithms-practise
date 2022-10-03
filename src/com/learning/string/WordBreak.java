package com.learning.string;

/**
 * A pangram is a sentence containing every letter in the English Alphabet.
 * */
public class WordBreak {

    public static void main(String[] arg) {
        String s = "The quick brown fox jumps over the lazy dog";
        System.out.println(isPangram(s));
    }

    private static boolean isPangram(String s) {
        char[] arr = s.toCharArray();
        boolean[] res = new boolean[26];
        int index = 0, len = res.length - 1;
        for(char c: arr) {
            index = Character.toLowerCase(c) - 'a';
            if(index < 0 || index > len) continue;
            res[index] = true;
        }
        for(boolean i: res)
            if(!i) return false;
        return true;
    }
}
