package BinarySearchTreeNodeADT;

import BinaryTreeNodeADT.BinaryTreeNodeADT;
import BinaryTreeNodeADT.IBinaryTreeNodeADT;

public class BinarySearchTreeNodeADT extends BinaryTreeNodeADT implements IBinarySearchTreeNodeADT{
    private IBinarySearchTreeNodeADT parent;

    public BinarySearchTreeNodeADT (int value){
        super(value);
    }

    @Override
    public void setParent(IBinarySearchTreeNodeADT parent) {
        this.parent = parent;
    }

    @Override
    public IBinarySearchTreeNodeADT getParent() {
        return parent;
    }
}
