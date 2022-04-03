package com.learning.array;

public class MajorityElement {

    public static void main(String[] arg) {
        int a[] = {3, 4, 4, 3, 4, 2, 4, 4, 5, 6, 4};
        int result = findCandidate(a, a.length);
        System.out.println(result);
    }

    private static int findCandidate(int a[], int size) {
        int maj_index = 0, count = 1, max = Integer.MIN_VALUE, max_index = 0;
        int i;
        for (i = 1; i < size; i++) {
            if (a[maj_index] == a[i]) count++;
            else count--;

            if (count == 0) {
                maj_index = i;
                count = 1;
            }
        }
        return a[maj_index];
    }
}
