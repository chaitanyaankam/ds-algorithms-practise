package com.learning.backtracking;

public class Maximal {
    public static void main(String arg[]) {
        Result result = new Result();
        System.out.println(maximal("12345".toCharArray(), 4, result));
    }

    public static String maximal(char[] s, int b, Result result) {
        if( b == 0 )
            return result.max;

        for(int i = 0; i < s.length; i++) {
            for(int j = i + 1; j < s.length; j++) {
                if(s[j] < s[i])
                    continue;

                char temp = s[i];
                s[i] = s[j];
                s[j] = temp;

                String str = new String(s);
                if(result.max.compareTo(str) < 0)
                    result.max = str;

                maximal(s, b-1, result);

                temp = s[j];
                s[j] = s[i];
                s[i] = temp;
            }
        }
        return result.max;
    }
}

class Result {
    static String max = "";
}
