package com.learning.string;

import java.util.*;

class Node {
    String content;
    ArrayList<Node> children = null;

    public Node(String data) {
        this.content = data;
        this.children = new ArrayList<>();
    }
}

class Solution {

    public static void main(String[] arg) {
        //possibleWords(new int[]{2, 3, 4}, 3);
        //System.out.println(longestPalindrome("rfk"));
        //System.out.println(longestPalindrome("wwpmlmpppppppp"));
        //System.out.println(myAtoi("89789"));
        int result = findPalindromicSubStr("abaab".toCharArray(), 0, "", 0);
        System.out.println(result);
    }

    static int findPalindromicSubStr(char[] arr, int i, String str, int count) {
        if(i > arr.length-1) return count;
        String temp = str+arr[i];
        if(isValid(temp)) {
            System.out.println(temp);
            count++;
        }
        return findPalindromicSubStr(arr, i+1, temp, count) +
                findPalindromicSubStr(arr, i+1, "", 0);
    }

    static boolean isValid(String str) {
        char[] c = str.toCharArray();
        int s = 0, e = c.length-1;
        while(e > s) {
            char temp = c[s];
            c[s] = c[e];
            c[e] = temp;
            s++;
            e--;
        }
        return (str.equals(new String(c)) && str.length()>=2);
    }

    //Function to find list of all words possible by pressing given numbers.
    static ArrayList <String> possibleWords(int a[], int N) {
        char[][] numberPad = new char[][]{
                {}, //0
                {}, //1
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'}
        };

        Node root = new Node("");
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Node> temp = new ArrayList<>();

        for(int i: a) {
            Node n = null;
            while (!queue.isEmpty()) {
                n = queue.poll();
                for(char c: numberPad[i]) {
                    Node newNode = new Node(String.valueOf(c));
                    n.children.add(newNode);
                    temp.add(newNode);
                }
            }
            for(Node nc: temp)
                queue.offer(nc);
            temp.clear();
        }

        ArrayList<String> result = new ArrayList<>();
        constructAllPaths(root, result, "");
        result.stream().forEach(e -> System.out.println(e));
        return result;
    }

    public static void constructAllPaths(Node node, ArrayList<String> paths, String s) {
        if(node.children.isEmpty()) {
            paths.add(s+node.content);
            return;
        }
        for(Node n: node.children)
            constructAllPaths(n, paths, s+node.content);
    }

   public static String longestPalindrome(String s){
        int max = Integer.MIN_VALUE;
        String result = "", temp = "";
        for(int i=0; i<s.length(); i++) {
            temp = expandAround(i, s);
            if(temp.length()> max) {
                max = temp.length();
                result = temp;
            }
        }
        return result;
    }

    public static String expandAround(int i, String str) {
        int n = str.length()-1;
        int s = (i > 0 && i < n && str.charAt(i-1) == str.charAt(i+1))? i-1: i;
        int e = i+1;
        while(s >= 0 && e <= n && str.charAt(s) == str.charAt(e)) {
            s--;
            e++;
        }
        return str.substring( s+1==e ? s : s+1, e);
    }

    static int myAtoi(String str)
    {
        // Initialize result
        int res = 0;

        // Iterate through all characters
        // of input string and update result
        // take ASCII character of corresponding digit and
        // subtract the code from '0' to get numerical
        // value and multiply res by 10 to shuffle
        // digits left to update running total
        for (int i = 0; i < str.length(); ++i)
            res = res * 10 + str.charAt(i)-'0';

        // return result.
        return res;
    }
}
