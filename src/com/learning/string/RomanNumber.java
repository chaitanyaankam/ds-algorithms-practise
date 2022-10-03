package com.learning.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol Value
 * I      1
 * V      5
 * X      10
 * L      50
 * C      100
 * D      500
 * M      1000
 *
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * */
public class RomanNumber {

    private static int solve(String str, Map<Character, Integer> map) {
        if(str.length() == 0) return -1;
        int n = str.length(), i = n - 1, num = map.get(str.charAt(i--));
        while(i >= 0) {
            int prev = map.get(str.charAt(i + 1));
            int curr = map.get(str.charAt(i));
            num = (prev > curr) ? num - curr : num + curr;
            i--;
        }
        return num;
    }

    public static void main(String[] arg) {
        String s = "LVIII";
        s = "MCMXCIV";
        Map<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int res = solve(s, map);
        System.out.println(res);
    }
}
