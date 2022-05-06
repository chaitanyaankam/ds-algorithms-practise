package com.learning.backtracking;

public class GeneratingBrackets {

    public static void main(String[] arg) {
        generateStrings(3 , 3, "");
    }

    public static void generateStrings(int o, int c, String out) {
        if(o == 0) {
            while(c > 0) {
                out += ")";
                c--;
            }
            System.out.println(out);
            return;
        }

        if(o == c) generateStrings(o - 1, c, out + "(");
        else {
            generateStrings(o - 1, c, out + "(");
            if(!out.isEmpty()) out.substring(0, out.length() - 1);
            generateStrings(o, c - 1, out + ")");
        }
    }
}
