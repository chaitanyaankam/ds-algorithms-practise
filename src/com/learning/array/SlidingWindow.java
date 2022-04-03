package com.learning.array;

import java.util.*;

public class SlidingWindow {

    public static void main(String arg[]) {
       /* slidingWindow(new int[]{1, 3, 2, 1, 4, 1, 3, 2, 1, 1, 1, 2}, 8)
                .stream()
                .forEach(e -> System.out.println(e[0]+" "+e[1]));*/

        //System.out.println(uniqueSubstring("prateekbhaiya")); //ekbhaiy
        //System.out.println(stringWindow("helloe", "loe"));
        //System.out.println(smallestDistinctWindow("aaaaaa"));
        slidingWindowMaximum(new int[]{3, 1, 2, 1, 4, 1, 2, 3, 6}, 4).stream().forEach(System.out::println);
    }

    public static List<Integer> slidingWindowMaximum(int[] a, int k) {// O(n log n)
        int n = a.length, i = 0, max = Integer.MIN_VALUE, nextMax = Integer.MIN_VALUE;
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        while(i < n) {
            queue.offer(a[i]);
            max = Math.max(max, a[i++]);

            if(queue.size() == k) {
                result.add(max);
                max = (max == queue.poll())
                        ? queue.stream().max((x, y) -> x.compareTo(y)).get()
                        : max; //here we can do binary search
            }
        }
        return result;
    }

    public static String smallestDistinctWindow(String s) {
        Map<Character, Integer> fp = new HashMap<>();
        for(Character c: s.toCharArray()) {
            if(fp.containsKey(c)) fp.put(c, fp.get(c)+1);
            else fp.put(c, 1);
        }
        int n = s.length(), start = 0, beginning = 0, count = 0, i = 0, minLen = Integer.MAX_VALUE;
        Map<Character, Integer> fs = new HashMap<>();
        while(i < n){
            //Expand right
            Character c = s.charAt(i++);
            if(fs.containsKey(c)) fs.put(c, fs.get(c)+1);
            else fs.put(c, 1);

            if(fp.containsKey(c) && fs.get(c) == 1)
                count++;

            if(count == fp.keySet().size()) {
                //Compress from beginning only when element not in distinct elements && occurrence more than once
                while(!fp.containsKey(s.charAt(start)) || fs.get(s.charAt(start)) > 1 ) {
                    fs.put(s.charAt(start), fs.get(s.charAt(start)) -1);
                    start++;
                }

                if(minLen > (i-start)) {
                    minLen = (i - start);
                    beginning = start;
                }
            }
        }
        System.out.println("beginning "+ beginning);
        return s.substring(beginning, minLen+beginning);
    }

    public static String stringWindow(String str, String pattern) {
        Map<Character, Integer> fp = new HashMap<>();
        for(Character c: pattern.toCharArray()){
            if(fp.containsKey(c)) fp.put(c, fp.get(c)+1);
            else fp.put(c, 1);
        }

        //Expand right and compress left
        int n = str.length(), i = 0, count = 0, start = 0, beginning = -1 , minLen = Integer.MAX_VALUE;
        Map<Character, Integer> fs = new HashMap<>();
        while(i < n) {
            //Expanding right
            Character c = str.charAt(i++);
            if(fs.containsKey(c)) fs.put(c, fs.get(c)+1);
            else fs.put(c, 1);

            //count how many characters have been matching till now
            if(fp.containsKey(c) && fs.get(c) <= fp.get(c)) count++;

            //If pattern matches then contract from left
            //remove element whose freq more than required & char not in pattern string
            if(count == pattern.length()) {
                while(fp.getOrDefault(str.charAt(start), 0) == 0
                        || fs.get(str.charAt(start)) > fp.getOrDefault(str.charAt(start), 0) ) {
                    fs.put(str.charAt(start) , fs.get(str.charAt(start))-1);
                    start++;
                }

                if((i - start) < minLen) {
                    beginning = start;
                    minLen = (i-start);
                }
            }
        }

        return (beginning == -1) ? "" : str.substring(beginning, beginning+minLen);
    }

    /**largest substring with unique characters*/
    public static String uniqueSubstring(String s){
        Map<Character, Integer> map = new HashMap<>();
        int longest=0, start = 0, beginning = 0;
        for(int end = 0; end < s.length(); end++) {
            if(map.containsKey(s.charAt(end)) && map.get(s.charAt(end)) > start)
                start = map.get(s.charAt(end)) + 1;
            map.put(s.charAt(end), end);
            if((end-start) > longest){
                longest = end-start;
                beginning = start;
            }
        }
        return s.substring(beginning, beginning+longest+1);
    }

    public static List<int[]> slidingWindow(int[] a, int k) {
        List<int[]> result = new ArrayList<>();
        int s = 0, e = 0, n = a.length, count = 0;
        while(e < n) {
            count +=a[e++];
            while(count > k && s < e)
                count -= a[s++];
            if(count == k)
                result.add(new int[]{s, e-1});
        }
        return result;
    }
}
