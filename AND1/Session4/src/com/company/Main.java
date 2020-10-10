package com.company;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        int[] tree = {20,10,30,5,15,-1,33,-1,6,-1,-1,-1,-1,30};

        printLowestToHighest(tree, 0);
    }

    private static void  printLowestToHighest(int[] tree, int index){
        int temp;
        int indexLeft = (2 * index) + 1;
        int indexRight = (2 * (index + 1));
        try{
            temp = tree[indexLeft];
            if(temp != -1)
                printLowestToHighest(tree, indexLeft);
        } catch (Exception e){
        }
        System.out.println(tree[index]);
        try{
            temp = tree[indexRight];
            if(temp != -1)
                printLowestToHighest(tree, indexRight);
        } catch (Exception e){
        }
    }
}
