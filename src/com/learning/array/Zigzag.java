package com.learning.array;

import java.util.Arrays;

public class Zigzag {

    public static void main(String[] arg) {
        String res = traverse("ABCDEFGH", 2);
        System.out.println(res);
    }

    private static String traverse(String s, int k) {
        int n = s.length(), row = 0;
        boolean down = false;
        char[] arr = s.toCharArray();
        String[] res = new String[k];
        Arrays.fill(res, new String());

        for(int col = 0; col < n; col++) {
            res[row] += arr[col];
            if(row == 0 || row == k - 1) down = !down;
            row += down ? 1 : -1;
        }

        StringBuilder sb = new StringBuilder();
        for(String str: res) sb.append(str);
        return sb.toString();
    }
}
