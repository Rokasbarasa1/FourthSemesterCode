package BinaryTreeNodeADT;

public class BinaryTreeNodeADT implements IBinaryTreeNodeADT{
    private int value;
    private IBinaryTreeNodeADT leftChild;
    private IBinaryTreeNodeADT rightChild;

    public BinaryTreeNodeADT(int value) {
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
    public void addLeftChild(int value) {
        IBinaryTreeNodeADT node = new BinaryTreeNodeADT(value);
        leftChild = node;
        /*
        if(element <= value){
            IBinaryTreeNodeADT node = new BinaryTreeNodeADT(element);
            leftChild = node;
        }else{
            throw new IllegalArgumentException();
        }
         */
    }

    @Override
    public void addRightChild(int value) {
        IBinaryTreeNodeADT node = new BinaryTreeNodeADT(value);
        rightChild = node;
        /*
        if(element > value){
            IBinaryTreeNodeADT node = new BinaryTreeNodeADT(element);
            rightChild = node;
        }else{
            throw new IllegalArgumentException();
        }
         */
    }

    @Override
    public IBinaryTreeNodeADT getLeftChild() {
            return leftChild;
    }

    @Override
    public IBinaryTreeNodeADT getRightChild() {
            return rightChild;
    }
}
