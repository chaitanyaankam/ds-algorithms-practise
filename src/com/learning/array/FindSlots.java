package com.learning.array;

import com.learning.tree.Tree;

import java.util.*;

public class FindSlots {

    public static void main(String[] arg) {
        WorkingHours w1 = new WorkingHours("Ravi", 1 , 10);
        WorkingHours w2 = new WorkingHours("Ashu", 2 , 4);
        WorkingHours w3 = new WorkingHours("Jhon", 3 , 7);
        WorkingHours w4 = new WorkingHours("X", 13 , 17);
        List<WorkingHours> list = Arrays.asList(new WorkingHours[]{w1, w2, w3, w4});
    }

    private static void findSlots(List<WorkingHours> list) {

    }
}

class WorkingHours {
    int start;
    int end;
    String name;

    public WorkingHours(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}
