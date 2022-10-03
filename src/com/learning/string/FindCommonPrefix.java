package com.learning.string;

public class FindCommonPrefix {

    private static String solve(String[] arr) {
        Trie trie = new Trie();
        for(String s: arr) trie.insert(s);
        String s = trie.findCommonPrefix();
        return s;
    }

    public static void main(String arg[]) {
        String[] arr = {"coding", "codeninga", "codeability", "codenear"};
        String s = solve(arr);
        System.out.println(s);
    }
}
