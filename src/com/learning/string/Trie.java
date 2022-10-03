package com.learning.string;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode curr = root;
        for(char l: word.toCharArray())
            curr = curr.children.computeIfAbsent(l, c -> new TrieNode());
        curr.s = word;
        curr.isWord = true;
    }

    public String findCommonPrefix() {
        TrieNode curr = root;
        StringBuilder sb = new StringBuilder();
        while(curr.children.size() == 1) {
            Map.Entry<Character, TrieNode> entry = curr.children.entrySet().iterator().next();
            sb.append(entry.getKey());
            curr = entry.getValue();
        }
        return sb.toString();
    }
}

class TrieNode {
    String s;
    boolean isWord;
    Map<Character, TrieNode> children = new HashMap<>();
}
