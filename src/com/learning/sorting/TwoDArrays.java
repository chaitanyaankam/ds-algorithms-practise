package com.learning.sorting;

public class TwoDArrays {

    public static void main(String arg[]){
        int[][] a = new int[][]{ {1,2,3}, {4,5,6}, {7,8,9}};
        twoDArrayRotation(a);
    }

    public static void twoDArrayRotation(int[][] a){
        int n = a.length-1;
        for(int i=0;i<=n;i++) {
            for(int j=i;j<=n;j++)
                a[i][j] = a[j][i]+a[i][j]-(a[j][i]=a[i][j]);
            for(int j=0;j<=n/2;j++)
                a[i][n-j] = a[i][j]+a[i][n-j]-(a[i][j]=a[i][n-j]);
        }
    }
}
