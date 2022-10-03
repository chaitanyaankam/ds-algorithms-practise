package com.learning.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SmallestNumberOnLeft {

    private static List<Integer> solve(int[] a) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < a.length; i++) {
            if(stack.isEmpty()) {
                result.add(-1);
                stack.push(a[i]);
            }
            else if(a[i] > stack.peek()) {
                result.add(stack.peek());
                stack.push(a[i]);
            }
            else {
                while(!stack.isEmpty() && a[i] <= stack.peek()) {
                    stack.pop();
                }
                i = i - 1;
            }
        }
        return result;
    }

    public static void main(String arg[]) {
        int[] a = {1, 5, 0, 3, 4, 5};
        List<Integer> result = solve(a);
        result.forEach(i -> System.out.print(i + " "));
    }
}
