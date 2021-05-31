package com.learning.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SubArrays {

    public static void main(String[] arg){
        int[] a = {0, 3, 2, 1, 7, 5, 6, 9};
        Arrays.stream(findWindowToSwapEntireArray(a)).forEach(i->System.out.print(a[i]+" "));
    }

    public static int[] findWindowToSwapEntireArray(int[] a){
        int s = 0, e = 0, n = a.length;
        for(int i = 0; i < n-1; i++){
            if(a[i] > a[i+1])
                s = i;
            if(a[n-1-i] < a[n-2-i])
                e = n-2-i;
        }

        s = (s > e) ? s+e-(e = s) : s;
        //System.out.println("s "+s+" e "+e);

        int i = 0, start = 0, end = 0;
        while(i < s) {
            if(a[i] > a[s]) {
                start = a[i];
                break;
            }
            i++;
        }

        i = n-1;
        while(i > end){
            if(a[e] > a[i]) {
                end = i;
                break;
            }
            i--;
        }

        //System.out.println("start "+start+" end "+end);
        return new int[] {start, end};
    }
}
