package BinarySearchTreeADT;

import BinaryTreeADT.IBinaryTreeADT;

public interface IBinarySearchTreeADT extends IBinaryTreeADT {
    void addElement(int element);
    void removeElement(int element);
    void removeAllOccurrences(int element);
    void removeMin();
    void removeMax();
    int findMin();
    int findMax();
}
