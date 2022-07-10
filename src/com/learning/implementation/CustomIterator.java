package com.learning.implementation;

import java.util.List;

public class CustomIterator<T> {

    private int arrayIndex = 0;
    private int elementIndex = 0;
    private int n = 0;

    private List<int[]> items = null;

    public CustomIterator() {
        this.items = List.of(
                new int[]{1, 2},
                new int[]{3},
                new int[]{},
                new int[]{4, 5, 6}
        );
        this.n = this.items.size();
        this.arrayIndex = 0;
        this.elementIndex = 0;
    }

    public int next() throws RuntimeException {
        if(hasNext()) return items.get(arrayIndex)[elementIndex++];
        else throw new RuntimeException("No more elements");
    }

    public boolean hasNext() {
        if(arrayIndex >= n)
            return false;
        boolean isEmpty = items.get(arrayIndex).length == 0;
        boolean elementOutOfBounds = elementIndex >= items.get(arrayIndex).length;
        if(isEmpty || elementOutOfBounds) {
            arrayIndex++;
            elementIndex = 0;
            return hasNext();
        }
        return true;
    }

    public static void main(String arg[]) {
        CustomIterator iterator = new CustomIterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        iterator.next();
    }
}
