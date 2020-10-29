package BinaryTreeNodeADT;

public class BinaryTreeNode implements IBinaryTreeNode {
    private int value;
    protected IBinaryTreeNode leftChild;
    protected IBinaryTreeNode rightChild;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    @Override
    public void setElement(int value) {
        this.value = value;
    }

    @Override
    public int getElement() {
        return value;
    }

    @Override
    public void addLeftChild(IBinaryTreeNode value) {
        leftChild = value;
    }

    @Override
    public void addRightChild(IBinaryTreeNode value) {
        rightChild = value;
    }

    @Override
    public IBinaryTreeNode getLeftChild() {
            return leftChild;
    }

    @Override
    public IBinaryTreeNode getRightChild() {
            return rightChild;
    }
}
