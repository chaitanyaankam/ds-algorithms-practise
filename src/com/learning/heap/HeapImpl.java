package com.learning.heap;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

    private TYPE heapType;

    private int heap_size = 0;

    private List<HeapNode<T>> completeLeftBST;

    public int getHeapSize(){
        return this.heap_size;
    }

    public List<HeapNode<T>> getBST() {
        return Collections.unmodifiableList(completeLeftBST);
    }

    private final BiFunction<Integer, Integer, Boolean> heapEvaluation = (parent, child) -> (heapType==TYPE.MIN) ?
            this.completeLeftBST.get(parent).value.compareTo(this.completeLeftBST.get(child).value)<0 :
            this.completeLeftBST.get(parent).value.compareTo(this.completeLeftBST.get(child).value)>0 ;

    private final Function<Integer, HeapNode<T>> getNode = (index) -> {
        if(index < heap_size) {
            HeapNode<T> node = completeLeftBST.get(index);
            return (node.value!=null) ? node : null;
        } else
            return null;
    };

    HeapImpl(TYPE heapType){
        this.heapType = heapType;
        completeLeftBST = new ArrayList<>(16);
    }

    @Override
    public void offer(HeapNode<T> newNode) {
        completeLeftBST.add(newNode);
        heap_size++;
        int indexOfInsert = heap_size-1;
        while(indexOfInsert>0 && !heapEvaluation.apply((indexOfInsert-1)/2, indexOfInsert)){
            int parent = (indexOfInsert-1)/2;
            T temp= completeLeftBST.get(indexOfInsert).value;
            completeLeftBST.get(indexOfInsert).value = completeLeftBST.get(parent).value;
            completeLeftBST.get(parent).value = temp;
            indexOfInsert = parent;
        }
    }

    @Override
    public HeapNode<T> pop() {
        HeapNode<T> topNode = completeLeftBST.get(0);
        HeapNode<T> lastNode = completeLeftBST.get(heap_size-1);
        T value = topNode.value;
        topNode.value = lastNode.value;
        lastNode.value = null;

        heap_size--;
        heapify(0);
        return new HeapNode<>(value);
    }

    @Override
    public HeapNode<T> getMinimum() {
        return (heapType==TYPE.MAX) ? this.completeLeftBST.get(heap_size-1) : this.completeLeftBST.get(0);
    }

    @Override
    public HeapNode<T> getMaximum() {
        return (heapType==TYPE.MAX) ? this.completeLeftBST.get(0) : this.completeLeftBST.get(heap_size-1);
    }

    @Override
    public List<HeapNode<T>> heapSort(Collection<T> collection) {
        this.destroyHeap();
        collection.forEach(item->this.offer(new HeapNode<>(item)));
        return this.completeLeftBST.stream().map(heapNode -> this.pop()).collect(Collectors.toList());
    }

    @Override
    public void heapify(int index) {
        int maxIndex;
        int leftIndex = (2*index)+1, rightIndex = (2*index)+2;
        Optional<HeapNode<T>> leftNode = Optional.ofNullable(this.getNode.apply(leftIndex));
        Optional<HeapNode<T>> rightNode = Optional.ofNullable(this.getNode.apply(rightIndex));

        maxIndex = leftNode.isPresent() && heapEvaluation.apply(leftIndex, index) ? leftIndex : index;
        maxIndex = rightNode.isPresent() && heapEvaluation.apply(rightIndex, maxIndex) ? rightIndex: maxIndex;

        if(maxIndex!=index){
            HeapNode<T> maxIndexNode = this.getNode.apply(maxIndex);
            HeapNode<T> indexNode = this.getNode.apply(index);
            T temp = maxIndexNode.value;
            maxIndexNode.value = indexNode.value;
            indexNode.value = temp;
        }

        if(maxIndex==index) return;
        this.heapify(maxIndex);
    }

    @Override
    public void destroyHeap() {
        completeLeftBST = new ArrayList<>();
        heap_size=0;
    }
}
