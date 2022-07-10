package com.learning.implementation;

/**
 * Using a read7() method that returns 7 characters from a file, implement readN(n) which reads n characters.
 * For example, given a file with the content “Hello world”, three read7() returns “Hello w”, “orld” and then “”.
 * */
public class ReadN {

    private static String buffer = "";
    private static int index = 0;

    private String readN(int n) {
        String s = null;
        while(buffer.length() < n && !(s = read7()).isEmpty()) {
            buffer += s;
        }
        buffer = buffer.substring(n + 1, buffer.length());
        return buffer.substring(0, n);
    }

    //case read10
    //a. Hello < 10 >> Hello
    //b. Hello world java eleven > 10  >> Hello worl
    //c. Hello w if n=2
    private String read7() {
        //this function is given
        //reads 7 characters of a given string
        return null;
    }
}
