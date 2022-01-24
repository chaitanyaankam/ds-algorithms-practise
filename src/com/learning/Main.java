package com.learning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/*Name of the class has to be "Main" only if the class is public. */
class Main {

    public static void main (String[] args) throws Exception {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line = in.readLine();
        String[] nums = line.split(";")[0].split(",");
        int k = Integer.valueOf(line.split(";")[1]);
        System.out.println(reversedByK(nums, k));
    }

    private static String reversedByK(String[] nums, int k) {
        StringBuilder result = new StringBuilder();
        for (int i = k - 1; i < nums.length; i += k) {
            for (int j = i; i - j <= k - 1; j--) {
                result.append(',').append(nums[j]);
            }
        }

        for (int i = nums.length - nums.length % k; i < nums.length; i++) {
            result.append(',').append(nums[i]);
        }
        return result.substring(1);
    }
}