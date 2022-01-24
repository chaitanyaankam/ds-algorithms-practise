package com.learning.companies;

import java.util.*;

public class AmazonTest {

    public static void main(String[] arg) {
        //String s ="aabbcc";
        //String str = findLongestSubStringWithKCharacters(s, 1);
        //System.out.println(str);
        //System.out.println(AllParenthesis(3));

        //Set<String> result = new HashSet<>();
        //String s = "madam";
        //generatePalindrome(s.toCharArray(), 0, "", result);
        //int[] cost = new int[] { 1, 12, 5, 111, 200, 1000, 10, 9, 12, 15 };
        //System.out.println(maxToys(cost, 10, 50));

        /*int[] toys =  { 2,  4, 3, 32, 1};
        int[] x =     {-2, -1, 2, 3,  4};
        int k = 1;
        int pos = -1;
        System.out.println(calcMaxToysAtDistance(x, toys, pos, k));*/

        int[] a = {5, 2, 7, 8};
    }

    static int calcMaxToysAtDistance(int[] x, int[] toys, int pos, int k) {
        int n = x.length-1, index = 0;
        for(int i=1; i< n; i++) {
            if(x[i-1] == pos) index = i-1;
            else if(x[i] == pos) index = i;
            else if(x[i-1] < pos && x[i] > pos) index = i;
        }

        System.out.println(index);

        int[] _2d = new int[x.length];
        for(int i=0; i< _2d.length; i++) {
            _2d[i] = 2 * Math.abs(x[i]);
        }

        //index -> n
        int count1 = calculate1d(x, toys, k, index, n);
        System.out.println("count1: "+count1);

        //0 -> index-1
        int count2 = calculate1d(x, toys, k, 0, index-1);
        System.out.println("count2: "+count2);

        //backward -> forward
        int count3 = Integer.MIN_VALUE;
        for(int i=0; i <= n; i++) {
            int toysCount = toys[i];
            int d = k - _2d[i];
            for(int j = i+1; j <= n && (Math.abs(x[j]) <= d); j++) {
                toysCount += toys[j];
            }
            count3 = Math.max(toysCount, count3);
        }
        System.out.println("count3: "+count3);

        //forward -> backward
        int count4 = Integer.MIN_VALUE;
        for(int i= n; i > 0; i--) {
            int toysCount = toys[i];
            int d = k - _2d[i];
            for(int j = i-1; j > 0 && (Math.abs(x[j]) <= d); j--) {
                toysCount += toys[j];
            }
            count4 = Math.max(toysCount, count4);
        }
        System.out.println("count4: "+count4);

        //complexity O(n^2)
        return Math.max(count4,Math.max(count1, Math.max(count2, count3)));
    }

    static int calculate1d(int[] x, int[] toys, int k, int start, int end) {
        int count = 0;
        while (start <= end && Math.abs(x[start]) <=k ) {
            count += toys[start];
            start++;
        }
        return count;
    }

    /*
    *   Input:  N = 10, K =  50
        cost = { 1, 12, 5, 111, 200, 1000, 10, 9, 12, 15 }
        Output: 6
    * */
    static int maxToys(int[] cost, int n, int k) {
        Arrays.sort(cost);
        int count = 0;
        for(int i = 0; i< n && k > 0; i++) {
            k -= cost[i];
            if(k >= 0) count++;
        }
        return count;
    }

    static final int no_of_chars = 256;

    static String findSubString(String str, String pat) {
        int len1 = str.length();
        int len2 = pat.length();
        if (len1 < len2) {
            System.out.println("No such window exists");
            return "";
        }

        int hash_pat[] = new int[no_of_chars];
        int hash_str[] = new int[no_of_chars];

        for (int i = 0; i < len2; i++)
            hash_pat[pat.charAt(i)]++;

        int start = 0, start_index = -1, min_len = Integer.MAX_VALUE;

        int count = 0;
        for (int j = 0; j < len1; j++) {
            hash_str[str.charAt(j)]++;
            if (hash_str[str.charAt(j)] <= hash_pat[str.charAt(j)])
                count++;
            if (count == len2) {
                while (hash_str[str.charAt(start)] > hash_pat[str.charAt(start)] || hash_pat[str.charAt(start)] == 0) {
                    if(hash_str[str.charAt(start)] > hash_pat[str.charAt(start)])
                        hash_str[str.charAt(start)]--;
                    start++;
                }
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_index = start;
                }
            }
        }
        if (start_index == -1) {
            System.out.println("No such window exists");
            return "";
        }
        return str.substring(start_index, start_index + min_len);
    }

    static String findLongestSubStringWithKCharacters(String str, int k) {
        int n = str.length();
        if (n <= 1)
            return str;
        int dist_count = 0;

        boolean[] visited = new boolean[no_of_chars];
        Arrays.fill(visited, false);
        for (int i = 0; i < n; i++) {
            if (visited[str.charAt(i)] == false) {
                visited[str.charAt(i)] = true;
                dist_count++;
            }
        }

        int start = 0, start_index = -1;
        int max_len = Integer.MIN_VALUE;

        int count = 0;
        int[] curr_count = new int[no_of_chars];
        for (int j = 0; j < n; j++) {
            curr_count[str.charAt(j)]++;
            if (curr_count[str.charAt(j)] == 1)
                count++;
            while (count > k) {
                curr_count[str.charAt(start)]--;
                start++;
            }
            int len_window = j - start + 1;
            if (max_len < len_window) {
                max_len = len_window;
                start_index = start;
            }
        }
        return str.substring(start_index, start_index + max_len);
    }

    /**
     * aaaabdcab
     * **/
    static String findLongestSubstring(String str) {
        int maxLen = -1, start = 0, startIndex = -1, len = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c, i);
            } else {
                if(map.get(c) >= start){
                    len = i - start;
                    if(len>maxLen){
                        maxLen = len;
                        startIndex = start;
                    }
                    start = map.get(c)+1;
                }
                map.put(c, i);
            }
        }
        return null;
    }

    public static List<String> AllParenthesis(int n)
    {
        List<String> result = new ArrayList<>();
        generate(n , 0, 0, "", result);
        return result;
    }

    public static List<String> generate(int n, int o, int c, String s, List<String> result) {
        if(o == n && c == n) {
            result.add(s);
            return result;
        }
        if(o < n) generate(n, o+1, c, s+"{", result);
        if(c < o) generate(n, o, c+1, s+"}", result);
        return result;
    }

    static void generatePalindrome(char[] c, int i, String s, Set<String> result) {
        if(isValid(s))
            result.add(s);
        if(i >= c.length)
            return;
        generatePalindrome(c, i+1, s+c[i], result);
        generatePalindrome(c, i+1, s, result);
        return;
    }

    static boolean isValid(String str) {
        if(str.isBlank()) return  false;

        char[] c = str.toCharArray();
        int s= 0, e = c.length-1;
        while(s < e) {
            char temp = c[s];
            c[s] = c[e];
            c[e] = temp;
            s++;
            e--;
        }
        return str.equals(new String(c));
    }


}
