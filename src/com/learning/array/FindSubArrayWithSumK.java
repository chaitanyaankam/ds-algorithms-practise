package com.learning.array;

public class FindSubArrayWithSumK {

    private static int solve(int[] arr, int x) {
        int start = 0, sum = arr[0], count = 0;
        for(int i = 1; i < arr.length; i++) {
            if(sum == x){
                count++;
                start++;
                sum = 0;
                continue;
            }

            sum += arr[i];

            if(sum > x) {
                while(sum < x) {
                    sum -= arr[start];
                    start++;
                }
            }
        }
        return count;
    }

    public static void main(String arg[]) {
        int[] arr = {4, 2, 2, 6, 4};
        int x = 6;
        int result = solve(arr, x);
        System.out.println(result);
    }
}
