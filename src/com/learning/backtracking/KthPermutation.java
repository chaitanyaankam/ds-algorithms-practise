package com.learning.backtracking;

import java.util.ArrayList;
import java.util.List;

public class KthPermutation {

    public static void main(String arg[]) {
        getPermutation(3, 4);
    }

    public static void getPermutation(int a, int b) {
        StringBuilder str = new StringBuilder();
        for(int i = 1; i <= a; i++)
            str.append(i);
        List<String> result = new ArrayList<>();
        String res = kthPermute(str.toString(), 0, str.length()-1, result, b);
        System.out.println("result "+res);
    }

    public static String kthPermute(String str, int l, int r, List<String> result, int b) {
        if(l == r) {
            result.add(str);
            return (result.size() == b) ? str : "";
        }
        for(int i = l; i <= r; i++) {
            str = swap(str, l, i);
            String temp = kthPermute(str, l+1, r, result, b);
            if(!temp.isBlank()) return temp;
            str = swap(str, l, i);
        }
        return "";
    }

    private static void permute(String str, int l, int r) {
        if (l == r)
            System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }

    public static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
