package com.learning.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoom {

    public static void main(String[] arg) {
        int[][] a = {
                {0, 30},
                {5, 10},
                {15, 20},
        };
        System.out.println(canAttend(a));
        System.out.println(meetingRoomsRequired(a));
    }

    public static int meetingRoomsRequired(int[][] a) {
        Arrays.sort(a, Comparator.comparing((int[] x) -> x[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int count = 0;

        for(int[] item: a) {
            if(heap.isEmpty()) {
                heap.offer(item[1]);
                count++;
            } else {
                if(item[0] >= heap.peek()) heap.poll();
                else count++;
                heap.offer(item[1]);
            }
        }
        return count;
    }

    public static boolean canAttend(int[][] a) {
        quickSort(a, 0 , a.length - 1);
        //for(int i = 0; i < a.length; i++) System.out.println(a[i][0]+" "+a[i][1]);
        for(int i = 1; i < a.length; i++)
            if(a[i - 1][1] > a[i][0]) return false;
        return true;
    }

    public static void quickSort(int[][] a, int s, int e) {
        if(s >= e) return ;
        int p = partition(a, s, e);
        quickSort(a, p + 1, e);
        quickSort(a, s, p - 1);
    }

    public static int partition(int[][] a, int s, int e) {
        int p = e, w = s - 1;
        for(int r = s; r < e; r++) {
            if(a[r][0] < a[p][0]) {
                w++;
                int[] temp = a[w];
                a[w] = a[r];
                a[r] = temp;
            }
        }

        w++;
        int[] temp = a[w];
        a[w] = a[p];
        a[p] = temp;
        return w;
    }
}
