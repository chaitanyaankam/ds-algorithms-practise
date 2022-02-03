package com.learning.array;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeOverlappingMeetings {

    public static void main(String[] arg) {
        int[][] a = {
                {1, 10},
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        a = mergeOverlappingMeetings(a);
        for (int[] item: a) System.out.println(item[0]+" "+item[1]);
    }

    public static int[][] mergeOverlappingMeetings(int[][] a) {
        //Arrays.sort(a, Comparator.comparing((int[] x) -> x[0]));
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparing((int[] x) -> x[1]));
        for(int i = 0; i < a.length; i++) {
            if(!heap.isEmpty() && heap.peek()[1] > a[i][0]) {
                int[] min = heap.poll();
                min[1] = a[i][1];
                heap.offer(min);
            } else heap.add(a[i]);
        }
        int[][] result = new int[heap.size()][heap.size()];
        int i = 0;
        while (!heap.isEmpty()) {
            result[i] = heap.poll();
            i++;
        }
        return result;
    }
}
