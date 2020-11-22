package BinaryTreeADT;

import BinaryTreeNodeADT.IBinaryTreeNode;

import java.util.ArrayList;

public interface IBinaryTree {
    IBinaryTreeNode getRoot();
    Boolean isEmpty();
    int size();
    boolean contains(int value);
    ArrayList<Integer> inOrder();
    ArrayList<Integer> preOrder();
    ArrayList<Integer> postOrder();
    ArrayList<Integer> levelOrder();
    int height();
}
