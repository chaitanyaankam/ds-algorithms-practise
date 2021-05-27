package com.learning.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeapApplication {

    public static void main(String[] args) {

        HeapImpl<Integer> heap = new HeapImpl<>(Heap.TYPE.MAX);
        heap.offer(new HeapNode<Integer>(70));
        heap.offer(new HeapNode<Integer>(50));
        heap.offer(new HeapNode<Integer>(40));
        heap.offer(new HeapNode<Integer>(45));
        heap.offer(new HeapNode<Integer>(35));
        heap.offer(new HeapNode<Integer>(39));
        heap.offer(new HeapNode<Integer>(16));
        heap.offer(new HeapNode<Integer>(10));
        heap.offer(new HeapNode<Integer>(9));

        System.out.println("#Nodes before construction");
        System.out.println(heap.getBST().stream().map(node->node.value+" ").collect(Collectors.joining("|")) );

        heap.offer(new HeapNode<Integer>(60));
        System.out.println("#Nodes after inserting into heap");
        System.out.println(heap.getBST().stream().map(node->node.value+" ").collect(Collectors.joining("|")) );

        System.out.println("#Testing Heapification");
        heap.getBST().get(0).value = 48;
        heap.heapify(0);
        System.out.println(heap.getBST().stream().map(node->node.value+" ").collect(Collectors.joining("|")) );

        System.out.println("#RE: Testing Heapification");
        heap.getBST().get(0).value = 70;
        heap.heapify(0);
        System.out.println(heap.getBST().stream().map(node->node.value+" ").collect(Collectors.joining("|")) );

        System.out.println("#Heap Sorting Algorithm");
        List<Integer> ascendingSortedList = IntStream.range(1, 10).mapToObj(Integer::new).collect(Collectors.toList());
        List<HeapNode<Integer>> sorted = heap.heapSort(ascendingSortedList);
        System.out.println(sorted.stream().map(node->node.value+" ").collect(Collectors.joining("|")) );

        System.out.println("#Heap Sorting Algorithm | Strings");
        HeapImpl<String> heapString = new HeapImpl<>(Heap.TYPE.MIN);
        List<String> words = Arrays.asList(new String[]{"Saroja Ankam", "Meenakshi Ankam", "Pushpa Tangella", "Chaitanya Ankam"});
        List<HeapNode<String>> sortedWords = heapString.heapSort(words);
        System.out.println(sortedWords.stream().map(node->node.value+" ").collect(Collectors.joining("|")) );

        System.out.println("#Give an algorithm to find elements less than some value K, with worst case time complexity O(n)");
        List<Integer> valuesLessThankK = valuesLessThanK(ascendingSortedList, 4);
        System.out.println(valuesLessThankK.stream().map(item->Integer.toString(item)).collect(Collectors.joining("|")) );

        System.out.println("#Add one heap to another; Build heap in O(n)");
        List<Integer> source1 = Arrays.asList(new Integer[]{1, 3, 5, 7, 9});
        List<Integer> source2 = Arrays.asList(new Integer[]{2, 4, 6, 8, 10});
        HeapImpl<Integer> heapOfSource1 = new HeapImpl<>(Heap.TYPE.MIN);
        HeapImpl<Integer> heapOfSource2 = new HeapImpl<>(Heap.TYPE.MIN);
        source1.stream().map(HeapNode::new).forEach(heapOfSource1::offer);
        source2.stream().map(HeapNode::new).forEach(heapOfSource2::offer);

        buildHeap(heapOfSource1,heapOfSource2);
        System.out.println(heapOfSource1.getBST().stream().map(integerHeapNode -> integerHeapNode.value+" ")
                .collect(Collectors.joining("|")));

        System.out.println("#Kth smallest element for k "+8+" is "+getKthSmallestElement(heapOfSource1, 8));
        System.out.println(heapOfSource1.getBST().stream().map(integerHeapNode -> integerHeapNode.value+" ")
                .collect(Collectors.joining("|")));

        System.out.println("#Merging k sorted lists (ascending) of size n");
        //Expected output: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
        List<Integer> source_1 = Arrays.asList(new Integer[]{2, 5 , 8 , 11, 14});
        List<Integer> source_2 = Arrays.asList(new Integer[]{3, 6, 9, 12, 15});
        List<Integer> source_3 = Arrays.asList(new Integer[]{1, 4, 7, 10, 13});

        List<Integer>[] kSortedLists = new List[]{source_1, source_2, source_3};
        List<Integer> sortedListMerged = mergeKSortedListsofSameSize(kSortedLists);
        System.out.println(sortedListMerged.stream().map(integer -> Integer.toString(integer)).collect(Collectors.joining("|")));
    }

    /**k sorted lists of size n each is given
     * Creating max_heap using heads of each and iterating through remaining elements of each at the sametime; adding them to max_heap
     * Achieved O(nk log(k)) must achieve O(n log(k))*/
    public static <T extends Comparable<T>> List<T> mergeKSortedListsofSameSize(List<T>[] kSortedLists) {
        int k = kSortedLists.length;
        int size = kSortedLists[0].size();
        LinkedList<T> result = new LinkedList<T>();
        HeapImpl<T> heap = new HeapImpl<T>(Heap.TYPE.MAX);

        while(size>0){ //O(n)
            for(int i=0;i<k;i++) //O(k)
                heap.offer(new HeapNode<T>(kSortedLists[i].get(size-1))); //O(log(k))
            while(heap.getHeapSize()>0) //O(k)
                result.addFirst(heap.pop().value); //O(log(k))
            heap.destroyHeap();
            size--;
        }
        return result;
    }

    /**O(nlog(n)) time complexity, if binary heap is not given
     *If a binaryHeap is given then it must be O(n)*/
    public static <T extends Comparable<T>> List<T> valuesLessThanK (List<T> list, T k) {
        HeapImpl<T> heap = new HeapImpl<T>(Heap.TYPE.MIN);
        list.forEach(integer -> heap.offer(new HeapNode<T>(integer))); //O(log(n))
        return heap.getBST().stream().filter(item->(k.compareTo(item.value)>=0)).map(item->item.value).collect(Collectors.toList());//O(n)
    }

    /**Delete from min heap Ktimes and Kth time delete returns kth smallest element
     * O(k log(n)) is time complexity*/
    public static <T extends Comparable<T>> T getKthSmallestElement(HeapImpl<T> minHeap, int k) {
        return IntStream.range(0,k).mapToObj(a->minHeap.pop().value)
                .reduce((first, second)-> second).orElse(null);
    }


    /**Building a heap using reverse level order traversal
     * Time complexity is O(n) compared to top-down approach or percolating down O(nlog(n))
     * Read fibonnaci heap O(1) for the same problem
     * If we can only heapify non-leaf nodes, its enough the complexity will be O(n)
     * The Non-leaf nodes can be identified as i=(sizeOfHeap-1)/2 and i progressing as i--; if we heapify till i>0 dissatisfies
     * The complexity achieved below is O(m+n)*/
    public static <T extends Comparable<T>> HeapImpl<T> buildHeap(HeapImpl<T> heapA, HeapImpl<T> heapB) {
        List<HeapNode<T>> heapNodesToAdd = (heapA.getHeapSize()>= heapB.getHeapSize()) ? heapB.getBST(): heapA.getBST();
        HeapImpl<T> heap = (heapA.getHeapSize()>= heapB.getHeapSize()) ? heapA: heapB;

        heapNodesToAdd.forEach(heap::offer);//O(n)
        int n = heap.getHeapSize();
        for(int i=((n-1)/2);i>0;i--) //O(m+n)
            heap.heapify(i);
        return null;
    }

    /**implementing Queue using a Heap; The current implementation is doing the same*/
}
