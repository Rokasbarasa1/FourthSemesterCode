package BinarySearchTreeNodeADT;

import BinaryTreeNodeADT.BinaryTreeNode;

public class BinarySearchTreeNode extends BinaryTreeNode implements IBinarySearchTreeNode {
    private IBinarySearchTreeNode parent;

    public BinarySearchTreeNode(int value){
        super(value);
    }

    @Override
    public void setParent(IBinarySearchTreeNode parent) {
        this.parent = parent;
    }

    @Override
    public IBinarySearchTreeNode getParent() {
        return parent;
    }
}
