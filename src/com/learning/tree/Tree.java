package com.learning.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tree {

    /*If either l or r is null, it will be represented as a zero.
    Otherwise, it will be represented by a new (lr) pair.
        Here are a few examples:
        A root node with no children: (00)
        A root node with two children: ((00)(00))
        An unbalanced tree with three consecutive left children: ((((00)0)0)0)
    *
    public static void main(String arg[]){
        String s = "(((00)0)0)", leaf = "(00)";
        int count = 0;
        while(!s.equals(leaf)){
            s = s.replaceAll("\\(00\\)","0");
            count++;
        }
        System.out.println(count);
    } */

    public static void main(String arg[]){
        int[] a = new int[]{4, 2, 5, 1, 6, 3, 7};
        TreeNode root = constructTree(a, 0, a.length-1);
        levelOrderTraversal(root);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
        System.out.println("sum: "+sum(root));
    }

    /*
    * Given in-order traversal in int[] a
    * */
    public static TreeNode constructTree(int[] a, int start, int end){
        //base case
        if(start == end)
            return new TreeNode(a[start]);

        int mid = start+(end-start)/2;
        TreeNode node = new TreeNode(a[mid]);
        node.right = constructTree(a, mid+1, end);
        node.left = constructTree(a, start, mid-1);

        return node;
    }

    public static void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.data+" ");
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
    }

    public static void inOrderTraversal(TreeNode root){
        TreeNode temp = root;
        if(root==null)
            return;
        inOrderTraversal(root.left);
        System.out.print(root.data+" ");
        inOrderTraversal(root.right);
    }

    public static int sum(TreeNode root){
        TreeNode temp = root;
        if(root==null)
            return 0;
        return sum(root.left) +root.data + sum(root.right);
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data){
        this.data = data;
    }
}