package com.learning.backtracking;

public class HeightOfBT {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        root.left = left;
        root.right = right;

        System.out.println(heightOfBT(root));
    }

    public static int heightOfBT(Node root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        return heightOfBT(root.left) + heightOfBT(root.right) ;
    }
}
class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}
