package com.learning.linkedlist;

import java.util.HashMap;
import java.util.Map;

class Test {

    /**
     * clone a linked list https://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
     * pivoting the elements
     * print data linked list from tail to nth node
     * Loop detection; Loop break by removing node
     * Even or odd (runner technique)
     * pair wise reversal
     * adding of linked list
     * Adding linked list with carry
     * */
    public static void main(String arg[]) {
        DoublyNode d1 = new DoublyNode(1);
        DoublyNode d2 = new DoublyNode(2);
        DoublyNode d3 = new DoublyNode(3);
        DoublyNode d4 = new DoublyNode(4);

        d1.next = d2;
        d2.next = d3;
        d3.next = d4;
        d4.next = null;

        d3.any = d1;
        d4.any = d2;

        cloneLinkedList(d1);
    }

    /**
     * Partitioning a linked list around a given value and keeping the original order
     * */
    public void partitionUsingPivot(){

    }



    public static DoublyNode cloneLinkedList(DoublyNode head) {
        Map<Integer, DoublyNode> map = new HashMap<>();
        DoublyNode original = head, cloned = null;

        while(original != null) {
            map.put(original.data, new DoublyNode(original.data));
            if(cloned == null) map.get(original.data);
            original = original.next;
        }

        original = head;
        while(original != null) {
            DoublyNode n = map.get(original.data);
            n.next = map.get(original.next.data);
            n.any = map.get(original.any.data);
        }
        return cloned;
    }
}

class DoublyNode {
    int data;
    DoublyNode next;
    DoublyNode any;

    public DoublyNode() {}

    public DoublyNode(int data) {
        this.data = data;
    }
}


