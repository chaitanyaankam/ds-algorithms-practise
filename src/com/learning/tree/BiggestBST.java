package com.learning.tree;

import java.util.Objects;

public class BiggestBST {

    public static void main(String arg[]) {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(11);
        TreeNode node2l = new TreeNode(2);
        TreeNode node2r = new TreeNode(14);

        TreeNode node3 = new TreeNode(5);
        TreeNode node3l = new TreeNode(3);
        TreeNode node3r = new TreeNode(6);
        TreeNode node3ll = new TreeNode(1);
        TreeNode node3lr = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.left = node2l;
        node2.right = node2r;
        node3.left = node3l;
        node3.right = node3r;
        node3l.left = node3ll;
        node3l.right = node3lr;

        BiggestBST biggestBST = new BiggestBST();
        LargestNode result = biggestBST.highestBST(node1);
        System.out.println(result.bstNode.data +" "+result.height);
    }

    private LargestNode highestBST(TreeNode root) {
        if(Objects.isNull(root))
            return new LargestNode(null, 0);
        boolean isBST = isBST(root);
        LargestNode l = highestBST(root.left);
        LargestNode r = highestBST(root.right);

        if(!isBST)
            return l.height > r.height ? l: r;

        var curr_height = (l.height > r.height ? l.height: r.height) + 1;
        LargestNode current = new LargestNode(root, curr_height);
        return current;
    }

    public boolean isBST(TreeNode root) {
        if(root.left == null && root.right == null) return true;
        else if(root.left != null && root.right == null) return root.left.data < root.data;
        else if(root.right != null && root.left == null) return  root.right.data > root.data;
        else return root.left.data < root.data && root.data < root.right.data;
    }

    class LargestNode {
        TreeNode bstNode;
        int height;

        public LargestNode(TreeNode bst, int height) {
            this.bstNode = bst;
            this.height = height;
        }
    }
}
