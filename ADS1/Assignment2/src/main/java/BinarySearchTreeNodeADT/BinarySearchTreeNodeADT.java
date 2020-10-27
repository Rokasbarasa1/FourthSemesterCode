package BinarySearchTreeNodeADT;

import BinaryTreeNodeADT.BinaryTreeNodeADT;
import BinaryTreeNodeADT.IBinaryTreeNodeADT;

public class BinarySearchTreeNodeADT extends BinaryTreeNodeADT implements IBinarySearchTreeNodeADT{
    private IBinaryTreeNodeADT parent;

    @Override
    public void setParent(IBinarySearchTreeNodeADT parent) {
        this.parent = parent;
    }

    @Override
    public IBinarySearchTreeNodeADT getParent() {
        return null;
    }
}
