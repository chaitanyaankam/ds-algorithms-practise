package com.learning.array;

import java.util.*;

/**
 * This problem was asked by Google.
 *
 * Given an array of strictly the characters 'R', 'G', and 'B',
 * segregate the values of the array so that all the Rs come first, the Gs come second, and the Bs come last.
 * You can only swap elements of the array.
 * Do this in linear time and in-place.
 * For example, given the array ['G', 'B', 'R', 'R', 'B', 'R', 'G'], it should become ['R', 'R', 'R', 'G', 'G', 'B', 'B'].
 * */
public class Rearrange {

    private static Map<Character, LinkedList<Integer>> map = new HashMap<>();

    private static void rearrange(char[] c) {
       map.put('G', new LinkedList<>());
       map.put('R', new LinkedList<>());
       map.put('B', new LinkedList<>());

       int i = 0;
       while(i < c.length) {
           char curr = c[i];
           boolean isR = (curr == 'R');
           boolean isG = (curr == 'G');
           boolean hasG = Objects.nonNull(map.get('G')) && !map.get('G').isEmpty();
           boolean hasB = Objects.nonNull(map.get('B')) && !map.get('B').isEmpty();;

           if(isR && (hasG || hasB)) swap(i, map.get(hasG ? 'G' : 'B').pop(), c);
           else if(isG && hasB) swap(i, map.get('B').pop(), c);
           else map.get(curr).addLast(i++);
       }
        for (char character : c) System.out.print(character+ " ");
    }

    private static void swap(int x, int y, char[] c) {
        map.get(c[y]).addLast(x);
        map.get(c[x]).addFirst(y);

        char temp = c[x];
        c[x] = c[y];
        c[y] = temp;
    }

    public static void main(String arg[]) {
        rearrange(new char[]{'G', 'B', 'R', 'R', 'B', 'R', 'G'});
    }
}
