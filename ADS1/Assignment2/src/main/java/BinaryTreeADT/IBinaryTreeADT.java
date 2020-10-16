package BinaryTreeADT;

import BinaryTreeNodeADT.IBinaryTreeNodeADT;

public interface IBinaryTreeADT {
    IBinaryTreeNodeADT getRoot();
    Boolean isEmpty();
    int size();
    boolean contains();
    // TODO give return type name
    //something inOrder();
    //something preOrder();
    //something postOrder();
    //something levelOrder();
    int height();
}
