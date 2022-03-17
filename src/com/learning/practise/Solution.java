package com.learning.practise;

public class Solution {

    public static void main(String arg[]) {
        //local variable
        int x = 1;
        System.out.println(x);

        //instance variable; default values
        Employee e=new Employee();
        System.out.println(e.rollNo);

        e.rollNo=10;
        e.aChar='A';
        Util.test(e.rollNo);// pass by value
        System.out.println(e.rollNo);
        Util.test(e);// pass by reference
        System.out.println(e.rollNo);

        int a=20;
        int b=30;
        int c = Util.sum(a,b);
        System.out.println("c "+c+" a "+a+" b "+b);
    }
}
