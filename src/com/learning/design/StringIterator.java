package com.learning.design;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class StringIterator {
    char[] charArr;
    Queue<Character> chars;
    LinkedList<Integer> count;

    //L1e12t3 is the compressed string
    public StringIterator(String s) {
        this.charArr = s.toCharArray();
        chars = new ArrayDeque<>();
        count = new LinkedList<>();

        String prev = "0";

        for(int i = 0; i < charArr.length; i++) {
            char curr = charArr[i];
            if(Character.isDigit(curr)) {
                prev += curr;
                continue;
            }
            count.offer(Integer.parseInt(prev));
            chars.offer(curr);
            prev = "";
        }
        count.offer(Integer.parseInt(prev));
        count.poll();
    }

    public boolean hasNext() {
        return !chars.isEmpty();
    }

    public Character next() {
        if(chars.isEmpty()) throw new RuntimeException("OutOfBounds");

        Character curr = chars.peek();
        int currCount = count.poll();
        if(currCount - 1 > 0) count.addFirst(currCount - 1);
        else chars.poll();
        return curr;
    }

    public static void main(String[] arg) {
        StringIterator strItr = new StringIterator("L1e12t3");
        while (strItr.hasNext())
            System.out.print(strItr.next()+" ");
    }
}
