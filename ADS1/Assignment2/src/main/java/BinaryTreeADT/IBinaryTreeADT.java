package BinaryTreeADT;

import BinaryTreeNodeADT.IBinaryTreeNodeADT;

import java.util.ArrayList;

public interface IBinaryTreeADT {
    IBinaryTreeNodeADT getRoot();
    Boolean isEmpty();
    int size();
    boolean contains(int value);
    ArrayList<IBinaryTreeNodeADT> inOrder();
    ArrayList<IBinaryTreeNodeADT> preOrder();
    ArrayList<IBinaryTreeNodeADT> postOrder();
    ArrayList<IBinaryTreeNodeADT> levelOrder();
    int height();
}
