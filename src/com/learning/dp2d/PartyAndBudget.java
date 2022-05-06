package com.learning.dp2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * You just received another bill which you cannot pay because you lack the money.
 * Unfortunately, this is not the first time to happen, and now you decide to investigate the cause of your constant monetary shortness. The reason is quite obvious: the lion's share of your money routinely disappears at the entrance of party localities.
 *
 * You make up your mind to solve the problem where it arises, namely at the parties themselves. You introduce a limit for your party budget and try to have the most possible fun with regard to this limit.
 *
 * You inquire beforehand about the entrance fee to each party and estimate how much fun you might have there. The list is readily compiled, but how do you actually pick the parties that give you the most fun and do not exceed your budget?
 *
 * Write a program which finds this optimal set of parties that offer the most fun. Keep in mind that your budget need not necessarily be reached exactly. Achieve the highest possible fun level, and do not spend more money than is absolutely necessary.
 *
 * Input
 * The first line of the input specifies your party budget and the number n of parties.
 *
 * The following n lines contain two numbers each. The first number indicates the entrance fee of each party. Parties cost between 5 and 25 francs. The second number indicates the amount of fun of each party, given as an integer number ranging from 0 to 10.
 *
 * The budget will not exceed 500 and there will be at most 100 parties. All numbers are separated by a single space.
 *
 * There are many test cases. Input ends with 0 0.
 *
 * Output
 * For each test case your program must output the sum of the entrance fees and the sum of all fun values of an optimal solution. Both numbers must be separated by a single space.
 *
 * 50 10
 * 12 3
 * 15 8
 * 16 9
 * 16 6
 * 10 2
 * 21 9
 * 18 4
 * 12 4
 * 17 8
 * 18 9
 *
 * 49 26
 * */
public class PartyAndBudget {

    public static void main(String[] arg) {
        int b = 50, n = 10;
        int[][] arr = {
                {12, 3},
                {15, 8},
                {16, 9},
                {16, 6},
                {10, 2},
                {21, 9},
                {18, 4},
                {12, 4},
                {17, 8},
                {18, 9}
        };
        int[][] dp = new int[b + 1][2];
        for(int[] x: dp) {
            x[0] = Integer.MAX_VALUE;
            x[1] = -1;
        }
        int[] res = top_down(b, arr, n - 1, dp);
        System.out.println("budget "+res[0]+" fun "+ res[1]);
    }

    private static int[] top_down(int b, int[][] a, int i, int[][] dp) {
        if(b == 0) return new int[]{0, 0};
        if(i == -1) return new int[]{Integer.MAX_VALUE, -1};

        if(dp[b][0] != Integer.MAX_VALUE && dp[b][1] != -1)
            return dp[b];

        int curr_b = a[i][0], curr_f = a[i][1];
        int[] inc  = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};

        if(b - curr_b >= 0) {
            inc[0] = curr_b;
            inc[1] = curr_f;

            int[] sub_problem = top_down(b - curr_b, a, i - 1, dp);
            if(sub_problem[0] != Integer.MAX_VALUE) {
                inc[0] += sub_problem[0];
                inc[1] += sub_problem[1];
            }
        }
        int[] exc = top_down(b, a, i - 1, dp);
        return dp[b] = (inc[1] > exc[1]) ? inc: (inc[0] < exc[0]) ? inc : exc;
    }
}
