package com.learning.array;

import java.util.*;

public class PowerSet {

    private static List<List<Integer>> out = new ArrayList<>();

    private static void powerSet(List<Integer> lst, int index, List<Integer> in) {
        if(index == lst.size()) {
            out.add(new ArrayList<>(in));
            System.out.println();
            for(int i: in) System.out.print(i +" ");
            return;
        };

        int curr = lst.get(index);
        in.add(curr);
        powerSet(lst, index + 1, in);

        if(!in.isEmpty()) in.remove(in.size() - 1);
        powerSet(lst, index + 1, in);
    }

    public static void main(String[] arg) {
        List<Integer> in = new ArrayList<>();
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        List<Integer> lst = new ArrayList<>(set);
        powerSet(lst, 0, in);
    }
}
