package BinarySearchTreeNodeADT;

import BinaryTreeNodeADT.IBinaryTreeNode;

public interface IBinarySearchTreeNode extends IBinaryTreeNode {
    void setParent(IBinarySearchTreeNode parent);
    IBinarySearchTreeNode getParent();
}
