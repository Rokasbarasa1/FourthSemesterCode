package BinarySearchTreeADT;

import BinarySearchTreeNodeADT.BinarySearchTreeNodeADT;
import BinarySearchTreeNodeADT.IBinarySearchTreeNodeADT;
import BinaryTreeADT.BinaryTreeADT;
import BinaryTreeNodeADT.BinaryTreeNodeADT;
import BinaryTreeNodeADT.IBinaryTreeNodeADT;


public class BinarySearchTreeADT extends BinaryTreeADT implements IBinarySearchTreeADT  {

    public BinarySearchTreeADT(int value) {
        element = new BinarySearchTreeNodeADT(value);
    }

    @Override
    public void addElement(int value) {
        IBinarySearchTreeNodeADT parent = addElementToTree((IBinarySearchTreeNodeADT)getRoot(), value);
        if(parent.getElement() <= value){
            parent.addRightChild(value); // Might need to remake the add child to node instead of the int.
        }
    }

    private IBinarySearchTreeNodeADT addElementToTree(IBinarySearchTreeNodeADT element,int value) {
        //Find the node now
        if(element)
    }


    @Override
    public void removeElement(int value) {

    }

    @Override
    public void removeAllOccurrences(int value) {

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
        return findMaximumValue(getRoot());
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
