package com.learning.heap;

public class HeapNode<T extends Comparable> {
    public T value;

    HeapNode(T value){
        this.value=value;
    }
}
