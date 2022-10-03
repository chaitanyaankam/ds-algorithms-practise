package com.learning.linkedlist;

import java.util.Stack;

public class FindIfLLisPalindrome {

    private static int solve(LLNode head) {
        LLNode ptr1 = head, ptr2 = head;
        Stack<Integer> stack = new Stack<>();
        int len = 0;

        while (ptr1 != null && ptr1.next != null) {
            stack.push(ptr2.data);
            ptr1 = ptr1.next.next;
            len+=2;
            ptr2 = ptr2.next;
        }

        while(ptr1 != null) {
            ptr1 = ptr1.next;
            len++;
        }

        if(len%2 != 0)
            ptr2 = ptr2.next;

        System.out.println("len is "+ len);

        while(!stack.isEmpty()) {
            if(stack.pop() != ptr2.data)
                return -1;
            ptr2 = ptr2.next;
        }

        System.out.println("LL is a Palindrome");
        return 1;
    }

    public static void main(String arg[]) {
        LLNode head = LLNode.build();
        solve(head);
    }
}