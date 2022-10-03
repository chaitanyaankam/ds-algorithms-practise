package com.learning.tree;

/**
 * Good morning! Here's your coding interview problem for today.
 * This problem was asked by Google.
 * A unival tree (which stands for "universal value") is a tree where all nodes
 * under it have the same value.
 * Given the root to a binary tree, count the number of unival subtrees.
 * For example, the following tree has 5 unival subtrees:
 *
 *    0
 *   / \
 *  1   0
 *     / \
 *    1   0
 *   / \
 *  1   1
 * **/
public class UnivalTree {

    public static void main(String arg[]) {
        TreeNode1 left3 = new TreeNode1(1, null, null);
        TreeNode1 right3 = new TreeNode1(1, null, null);
        TreeNode1 left2 = new TreeNode1(1, null, null);
        TreeNode1 right2 = new TreeNode1(1, null, null);
        TreeNode1 left1 = new TreeNode1(1, left3, right3);
        TreeNode1 right1 = new TreeNode1(1, left2, right2);
        TreeNode1 root = new TreeNode1(1, left1, right1);

        int res = solve(root);
        System.out.println(res);
    }

    public static int solve(TreeNode1 root) {
        if(root == null) return 0;
        else if(root.left == null && root.right == null) return 1;

        int count = solve(root.left) + solve(root.right);
        if(root.left == null && root.data == root.right.data) count++;
        else if(root.right == null && root.data == root.left.data) count++;
        else if(root.data == root.left.data && root.data == root.right.data) count++;

        return count;
    }
}

class TreeNode1 {
    int data;
    TreeNode1 left;
    TreeNode1 right;

    public TreeNode1(int data, TreeNode1 left, TreeNode1 right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
