package BinaryTreeADT;


import BinaryTreeNodeADT.BinaryTreeNodeADT;
import BinaryTreeNodeADT.IBinaryTreeNodeADT;

public class BinaryTreeADT implements IBinaryTreeADT{
    IBinaryTreeNodeADT[] elements;

    public BinaryTreeADT() {
        elements = new BinaryTreeNodeADT[100];
    }

    public IBinaryTreeNodeADT getRoot() {
        return elements[0];
    }

    public Boolean isEmpty() {
        return elements.length == 0;
    }

    public int size() {
        return 0;
    }

    public boolean contains() {
        return false;
    }

    public int height() {
        return 0;
    }
}
