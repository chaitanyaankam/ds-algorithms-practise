package com.learning.backtracking;

public class FindingSubSets {

    public static void main(String[] arg) {
        findSubSets("abc", 0, "");
    }

    public static void findSubSets(String s, int i, String out) {
        if(i == s.length()) {
            System.out.print(out +" ");
            return;
        }
        findSubSets(s, i + 1, out + s.charAt(i));
        if(!out.isEmpty()) out.substring(0, out.length() - 1);
        findSubSets(s, i + 1, out);
    }
}
