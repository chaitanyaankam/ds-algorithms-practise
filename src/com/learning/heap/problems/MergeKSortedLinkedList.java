package com.learning.heap.problems;

import java.rmi.UnexpectedException;
import java.util.*;

public class MergeKSortedLinkedList {

    public static void main(String[] arg) {
        Node n3 = new Node(3, null);
        Node n1 = new Node(1, n3);
        Node n4 = new Node(4, null);
        Node n2 = new Node(2, n4);

        List<Node> list = Arrays.asList(new Node[]{n1, n2});
        Node head = solve(list);

        while(!Objects.isNull(head)) {
            System.out.print(head.data +" ");
            head = head.next;
        }
    }

    private static Node solve(List<Node> list) {
        Comparator<Node> sortingOrder = (n1, n2) -> n1.data > n2.data ? 1 : n1.data < n2.data ? -1 : 0;
        PriorityQueue<Node> minHeap = new PriorityQueue<>(sortingOrder);
        list.forEach(node-> minHeap.offer(node));

        Node head = null, curr = null;
        while(!minHeap.isEmpty()) {
            if(Objects.isNull(head)) {
                head = minHeap.poll();
                curr = head;
            } else {
                curr.next = minHeap.poll();
                curr = curr.next;
            }

            if(curr.next != null)
                minHeap.offer(curr.next);
        }
        return head;
    }
}

class Node {
    int data;
    Node next;

    public Node(int data, Node n) {
        this.data = data;
        this.next = n;
    }
}
