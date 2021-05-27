package com.learning.companies;

import java.util.*;

class AppleInterview {

    public static void main(String arg[]){
        AppleInterview obj = new AppleInterview();
        obj.addNum(5);
        System.out.println(obj.findMedian());
        obj.addNum(10);
        obj.addNum(20);
        System.out.println(obj.findMedian());
    }

    PriorityQueue<Integer> maxHeap; //smaller elements from middle
    PriorityQueue<Integer> minHeap; //greater elements from middle

    /** initialize your data structure here. */
    public AppleInterview() {
        maxHeap = new PriorityQueue(Collections.reverseOrder());
        minHeap = new PriorityQueue();
    }

    public void addNum(int num) {
        if(maxHeap.size() == 0 || num < maxHeap.peek())
            maxHeap.add(num);
        else minHeap.add(num);

        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (minHeap.size() > maxHeap.size())
            maxHeap.add(minHeap.poll());
    }

    public double findMedian() {
        return (minHeap.size() == maxHeap.size()) ? (minHeap.peek() + maxHeap.peek())/2.0 : maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */