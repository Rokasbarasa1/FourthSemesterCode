package BinarySearchTreeADT;

import BinaryTreeADT.IBinaryTreeADT;

public interface IBinarySearchTreeADT extends IBinaryTreeADT {
    void addElement(int value);
    void removeElement(int value);
    void removeAllOccurrences(int value);
    void removeMin();
    void removeMax();
    int findMin();
    int findMax();
}
