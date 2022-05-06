package com.learning.dp;

import java.util.ArrayList;
import java.util.List;

public class PalindromicPartitioning {

    public static List<List<String>> res = new ArrayList<>();

    public static void main(String[] arg) {
        String s = "aab";

        for (List<String> x: res) {
            for (String o : x)
                System.out.print(o + " ");
            System.out.println();
        }
    }

    public static void topDown(String s) {
        //todo
    }
}
