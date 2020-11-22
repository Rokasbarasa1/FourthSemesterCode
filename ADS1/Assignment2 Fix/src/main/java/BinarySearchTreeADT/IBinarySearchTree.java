package BinarySearchTreeADT;

import BinarySearchTreeNodeADT.IBinarySearchTreeNode;
import BinaryTreeADT.IBinaryTree;

public interface IBinarySearchTree extends IBinaryTree {
    void addElement(IBinarySearchTreeNode value);
    void removeElement(int value);
    void removeAllOccurrences(int value);
    void removeMin();
    void removeMax();
    int findMin();
    int findMax();
}
