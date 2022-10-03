package com.learning.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
* Given the head to a singly linked list,
*  where each node also has a “random” pointer
* that points to anywhere in the linked list, deep clone the list.
* */
public class RandomPointer {

    private static Lnode solve(Lnode h) {
        Map<Integer, Lnode> map = new HashMap<>();
        Lnode curr = h;
        while(curr != null) {
            Lnode n = new Lnode(curr.data);
            map.put(curr.data, n);
            curr = curr.next;
        }

        Lnode temp = h;
        while(temp != null) {
            curr = map.get(temp.data);
            curr.next = (temp.next != null) ? map.get(temp.next.data) : null;
            curr.random = (temp.random != null) ? map.get(temp.random.data) : null;
            temp = temp.next;
        }
        return map.get(h.data);
    }

    public static void main(String arg[]) {
        Lnode _1 = new Lnode(1);
        Lnode _2 = new Lnode(2);
        Lnode _3 = new Lnode(3);
        Lnode _4 = new Lnode(4);
        Lnode _5 = new Lnode(5);
        _1.next= _2;
        _2.next = _3;
        _3.next = _4;
        _4.next = _5;

        _1.random = _3;
        _2.random = _4;

        Lnode clone = solve(_1);
        while (clone != null) {
            if(clone != null) System.out.println(clone.data + " ");
            if(clone.next != null) System.out.print(clone.next.data + " ");
            if(clone.random != null) System.out.print(clone.random.data);
            clone = clone.next;
        }
    }
}

class Lnode {
    int data;
    Lnode next;
    Lnode random;

    public Lnode(int data){ this.data = data; }
}
