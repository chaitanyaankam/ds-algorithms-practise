package com.learning.dp;

/*
* F(0) = 0, F(1) = 1
* F(n) = F(n - 1) + F(n - 2), for n > 1.
* */
public class Fibonacci {

    public static void main(String arg[]){
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.findFib(4));
    }

    public int findFib(int n) {
        return (n==0 || n==1)  ? n  : findFib(n-1)+findFib(n-2);
    }
}
