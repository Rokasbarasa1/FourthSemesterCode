package BinarySearchTreeADT;

import BinaryTreeADT.BinaryTreeADT;
import BinaryTreeNodeADT.BinaryTreeNodeADT;
import BinaryTreeNodeADT.IBinaryTreeNodeADT;


public class BinarySearchTreeADT extends BinaryTreeADT implements IBinarySearchTreeADT  {
    @Override
    public void addElement(int element) {

    }

    @Override
    public void removeElement(int element) {

    }

    @Override
    public void removeAllOccurrences(int element) {

    }

    @Override
    public void removeMin() {

    }

    @Override
    public void removeMax() {

    }

    @Override
    public int findMin() {
        return findMinimumValue(getRoot());
    }

    @Override
    public int findMax() {
        return findMaximumValue();
    }

    private int findMinimumValue(IBinaryTreeNodeADT element){
        if(element.getLeftChild() != null){
            return findMinimumValue(element.getLeftChild());
        }else {
            return element.getElement();
        }
    }

    private int findMaximumValue(IBinaryTreeNodeADT element){
        if(element.getRightChild() != null){
            return findMaximumValue(element.getRightChild());
        }else {
            return element.getElement();
        }
    }
}
