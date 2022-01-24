package com.learning.backtracking;

import java.util.Map;

public class Modulo {
    public static void main(String[] arg) {
        System.out.println(-1%20);
        System.out.println(modulo(2132, 0, 12));
    }

    public static int pow(int a, int b) {
        if (b == 0) return 1;
        return (b % 2 == 0)
                ?  pow(a, b/2) * pow (a, b/2)
                :  a * pow(a, b/2) * pow (a, b/2);
    }

    public static int modulo(int a, int b, int c) {
        return (c == 0) ? pow(a, b) : pow(a , b) % c;
    }
}
