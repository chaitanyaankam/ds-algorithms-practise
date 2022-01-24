package com.learning.alogrithm;

public class KMPSearchAlgorithm {

    public static void main(String[] arg) {
       //int result = patternSearch("TESTESTESTE TESTESTE".toCharArray(), "TESTE".toCharArray());
       nativeSearch("AABAACAADAABAABA".toCharArray(), "AABA".toCharArray());
    }

    //checks if there ia a suffix which is also a prefix
    public static int KMPSearchAlgorithm(char[] s, char[] p) {
        int[] prefix = new int[s.length];
        return 0;
    }

    public static void nativeSearch(char[] s, char[] p) {
        int m = p.length;
        int n = s.length;
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++)
                if (s[i + j] != p[j]) break;
            if (j == m)
                System.out.println("Pattern found at index " + i);
        }
    }
}
