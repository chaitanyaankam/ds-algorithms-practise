package com.learning.dp;

/*
* F(0) = 0, F(1) = 1
* F(n) = F(n - 1) + F(n - 2), for n > 1.
* */
public class Fibonacci {

    public static void main(String arg[]){
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.findFib(4));
    }

    public int findFib(int n) {
        return (n==0 || n==1)  ? n  : findFib(n-1)+findFib(n-2);
    }

    public static class OptiomizationProblems {

        public static void main(String arg[]) {
            System.out.println( optimizeToMinimumSquareSum("xxxxxxxxxxxccc", 2) );
        }

        /**
         * Stirng s = "xxxxxxxxxxccc"  b3a2x1c1 321
         * m = 2
         * */
        private static int optimizeToMinimumSquareSum(String s, int m) {
            int[] arr = new int[26];
            int start = 'a', index = Integer.MIN_VALUE;

            for(char c: s.toCharArray()) //O(n)
                index = Math.max(++arr[c-start], index);
            int[] countArr = new int[index + 1];
            for(int i: arr) //O(n)
                countArr[i]++;
            countArr[0] = 0;

            for(int i = 0; i < m; i++) { // O(m)
                if(index == 0) return -1;
                countArr[index - 1]++;
                index = (--countArr[index] > 0) ? index : index - 1;
            }

            int sum = 0;
            for(int i = 0; i< countArr.length; i++) //O(n)
                sum += countArr[i] * (i*i);
            return sum; //O(n)
        }
    }
}
