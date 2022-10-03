package com.learning.linkedlist;

public class LLNode {
    int data;
    LLNode next;

    public LLNode(int data) {
        this.data = data;
    }

    public static LLNode build() {
        LLNode _1 = new LLNode(1);
        LLNode _2 = new LLNode(2);
        LLNode _3 = new LLNode(3);
        LLNode _4 = new LLNode(4);
        LLNode _5 = new LLNode(5);
        LLNode _6 = new LLNode(6);

        _1.next= _2;
        _2.next = _3;
        _3.next = _4;
        _4.next = _5;
        _5.next = _6;

        return _1;
    }
}
