package com.learning.tree;


import java.util.*;
import java.lang.*;

/* Name of the class has to be "Main" only if the class is public. */
class ZigZagTreeTraversal {
    public static void main (String[] args) throws java.lang.Exception {
        Node node1 =  new Node (1);
        Node node2 =  new Node (2);
        Node node3 =  new Node (3);
        Node node4 =  new Node (4);
        Node node5 =  new Node (5);
        Node node6 =  new Node (6);
        Node node7 =  new Node (7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        int[] a = new int[7];
        Stack<Node> next = new Stack<>();
        Stack<Node> current = new Stack<>();
        Stack<Node> temp2 = null;
        current.push(node1);
        boolean r2l = true;
        Node temp = null;
        int p = 0;

        while(p < a.length) {
            if(current.isEmpty()) {
               temp2 = current;
               current = next;
               next = temp2;
               r2l = !r2l;
            }

            temp = current.pop();
            a[p] = temp.data;
            p++;

            if(r2l) {
                if(temp.left!=null)   next.push(temp.left);
                if(temp.right!=null)  next.push(temp.right);
            } else {
                if(temp.right!=null)  next.push(temp.right);
                if(temp.left!=null)   next.push(temp.left);
            }
        }

        for(int i: a)
            System.out.print(i+" ");
    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int n){
        this.data = n;
    }
}

