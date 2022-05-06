package com.learning.SegmentTree;

import java.util.ArrayList;
import java.util.List;

public class SegmentTree {
    int n;
    int size;
    List<Integer> st;

    public SegmentTree(int n) {
        this.n = n;
        this.size = 4 * n;
        st = new ArrayList<>(this.size);
    }

    public void build(int start, int end, int node, List<Integer> items) {
        int mid = (start + end)/2;
        if(start == end) {
            st.add(node, items.get(start));
            return;
        }
        build(start, mid, 2 * node + 1, st);
        build(start, mid + 1, 2 * node + 2, st);
        st.add(node, st.get(2 * node + 1) + st.get(2 * node + 2));
    }

    public void build(List<Integer> items) {
        build(0, n - 1, 0, items);
    }

    public int query(int start, int end, int l, int r, int node) {
        if(start > r || end < l) return 0;
        if(start >= l && end <= r) return st.get(node);
        int mid = (start + end)/2;
        int q1 = query(start, mid, l, r, 2 * node + 1);
        int q2 = query(mid + 1, end, l , r, 2 * node + 2);
        return q1 + q2;
    }

    public int query(int l, int r) {
        return query(0, n - 1, l, r, 0);
    }

    public void update(int start, int end, int node, int index, int value) {
        if(start == end) {
            st.add(node, value);
            return;
        }
       int mid = (start + end)/2;
       if(index <= mid) update(start, mid, 2 * node + 1, index, value);
       else update(mid + 1, end, 2 * node + 2, index, value);
       st.add(node, st.get(2 * node + 1) + st.get(2 * node + 2));
    }

    public void update(int index, int value) {
        update(0, n - 1, 0, index, value);
    }
}
