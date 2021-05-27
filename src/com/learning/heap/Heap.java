package com.learning.heap;

import java.util.Collection;
import java.util.List;

/* HEAP
Heap is a tree based data structure, tree in heap is almost complete binary tree
Data in complete binary tree must be as left as possible (Left Representation)

Array representation (left representation) of Binary tree is included here
Binary Tree - every node must have atmost 2 nodes
finding left and right node in an array
LEFT: 2*i+1
RIGHT: 2*i+2
finding parent from any node
PARENT: |(i-1)/2| floor value

Max Heap and Min Heap
In Max Heap: For every node i, the value of the node is less than or equal to its parent node value
In Min Heap: For every node i, the value of the node is greater than or equal to its parent node value
above conditions are not applicable for root node
Max Heap: A[Parent(i)]>=A[i], not applicable for root node
Max Heap: A[Parent(i)]<=A[i], not applicable for root node

Operations:
Insert Element to heap (min and max)
Heap sorting
* */
public interface Heap<T extends Comparable> {

    static enum TYPE { MAX, MIN };

    /**Max time O(log(n)) comparison will take at max height of tree which is log(n)
     *Element added as leaf and starts moving upwards
     */
    void offer(HeapNode<T> newNode);

    /**Only root element can be popped
     * Its like a queue only one on the top can be removed
     * After removing top element, inorder to preserver complete BST heapification must be done
     */
    HeapNode<T> pop();

    /**O(1) time complexity*/
    HeapNode<T> getMaximum();

    /**O(1) time complexity*/
    HeapNode<T> getMinimum();

    /**O(log(n)) time complexity*/
    void heapify(int index);

    /**O(nlog(n)) time complexity*/
    List<HeapNode<T>> heapSort(Collection<T> collection);

    void destroyHeap();
}
