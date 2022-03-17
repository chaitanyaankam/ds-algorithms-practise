package com.learning.string;

public class WordBreak {

    public static void main(String[] arg) {
        String s = "The quick brown fox jumps over the lazy dog";
        //System.out.println(isPangram(s));
        int sal = 2932500;
        System.out.println("45% "+(sal + sal*0.45));
        System.out.println("42% "+(sal + sal*0.42));
        System.out.println("40% "+(sal + sal*0.40));
        System.out.println("40% "+(sal + sal*0.38));
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
