package com.learning.string;

import java.util.*;

/*
* LPS
* */
public class LongestPalindromicSubString {

    public static void main(String arg[]) {
        LongestPalindromicSubString lps = new LongestPalindromicSubString();
        String lpsString = "cbbd"; //longest palindrome is racecar
        String reversed = lps.reverseString(lpsString);
        System.out.println(reversed);

        int len = 0, start = 0, end =0;
        for(int i=0; i< lpsString.length(); i++) {
            int len1 = lps.expandAroundCenter(lpsString, i, i);
            int len2 = lps.expandAroundCenter(lpsString, i, i+1);
            len = Math.max(len1, len2);

            if(len > end - start){
                start = i - ((len-1)/2);
                end = i + (len/2);
            }
        }
        System.out.println(lpsString.substring(start, end+1));
    }

    public int expandAroundCenter(String s, int l, int r){
        if(s.length() == 0 || l>r) return 0;
        while(l >= 0 && r < s.length() && s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        return r-l-1;
    }

    private String reverseString(String s){
        StringBuilder strBuilder = new StringBuilder();
        for(int i=s.length()-1; i>=0; i--)
            strBuilder.append(s.charAt(i));
        return strBuilder.toString();
    }

    public List<List<String>> Anagrams(String[] str) {
        Map<String, List<String>> map = new HashMap<>();
        String temp = null;
        for(String s: str) {
            temp = getLexOrder(s);
            if(!map.containsKey(temp))
                map.put(temp, new ArrayList<String>());
            map.get(temp).add(s);
        }
        return (ArrayList) map.values();
    }

    public String getLexOrder(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
