package com.learning.slidingwindow;

/**
* This problem was asked by Amazon.
 *
 * Run-length encoding is a fast and simple method of encoding strings.
 * The basic idea is to represent repeated successive characters as a single count and character.
 * For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
* */
public class RunLength {

    private static String runLength(String s) {
        char prev = s.charAt(0);
        int count = 1;
        StringBuilder result = new StringBuilder();

        for(int i = 1; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(curr == prev) count++;
            else {
                result.append(count +""+prev);
                prev = curr;
                count = 1;
            }
        }
        result.append(count +""+prev);
        return result.toString();
    }

    public static void main(String arg[]) {
        String res = runLength("AAAABBBCCCC");
        System.out.println(res);
    }
}
