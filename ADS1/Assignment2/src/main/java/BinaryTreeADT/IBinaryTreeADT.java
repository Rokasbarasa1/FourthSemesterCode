package BinaryTreeADT;

import BinaryTreeNodeADT.IBinaryTreeNodeADT;

import java.util.ArrayList;

public interface IBinaryTreeADT {
    IBinaryTreeNodeADT getRoot();
    Boolean isEmpty();
    int size();
    boolean contains(int value);
    ArrayList<Integer> inOrder();
    ArrayList<Integer> preOrder();
    ArrayList<Integer> postOrder();
    ArrayList<Integer> levelOrder();
    int height();
}
