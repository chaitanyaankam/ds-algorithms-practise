package com.learning.numerals;

/*
* Bitwise ~ operator
* First convert into 1's complement
* then convert into 2's complement
* https://www.youtube.com/watch?v=xXBitioUzf8
*
* Bitwise shift operator
*
* */
public class Numnerals01 {

    public static void main(String arg[]) {
        Numnerals01 numerals01 = new Numnerals01();
        System.out.println(numerals01.findSignedIntegerFromString("   -42"));
    }

    public int findSignedIntegerFromString(String temp){
        if(temp.length()==0)
            return 0;

        int i=0;
        char sign = '+';
        boolean signRead = false;
        String valString = "";
        while(i < temp.length()) {
            if (temp.charAt(i) == ' ')
                i++;
            else if (!signRead && (temp.charAt(i) == '-' || temp.charAt(i) == '+')) {
                sign = temp.charAt(i++);
                signRead = true;
            }
            else if(Character.isDigit(temp.charAt(i)))
                valString += temp.charAt(i++);
            else break;
        }

        if(valString.length()==0)
            return 0;

        try{
            return Integer.parseInt(sign+valString);
        } catch (Exception e){
           return (sign=='-')? Integer.MIN_VALUE: Integer.MAX_VALUE;
        }
    }

    public int reverseNumber(int a){
        long rev = 0;
        while(a !=0){
            rev = (rev*10) + (a % 10);
            a = a / 10;
        }
        return (rev > Integer.MAX_VALUE || rev< Integer.MIN_VALUE) ? 0 : (int) rev;
    }

    public int reverseBits(int a) {
        int rev = 0;
        while(a>0){
            rev<<=1;
            if(((int) a & 1) == 1)
                rev ^=1;
            a>>=1;
        }
        return rev;
    }
}
