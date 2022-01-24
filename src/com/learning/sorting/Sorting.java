package com.learning.sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class Sorting {

    public static void main(String arg[]){
        //int[] a = new int[] {70, 40, 30, 20, 60, 10};
        //SortingUtility sortingUtility = new SortingUtility();
        //sortingUtility.bubbleSort(a);
        //sortingUtility.insertionSort(a);
        //sortingUtility.selectionSort(a);

        //Testing merging 2 sorted arrays
        //int[] a1 = new int[]{10, 30, 60};
        //int[] a2 = new int[]{20, 40};
        //int[] af = new int[a1.length+a2.length];
        //sortingUtility.merge(af, a1, a2, a1.length, a2.length);
        //sortingUtility.mergeSort(a, a.length);
        int[] a = new int[]{10, 5, 2, 0, 7, 6, 4};
        //mergeSortUpgraded(a, 0, a.length-1);
        //Arrays.stream(a).forEach(x -> System.out.print(x+ " "));
        //System.out.println(findInversions(a, 0, a.length-1));
        quickSort(a, 0, a.length-1);
        Arrays.stream(a).forEach(x -> System.out.print(x+ " "));

        //List<String> activities = Arrays.asList(new String[]{"001", "035", "004"});
        /*Collections.sort(activities, (obj1, obj2)->{
            if("035".equals(obj1)) return -1;
            else if("035".equals(obj2)) return 1;
            else return 0;
        });
        activities.forEach(System.out::println);
        */
       /* a = new int[]{1, 2, 3, 4, 5};
        System.out.println(sortingUtility.findKthLargest(a,3));*/

        /*a = new int[]{0, 0, 1, 2, 2, 3, 4, 4};
        sortingUtility.countingSort(a);
        Arrays.stream(a).forEach(i->System.out.print(i+" "));*/

        /*a = new int[]{93, 82, 105, 11, 4, 78, 99, 100, 1054};
        sortingUtility.bucketSorting(a);
        Arrays.stream(a).forEach(i->System.out.print(i+" "));*/
    }

    public int[] bucketSorting(int[] a) {
        //finding max value                         O(n)
        int k = a[0], n = a.length;
        for(int i = 1; i < n; i++) {
            k = a[i] > k ? a[i] : k;
        }
        int len = Integer.toString(k).length();
        //array with prefix 0 constructed           O(n)
        String prefix = String.format("%0"+len+"d",0);
        String[] temp = new String[n];
        for(int i=0; i<n ;i++){
            temp[i] = (prefix+Integer.toString(a[i])).substring(Integer.toString(a[i]).length());
        }

        //bucket sorting                            O(d*n+10)
        int pos = 0;
        ArrayList<String>[] b = new ArrayList[10]; //0 -9  10 elements
        for (int j=len-1; j >= 0; j--) {
            for(int p=0; p<n; p++) {
                pos = Integer.parseInt(temp[p].charAt(j)+"");
                if(b[pos]==null)
                    b[pos] = new ArrayList<>();
                b[pos].add(temp[p]);
            }

            for(int p=0, z=0; p<b.length; p++) {
                if(b[p] == null)
                    continue;
                for(String s: b[p])
                    temp[z++] = s;
            }

            b = new ArrayList[10];
        }

        // amending the changes in actual array         O(n)
        for(int p=0; p<n; p++) {
            a[p] = Integer.parseInt(temp[p]);
        }

        return a; // worst case ~ O(n*d) ~O(n) for small d
    }

    /*
    * The following algorithm has few assumptions
    * Range will be from 0-k and k<=n only positive integers
    * The above assumptions makes it feasible to sort elements in ~O(n) complexity
    * */
    public int[] countingSort(int[] a) {
        int k = a[0], n = a.length;
        for(int i = 1; i < n; i++) {
            k = a[i] > k ? a[i] : k;
        }
        k++; // range from 0 to K

        if(k>a.length)
            throw new RuntimeException("Try Radix sorting");

        int[] b = new int[k]; // building count array
        for(int j=0; j<n; j++){
            b[a[j]] += 1;
        }

        for(int j=1; j<k; j++) {
            b[j] = b[j]+b[j-1];
        }

        for(int p=n-1; p>=0; p--){
            b[a[p]] = b[a[p]] - 1;
            a[b[a[p]]] = a[p];
        }
      return a;
    }

    public static void quickSort(int[] a, int s, int e) {
        //base case if 1 and zero elements return
        if(s>=e) return ;
        //partition
        int p = partition(a, s, e);
        quickSort(a, s, p-1);
        quickSort(a, p+1, e);
    }

    public static int partition(int[] a, int s, int e){
        //int p = e;
        // here last element e is considered as pivot
        // or middle element s+(e-s)/2 can be considered
        int i = s-1, p = e;
        for(int j=s; j<e; j++) {
            if(a[j] < a[p]) {
                i++;
                a[j] = a[i] + a[j] - (a[i] = a[j]);//s wap i and j
            }
        }
        a[p] = a[i+1]+a[p] - (a[i+1] = a[p]);
        return i+1;
    }

    /**
     * Refer HeapImpl.java
     * O(n logn)
     * */
    public int[] heapSort(int[] a) {
        return a;
    }

    /**
     * Description: Works on principle of divide and conquer
     * https://www.baeldung.com/java-merge-sort
     * */
    public int[] mergeSort(int[] a, int n) {
        if (n < 2)
            return a;
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++)
            l[i] = a[i];

        for (int i = mid; i < n; i++)
            r[i - mid] = a[i];

        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(a, l, r, mid, n - mid);

        return a;
    }

    /**
     * algorithm to merge 2 sorted arrays
     * Time Complexity: O(m+n)
     * Space Complexity: O(m+n)
     * */
    public void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }

        while (i < left) {
            a[k++] = l[i++];
        }

        while (j < right) {
            a[k++] = r[j++];
        }
    }

    /**
     * Description: Select the lowest element and place it at appropriate index for each pass
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Worst and best Case: O(n^2)
     * */
    public int[] selectionSort(int[] a) {
        int n = a.length, min_index = 0;
        for(int i = 0; i < n; i++){
            min_index = i;
            for(int j = i+1; j < n; j++)
                min_index  = (a[j] < a[min_index]) ? j : min_index;
            a[i] = a[i]+a[min_index] - (a[min_index] = a[i]); // in-line swapping;
        }
        return a;
    }

    /**
     * Description: index runs from 1 to n
     * for each index compare index i to 0
     * if value of temp < a[j-1] at any given point of time then exit
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Worst and best Case: O(n)
     * */
    public int[] insertionSort(int[] a) {
        int n = a.length, temp =0, j=0;
        for(int i=1;i<n;i++) {
            temp = a[i];
            for(j = i; j > 0 && temp < a[j-1]; j--) {
                a[j] = a[j-1];
            }
            a[j] = temp;
        }
        return a;
    }

    /**
     * Description: pass i (0->n-1) -> n-i iterations comparisons for each pass
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Worst and best Case: O(n^2)
     */
    public int[] bubbleSort(int[] a) {
        int n = a.length;
        for (int i=0;i<n-1;i++){
            for(int j=0; j< n-1-i;j++){
                if(a[j] > a[j+1])
                    a[j] = a[j]+a[j+1]-(a[j+1] = a[j]);
            }
        }
        return a;
    }

    public int findKthLargest(int[] a, int k) {
        int n = a.length, maxIndex = 0;
        for(int i=0; i<k; i++) {
            maxIndex= i;
            for(int j=i+1; j<n; j++){
                if(a[j] > a[maxIndex])
                    maxIndex = j;
            }
            a[i] = a[i]+a[maxIndex] -(a[maxIndex] = a[i]);
        }
        Arrays.stream(a).forEach(System.out::println);
        System.out.println();
        return a[k-1];
    }


    public static int[] mergeSortUpgraded(int[] a, int s, int e) {
        if(s>=e) return a;
        int mid = s+(e-s)/2;
        mergeSortUpgraded(a, s, mid);
        mergeSortUpgraded(a, mid+1, e);
        mergeUpgraded(a, s, e);
        return a;
    }

    public static void mergeUpgraded(int[] a, int s, int e) {
        int  l = s, m = s+(e-s)/2, r = m+1, k=0;
        int[] temp = new int[e+1];
        while(l <= m && r <= e) {
            if(a[l] < a[r]) temp[k++] = a[l++];
            else temp[k++] = a[r++];
        }
        while(l <= m) temp[k++] = a[l++];
        while(r <= e) temp[k++] = a[r++];

        l = s;
        while(l <= e) a[l] = temp[l++-s];
    }

    public static int findInversions(int[] a, int s, int e) {
        if(s>=e) return 0;
        int mid = s+(e-s)/2;
        return findInversions(a, s, mid)+
                findInversions(a, mid+1, e)+
                mergeInversion(a, s, e);
    }

    public static int mergeInversion(int[] a, int s, int e) {
        int  l = s, m = s+(e-s)/2, r = m+1, k=0, count = 0;
        int[] temp = new int[e+1];
        while(l <= m && r <= e) {
            if(a[l] < a[r]) temp[k++] = a[l++];
            else {
                count += (m-l+1);
                temp[k++] = a[r++];
            }
        }
        while(l <= m) temp[k++] = a[l++];
        while(r <= e) temp[k++] = a[r++];
        l = s;
        while(l <= e) a[l] = temp[l++-s];

        return count;
    }
}
