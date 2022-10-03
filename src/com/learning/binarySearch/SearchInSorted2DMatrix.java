package com.learning.binarySearch;

public class SearchInSorted2DMatrix {

    private static int[] solve(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int lo = 0, hi = (m * n) - 1;
        while(lo < hi) {
            int mid = (lo + (hi-lo)/2);
            int mid_element = matrix[mid/m][mid%m];
            if(mid_element == target)
                return new int[]{mid/m, mid%m};
            else if(mid_element > target) hi = mid - 1;
            else lo = mid + 1;
        }
        return new int[]{-1, -1};
    }

    public static void main(String arg[]) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {6, 8, 10, 11},
                {14, 15, 18, 20}
        };
        int[] res = solve(matrix, 14);
        System.out.println(res[0]+" "+res[1]);
    }
}
