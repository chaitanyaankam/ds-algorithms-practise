package com.learning.string;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringUtil {
    public static void main(String arg[]){
         /*String str = "I liked the movie, acting in the movie is great";
         String s = "movie";
         getOccurrences(str, s).stream().forEach(System.out::println);*/

        /*List<String> result = new ArrayList<>();
        findSubSequences("abbac", "", result);
        Collections.sort(result, (a, b)-> {
            if(b.length() > a.length()) return -1;
            else if(a.length() > b.length()) return 1;
            else return a.compareTo(b);
        });
        result.stream().forEach(System.out::println);*/

        //System.out.println(convertToDigitalTime(125));

        //System.out.print(biggestNumberString(Arrays.asList(30, 3)));

        //System.out.println(compressString("aaabcccccaab"));

        //palindromeBreak("abccba");

        System.out.println(camelCaseWords("This is    CHAITANYA ANKAM FROM wells fargo"));
    }

    /**
     * Replace exactly one character to break palindrome and
     * form a string lexicographically smallest as possible
     * return empty if not possible
     * abccba  132231
     * aa
     * aba
     * a
     * */
    public static String palindromeBreak(String s){
        if(s.length() <= 1) return new String();
        StringBuilder result = new StringBuilder(s);
        for(int i=0; i<s.length()/2; i++) {
            if (s.charAt(i) != 'a') {
                result.setCharAt(i, 'a');
                return result.toString();
            }
        }
        result.setCharAt(s.length()-1, 'b');
        return result.toString();
    }

    public static String camelCaseWords(String s) {
        String[] words = s.split("\\s+");
        return Arrays.stream(words)
                .map(e-> (""+e.charAt(0)).toUpperCase()+(e.substring(1).toLowerCase()))
                .reduce("",(s1, s2) -> s1+" "+s2);
    }

    public static String biggestNumberString(List<Integer> a){
        return a.stream().map(e -> Integer.toString(e)).sorted((x,y)->{
            if(x.length() == y.length())
                return y.compareTo(x);

            if(Integer.parseInt(x+y) > Integer.parseInt(y+x))
                return -1;
            else return 1;

        }).collect(Collectors.joining());
    }

    public static String convertToDigitalTime(int minutes){
        int hours = (int) Math.floor(minutes/60);
        String hh = String.format("%02d", hours); // this can also be done by adding 00 before and substring(n-2 , n) example, 002 -substring(n-2, n)-> 02
        String mm = String.format("%02d", (minutes-hours*60));
        return hh+":"+mm;
    }

    public static List<Integer> getOccurrences(String str,String s) {
        List<Integer> result = new ArrayList<>();
        int index = str.indexOf(s);
        while(index > 0) {
            result.add(index);
            index = str.indexOf(s, index+1);
        }
        return result;
    }

    public static void findSubSequences(String s, String agg, List<String> aggList) {
        if(s.length() == 0) {
            aggList.add(agg);
            return;
        }
        findSubSequences(s.substring(1), agg + s.charAt(0), aggList); // include ith element
        findSubSequences(s.substring(1),  agg, aggList);// don't include ith element
    }

    public static String compressString(String s) {
        char[] cArray = s.toCharArray();
        int count = 0;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i< cArray.length-1; i++){
            if(cArray[i]==cArray[i+1])
                count++;
            else {
                result.append(cArray[i]).append(count+1);
                count = 0;
            }
        }
        result.append(cArray[cArray.length-1]).append(count+1);
        return result.toString();
    }
}
