package com.learning.recursion;

/**
 * Given n friends who wants to got to party, each one can
 * remain single or can be paired up with some other friend.
 * Each friend can be paired only once. Find out total number
 * of ways in which friends can remain single or can be paired up.
 * */
public class Pairing {

    public static void main(String[] arg) {
        int res = findCount(3);
        System.out.println("count of ways "+ res);
    }

    private static int findCount(int n) {
        if(n == 1 || n == 0) return 1;
        return (n - 1) * findCount(n - 2) + findCount(n - 1);
    }
}
