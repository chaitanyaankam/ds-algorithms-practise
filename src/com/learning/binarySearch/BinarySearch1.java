package com.learning.binarySearch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BinarySearch1 {

    public static void main (String[] args) throws java.lang.Exception {
        int[] toys =  { 2,  8, 0, 32, 1};
        int[] x =     {-2, -1, 2, 3,  4};
        int k = 5;
        int pos = 0;
        //System.out.println(getMaxToysFromShopsAtSpecificDistance(x, toys, k, pos));
        getMaxToys(toys, x, k);

        /*BSTNode  root = new BSTNode(9);
        root.left    = new BSTNode(4);
        root.right   = new BSTNode(17);
        root.left.left = new BSTNode(3);
        root.left.right = new BSTNode(6);
        root.left.right.left = new BSTNode(5);
        root.left.right.right = new BSTNode(7);
        root.right.right = new BSTNode(22);
        root.right.right.left = new BSTNode(20);

        int result = findClosestNode(root, 2, root);
        System.out.println(result);*/
    }

    /**
     *  Given Sorted Array called Shops and respective toys
     *  find max toys that can be collected in K steps
     *  your at position pos
     *
     *  int[] shops = new int[] {-5, -1, 2, 5};
     *  int[] toys = new int[] {6, 2, 30, 8};
     *  int k = 5;// max steps to be taken
     *  int pos = 0;
     * */
    static int getMaxToysFromShopsAtSpecificDistance(int[] shops, int[] toys, int k, int pos) {
        BinarySearch1 b = new BinarySearch1();
        PriorityQueue<Shop> spq = b.getAllClosest(shops, toys, pos);
        int count = 0;

        while(k > 0 && !spq.isEmpty()) { //K times
            Shop t = spq.poll();
            if (k - t.diff >= 0) {
                k = k - t.diff;
                count = count + t.toys;
            }
        }
        return count;
    }

    class Shop {
        int d;
        int diff;
        int toys;

        public Shop(int d, int diff, int toys) {
            this.d = d;
            this.diff = diff;
            this.toys = toys;
        }
    }

    PriorityQueue<Shop> getAllClosest(int[] a, int[] toys, int x) {
        PriorityQueue<Shop> shops = new PriorityQueue<Shop>(
            (Shop s1, Shop s2) -> {
                if(s1.toys > s2.toys) return -1;
                else if (s1.toys < s2.toys) return 1;
                else {
                    if(s1.diff > s2.diff) return 1;
                    else if (s1.diff < s2.diff) return -1;
                    else return 0;
                }
            });

        for(int i = 0; i < a.length; i++) {
            int d = Math.abs(a[i]);
            int diff = Math.abs(d-x);
            Shop s = new Shop(d, diff, toys[i]);
            shops.offer(s);
        }  // O(N long N)

        shops.stream().forEach(s ->System.out.println(s.d +" "+s.diff+" "+s.toys));

        return shops;
    }

    private static void getMaxToys(int[] toys, int[] shops, int k) {
        int currentMax = 0;
        int finalMax = 0;
        for(int i=0; i < toys.length; i++){
            if(Math.abs(shops[i]) > k){
                continue;
            } else {
                int start = shops[i];
                currentMax = toys[i];
                int next = i + 1;

                while(next <= toys.length-1 && shops[next] <= k && shops[next] <= (start < 0 ? 2 * start : start)+ k ) {
                    currentMax += toys[next];
                    next++;
                }
            }
            if(currentMax > finalMax) {
                finalMax =currentMax;
            }

        }
        System.out.println(finalMax);
    }

    /**
     * Finding first closest element to given target
     * then iterate from that index to K if give array
        |a-x| = |b-x| & a < b then consider b
     int[] a = new int[] {1, 2, 3, 4, 5, 6};
     int k = 2;
     int x = 3;
     int firstClosest = findClosest(a, x, k);
     System.out.println("first closest "+firstClosest);**/
    static int findClosest(int[] a, int x, int k) {
        int l = 0, r = a.length - 1, n = a.length - 1;
        while (l < r) {
            int mid = l+(r-1)/2;
            int dMid = Math.abs(a[mid] - x);
            int dK = (mid + k > n) ? Integer.MAX_VALUE : Math.abs(a[mid + k] - x);

            if(dK > dMid) r = mid;
            else return  mid;
        }
        return r;
    }

    /**
     * Find the closest element in Binary Search Tree
     * */
    static class BSTNode {
        int key;
        BSTNode  left,  right;
        public BSTNode(int key) {
            this.key = key;
        }
    }

    public static int findClosestNode(BSTNode node, int target, BSTNode parent) {
        if(node == null) return parent.key;
        if(target == node.key) return node.key;

        int currDiff = Math.abs(node.key - target);
        int ptrDiff = Math.abs(parent.key - target);    

        if( (parent.key < target && target < node.key ) || ( parent.key > target && target > node.key))
            return (ptrDiff < currDiff) ? parent.key : node.key;

        return (target > node.key) ? findClosestNode(node.right, target, node) : findClosestNode(node.left, target, node);
    }

    static boolean binarySearch(int[] a, int l, int r, int target) {
        if(l > r) return false;
        int mid = (l + r)/2;

        if(a[mid] == target)
            return true;
        else if(target > a[mid])
            return binarySearch(a, mid+1, r, target);
        else
            return binarySearch(a, l, mid-1, target);
    }
}
