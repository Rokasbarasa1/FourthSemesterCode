package BinaryTreeNodeADT;

public class BinaryTreeNodeADT implements IBinaryTreeNodeADT{
    private boolean valueInitialized;
    private int value;
    private IBinaryTreeNodeADT leftChild;
    private IBinaryTreeNodeADT rightChild;

    public BinaryTreeNodeADT() {
        valueInitialized = false;
    }

    @Override
    public void setElement(int element) {
        value = element;
        valueInitialized = true;
    }

    @Override
    public int getElement() {
        if(valueInitialized){
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void addLeftChild(int element) {
        if(!valueInitialized){
            throw new IllegalArgumentException();
        } else if(element <= value){
            IBinaryTreeNodeADT node = new BinaryTreeNodeADT();
            node.setElement(element);
            leftChild = node;
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void addRightChild(int element) {
        if(!valueInitialized){
            throw new IllegalArgumentException();
        }else if(element > value){
            IBinaryTreeNodeADT node = new BinaryTreeNodeADT();
            node.setElement(element);
            rightChild = node;
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public IBinaryTreeNodeADT getLeftChild() {
        if(leftChild != null){
            return leftChild;
        } else {
            throw new NullPointerException();
        }

    }

    @Override
    public IBinaryTreeNodeADT getRightChild() {
        if(rightChild != null){
            return rightChild;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean isElementInitialized() {
        return valueInitialized;
    }
}
