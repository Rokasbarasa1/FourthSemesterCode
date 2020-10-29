package BinarySearchTreeNodeADT;

import BinaryTreeNodeADT.IBinaryTreeNodeADT;

public interface IBinarySearchTreeNodeADT extends IBinaryTreeNodeADT {
    void setParent(IBinarySearchTreeNodeADT parent);
    IBinarySearchTreeNodeADT getParent();
}
