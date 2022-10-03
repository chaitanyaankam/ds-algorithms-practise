package com.learning.linkedlist;

public class FindMid {

    private static LLNode solve(LLNode head) {
        LLNode ptr1 = head, ptr2 = head;
        while (ptr1.next != null && ptr1.next.next != null) {
            ptr1 = ptr1.next.next;
            ptr2 = ptr2.next;
        }
        return ptr2;
    }

    public static void main(String arg[]) {
        LLNode head = LLNode.build();
        solve(head);
    }
}