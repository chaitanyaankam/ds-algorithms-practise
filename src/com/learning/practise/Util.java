package com.learning.practise;

public class Util {

    public static void test(Employee e) {
        System.out.println(e);
        e.rollNo=55;
    }

    public static void test(int x) {
        x=60;
        System.out.println(x);
    }

    public static int sum(int a, int b) {
        b = 45;
        a = 55;
        return a + b;
    }
}
