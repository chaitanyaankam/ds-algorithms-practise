package com.learning.alogrithm;

public class KadanesAlgorithm {

    public static void main(String arg[]) {
        int[] a = {1, -2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(largestContiguousSubArraySum(a));

        a = new int[] {1, -2, -3, 4, -1, -2, 1, 5, -3};
        a = new int[] {-1,-2,-3,-4};
        a = new int[]{1,2,3,-2,5};
        int[] res = findLargestContiguousSubArr(a);
        System.out.println(res[0] +" "+ res[1]);
    }

    private static int[] findLargestContiguousSubArr(int[] a) {
        if(a == null)
            return null;
        int max = Integer.MIN_VALUE, sum = 0, index = 0;

        for(int i = 0; i < a.length; i++) {
            sum += a[i];
            if(sum > max) {
                max = sum;
                index = i;
            }
        }

        System.out.println("maximum from prefix "+max + " at index "+ index);

        int i = 0, start = 0, curr = max, left_max = max;
        while(i < index) {
            curr -= a[i];
            if(curr > left_max) {
                start = i + 1;
                left_max = curr;
            }
            i++;
        }

        System.out.println("left maximum "+ left_max + " from start "+ start );

        curr = left_max;
        int end = index;
        index++;

        while(index < a.length) {
            curr += a[index];
            if(curr > left_max) {
                end = index;
                left_max = curr;
            }
            index++;
        }

        System.out.println("right maximum "+ left_max + " till end "+ end );

        return new int[] {start, end};
    }

    public static int largestContiguousSubArraySum(int[] arr) {
        int max = Integer.MIN_VALUE, currMax = 0;
        for(int i = 0; i < arr.length; i++) {
            currMax += arr[i];
            if(currMax > max) max = currMax;
            if(currMax < 0) currMax = 0;
        }
        return max;
    }
}
