package com.learning.Game;

import java.util.Arrays;

public class NutsAndBolts {
    public static void main(String[] arg) {
        char[] nuts =  {'@', '%', '$', '#', '^'};
        char[] bolts = {'%', '@', '#', '$', '^'};
        matchPairs(nuts, bolts, nuts.length);
    }

    public static void matchPairs(char[] nuts, char[] bolts, int n) {
        Arrays.sort(nuts);
        Arrays.sort(bolts);

        for(int i = 0; i < nuts.length; i++)
            System.out.println(nuts[i]+" "+bolts[i]);
    }
}
