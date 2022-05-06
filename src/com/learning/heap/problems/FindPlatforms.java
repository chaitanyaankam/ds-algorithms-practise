package com.learning.heap.problems;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindPlatforms {

    public static void main(String arg[]) {
        int[] a = new int[] {900, 946, 1130, 1210, 1350};
        int[] b = new int[] {910, 1220, 1145, 1310, 1415};

        int result = findPlatforms(a, b);
        System.out.println(result);
    }

    public static int findPlatforms(int[] a, int[] d) {
        Comparator<Train> comparator = (t1, t2) -> t1.end > t2.end ? -1: t1.end == t2.end ? 0 : 1;
        PriorityQueue<Train> queue = new PriorityQueue<Train>( comparator );
        int i = 0, max = Integer.MIN_VALUE;
        while(i < a.length ) {
            Train curr = new Train(a[i], d[i]);
            while(!queue.isEmpty() && queue.peek().end < curr.start)
                queue.poll();
            queue.offer(curr);
            if(queue.size() > max)
                max = queue.size();
            i++;
        }
        return max;
    }
}

class Train {
    int start;
    int end;

    public Train(int start,int end) {
        this.start = start;
        this.end = end;
    }
}
