package com.learning.companies;

import java.util.Arrays;

public class AmazonInterview {

    public static void main(String args[]) {
        int k=3;
        int[] array = new int[]{4, 35, 80, 123, 12345, 44, 8, 5};

        int max = Arrays.stream(array).max().getAsInt();

        int maxLength = Integer.toString(max).length();
        StringBuilder edgeBuilder = new StringBuilder();

        for(int i=0;i<maxLength;i++)
            edgeBuilder.append("-");

        final String horizontalEdge = edgeBuilder.toString();
        final String verticalEdge = "|";
        int arrayLength= array.length;

        int iterations = arrayLength / k;
        iterations = ((arrayLength % k)>0)? iterations+1: iterations;
        int start=0, end= 0;
        boolean firstIteration = true;
        StringBuilder cmpHorizontalEdge = new StringBuilder();

        while(iterations>0) {
            end = start+k-1;
            end = (end>= arrayLength)? arrayLength-1: end;

            for(int i=start;i<=end;i++)
                cmpHorizontalEdge.append("+"+horizontalEdge);
            cmpHorizontalEdge.append("+\n");

            if(firstIteration) {
                System.out.print(cmpHorizontalEdge);
                firstIteration = false;
            }

            for(int i=start;i<=end;i++)
                System.out.print(verticalEdge+String.format("%1$"+maxLength+ "s", array[i]+""));
            System.out.print("|\n");

            System.out.print(cmpHorizontalEdge);

            start=end+1;
            iterations--;
            cmpHorizontalEdge.setLength(0);
        }
    }


}
