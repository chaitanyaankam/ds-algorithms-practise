package com.learning.design;

import java.util.Stack;

public class Minstack {
    int minimum;
    Stack<Integer> stack;

    public Minstack(){
        stack = new Stack<>();
    }

    public void push(Integer x) {
        if(stack.isEmpty()) {
            stack.push(x);
            minimum = x;
            return;
        }

        if(x < minimum) {
            stack.push(2*x - minimum);
            minimum = x;
        } else {
            stack.push(x);
        }
    }

    public Integer peek() {
        int curr = this.stack.peek();
        return (curr < minimum) ? minimum : curr;
    }

    public Integer pop() {
        int curr = stack.pop();
        if(curr < minimum)
            minimum = 2*minimum - curr;
        return curr;
    }

    public Integer getMin() {
        return minimum;
    }

    public static void main(String atg[]) {
        Minstack ms = new Minstack();
    }
}
