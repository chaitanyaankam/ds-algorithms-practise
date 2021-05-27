package com.learning.string;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public static void main(String arg[]){
         /*String str = "I liked the movie, acting in the movie is great";
         String s = "movie";
         getOccurrences(str, s).stream().forEach(System.out::println);*/
        findAllSubSequences("abc");
        //System.out.println("abc".substring(2));
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

    public static List<String> findAllSubSequences(String s) {
        findSubSequence(s, "");
        return null;
    }

    public static void findSubSequence(String s, String agg) {
        if(s.length() == 0) {
            System.out.println(agg);
            return;
        }
        findSubSequence(s.substring(1), agg + s.charAt(0)); // include ith element
        findSubSequence(s.substring(1),  agg);// don't include ith element
    }
}
