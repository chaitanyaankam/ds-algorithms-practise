package com.learning.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pascal's triangle is a triangular array of integers constructed with the following formula:
 * The first row consists of the number 1.
 * For each subsequent row, each element is the sum of the numbers directly above it, on either side.
 * For example, here are the first few rows:
 *     1
 *    1 1
 *   1 2 1
 *  1 3 3 1
 * 1 4 6 4 1
 * Given an input k, return the kth row of Pascal's triangle.
 * Bonus: Can you do this using only O(k) space?
 * */
public class PascalTriangle {

    private static int[] pascalTriangle(ArrayList<int[]> pTriangle, int k) {
        if(k == 0) return new int[] {1};
        if(k == 1) return new int[] {1, 1};

        if(pTriangle.size() - 1 >= k) return pTriangle.get(k);
        if(pTriangle.size() - 1 < k - 1) pascalTriangle(pTriangle, k - 1);

        int[] curr = new int[k + 1];
        int[] prev = pTriangle.get(k - 1);
        curr[0] = curr[k] = 1;

        for(int i = 1; i < k ; i++)
            curr[i] = prev[i] + prev[i - 1];

        pTriangle.add(k, curr);
        return curr;
    }

    public static void main(String arg[]) {
        ArrayList<int[]> pTriangle = new ArrayList(List.of(
                new int[] {1},
                new int[] {1, 1},
                new int[] {1, 2, 1},
                new int[] {1, 3, 3, 1},
                new int[] {1, 4, 6, 4, 1}
        ));
        int k = 7;
        int[] res = pascalTriangle(pTriangle, k - 1);
        Arrays.stream(res).forEach(i -> System.out.print(i +" "));
    }
}
