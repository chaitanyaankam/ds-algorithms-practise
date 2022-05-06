package com.learning.SegmentTree;

/**
 * R 1 - 10
 * A 2 - 4
 * J 3 - 7
 * B 13 - 17
 * */
public class IntervalTree {

    IntervalNode insert(IntervalNode root, Interval i) {
        if(root == null) return new IntervalNode(i);
        if(i.low < root.interval.low) root.left = insert(root.left, i);
        else root.right = insert(root.right, i);
        root.max = Math.max(root.max, i.high);
        return root;
    }

    boolean isOverlap(Interval i1, Interval i2) {
        return (i1.low <= i2.high && i2.low <= i1.high);
    }

    Interval overlapSearch(IntervalNode root, Interval i) {
        if(root == null) return null;
        if(isOverlap(root.interval, i)) return root.interval;
        if(root.left != null && root.left.max >= i.low)
            return overlapSearch(root.left, i);
        else return overlapSearch(root.right, i);
    }

    void inOrder(IntervalNode root) {
        if(root == null) return;
        inOrder(root.left);
        System.out.println("[" + root.interval.low+" "+root.interval.high+"], max "+root.max);
        inOrder(root.right);
    }
}

class IntervalNode {
    Interval interval;
    IntervalNode left;
    IntervalNode right;
    int max;

    public IntervalNode(Interval interval) {
        this.interval = interval;
        this.left = null;
        this.right = null;
        this.max = interval.high;
    }
}

class Interval {
    int low;
    int high;

    public Interval(int low, int high) {
        this.low = low;
        this.high = high;
    }
}
