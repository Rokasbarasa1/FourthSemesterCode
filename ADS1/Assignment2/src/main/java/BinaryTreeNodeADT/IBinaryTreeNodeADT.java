package BinaryTreeNodeADT;

public interface IBinaryTreeNodeADT {
    void setElement(int element);
    int getElement();
    void addLeftChild(int element);
    void addRightChild(int element);
    IBinaryTreeNodeADT getLeftChild();
    IBinaryTreeNodeADT getRightChild();
    boolean isElementInitialized();
}
