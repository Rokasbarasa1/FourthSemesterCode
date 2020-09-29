package com.company;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

class TreeOperations{
    public void preOrderNonRecursive(int[] tree){
        Stack<Integer> stack = new Stack<>();
        ArrayList<String> placesYouveBeenToo = new ArrayList<>();
        boolean left, right;
        int indexLeft;
        int indexRight;
        int index = 0;
        System.out.println(tree[index]);
        stack.push(index);
        for (int i = 0; i < (tree.length*2); i++) {
            indexLeft = (2 * index) + 1;
            try {
                int k = tree[indexLeft];
                if(k != -1)
                    left = true;
                else
                    left = false;
            }catch (ArrayIndexOutOfBoundsException e){
                left = false;
            }
            if(left && !placesYouveBeenToo.contains(indexLeft + "")){
                placesYouveBeenToo.add(indexLeft + "");
                stack.push(indexLeft);
                index = indexLeft;
                System.out.println(tree[index]);
                continue;
            }
            indexRight = (2 * (index + 1));
            try {
                int k = tree[indexRight];
                if(k != -1)
                    right = true;
                else
                    right = false;
            }catch (ArrayIndexOutOfBoundsException e){
                right = false;
            }
            if(right && !placesYouveBeenToo.contains(indexRight + "")){
                placesYouveBeenToo.add(indexRight + "");
                stack.push(indexRight);
                index = indexRight;
                System.out.println(tree[index]);
                continue;
            }
            try{
                index = stack.pop();
            }catch (EmptyStackException e){
                break;
            }
        }
    }
    public void preOrderRecursive(int[] tree, int index){
        boolean left, right;
        System.out.println(tree[index]);
        int indexLeft = (2 * index) + 1;
        int indexRight = (2 * (index + 1));
        try {
            int i = tree[indexLeft];
            if(i != -1)
                left = true;
            else
                left = false;
        }catch (ArrayIndexOutOfBoundsException e){
            left = false;
        }
        try {
            int i = tree[indexRight];
            if(i != -1)
                right = true;
            else
                right = false;
        }catch (ArrayIndexOutOfBoundsException e){
            right = false;
        }

        if(left){
            preOrderRecursive(tree, indexLeft);
        }
        if(right){
            preOrderRecursive(tree, indexRight);
        }
    }
}

public class Main {

    public static void main(String[] args) {
        int[] tree = {20,10,30,5,15,-1,33,-1,6,-1,-1,-1,-1,30};
        TreeOperations trees = new TreeOperations();
        System.out.println("Recursive pre order in action: ");
        trees.preOrderRecursive(tree, 0);
        System.out.println("Non-Recursive pre order in action: ");
        trees.preOrderNonRecursive(tree);
    }
}
