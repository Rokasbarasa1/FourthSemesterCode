package BinaryTreeNodeADT;

public interface IBinaryTreeNode {
    void setElement(int value);
    int getElement();
    void addLeftChild(IBinaryTreeNode value);
    void addRightChild(IBinaryTreeNode value);
    IBinaryTreeNode getLeftChild();
    IBinaryTreeNode getRightChild();
}
