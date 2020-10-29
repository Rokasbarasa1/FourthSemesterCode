package BinaryTreeNodeADT;

public interface IBinaryTreeNodeADT {
    void setElement(int value);
    int getElement();
    void addLeftChild(int value);
    void addRightChild(int value);
    IBinaryTreeNodeADT getLeftChild();
    IBinaryTreeNodeADT getRightChild();
}
