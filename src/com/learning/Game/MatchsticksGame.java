package com.learning.Game;

public class MatchsticksGame {

    public static void main(String[] args) {
        System.out.println(pickSticks(48));
    }

    public static int pickSticks(int N) {
        if(N % 5 != 0)
            return (int)(N % 5);
        else
            return -1;
    }
}
