package com.learning.dp2d;

import java.util.Arrays;

/**
 * A scuba diver uses a special equipment for diving.
 * He has a cylinder with two containers: one with oxygen and the other with nitrogen.
 * Depending on the time he wants to stay under water and the depth of diving
 * the scuba diver needs various amount of oxygen and nitrogen.
 * The scuba diver has at his disposal a certain number of cylinders.
 * Each cylinder can be described by its weight and the volume of gas it contains.
 * In order to complete his task the scuba diver needs specific amount of oxygen and nitrogen.
 * What is the minimal total weight of cylinders he has to take to complete the task?
 *
 * The scuba diver has at his disposal 5 cylinders described below.
 * Each description consists of:
 * volume of oxygen, volume of nitrogen (both values are given in litres) and weight of the cylinder (given in decagrams):
 *
 * 3 36 120
 * 10 25 129
 * 5 50 250
 * 1 45 130
 * 4 20 119
 *
 * If the scuba diver needs 5 litres of oxygen and 60 litres of nitrogen then he has to take two cylinders of total weight 249.
 *
 * Task
 * Write a program that for each test case:
 * reads scuba diver's demand for oxygen and nitrogen, the number of accessible cylinders and their descriptions;
 * computes the minimal total weight of cylinders the scuba diver needs to complete his task;
 * outputs the result.
 * Note: the given set of cylinders always allows to complete the given task.
 * */
public class SubaDiver {

    public static void main(String[] arg) {
        int o2 = 5;
        int n2 = 60;
        int n = 5;
        int[][] v_and_w = {
                {3, 36, 120},
                {10, 25, 129},
                {5, 50, 250},
                {1, 45, 130},
                {4, 20, 119}
        };
        int[][] dp = new int[o2 + 1][ n2 + 1];
        for(int[] arr: dp)
            Arrays.fill(arr, -1);
        int minWeight = topDown(o2, n2, v_and_w, n - 1, dp);
        System.out.println("min weight "+ minWeight);
    }

    private static int topDown(int o2, int n2, int[][] arr, int i, int[][] dp) {
        if(i == -1)
            return Integer.MAX_VALUE;
        if(o2 == 0 && n2 == 0)
            return 0;
        if(dp[o2][n2] != -1)
            return dp[o2][n2];

        int curr_o2 = arr[i][0];
        int curr_n2 = arr[i][1];
        int weight  = arr[i][2];

        int inc = Integer.MAX_VALUE;
        if(o2 <= curr_o2 && n2 <= curr_n2) inc = weight;
        else if(o2 > curr_o2 && n2 > curr_n2) {
            inc = weight;
            int sub_problem = topDown(o2 - curr_o2, n2 - curr_n2, arr, i - 1, dp);
            inc = (sub_problem == Integer.MAX_VALUE)? Integer.MAX_VALUE : inc + sub_problem;
        }
        int exc = topDown(o2, n2, arr, i - 1, dp);

        return dp[o2][n2] = Math.min(inc, exc);
    }
}
