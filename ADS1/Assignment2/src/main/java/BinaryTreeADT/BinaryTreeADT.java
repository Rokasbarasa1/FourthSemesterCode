package BinaryTreeADT;

import BinaryTreeNodeADT.IBinaryTreeNodeADT;
import java.util.ArrayList;

public class BinaryTreeADT implements IBinaryTreeADT{
    IBinaryTreeNodeADT element;

    public BinaryTreeADT() {
        element = null;
    }

    @Override
    public IBinaryTreeNodeADT getRoot() {
        if(element == null){
            throw new NullPointerException();
        }else {
            return element;
        }
    }

    @Override
    public Boolean isEmpty() {
        if(element == null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        if(element == null){
            return 0;
        }else{
            int size = findSizeOfTree(element);
            return size;
        }
    }

    @Override
    public boolean contains(int value) {
        if(element == null){
            return false;
        }else {
            Boolean contains = false;
            contains = doesTreeContainValue(element,value);
            return false;
        }
    }

    @Override
    public ArrayList<IBinaryTreeNodeADT> inOrder() {
        return null;
    }

    @Override
    public ArrayList<IBinaryTreeNodeADT> preOrder() {
        return null;
    }

    @Override
    public ArrayList<IBinaryTreeNodeADT> postOrder() {
        return null;
    }

    @Override
    public ArrayList<IBinaryTreeNodeADT> levelOrder() {
        return null;
    }

    @Override
    public int height() {
        if(element == null){
            return 0;
        }else {
            return 0;
        }
    }

    private int findSizeOfTree(IBinaryTreeNodeADT element) {
        if(element.getLeftChild() != null && element.getRightChild() != null){
            return 1 + findSizeOfTree(element.getLeftChild()) + findSizeOfTree(element.getRightChild());
        } else if(element.getLeftChild() != null && element.getRightChild() == null){
            return 1 + findSizeOfTree(element.getLeftChild());
        } else if(element.getLeftChild() == null && element.getRightChild() != null){
            return 1 + findSizeOfTree(element.getRightChild());
        }
        return 1;
    }

    private Boolean doesTreeContainValue(IBinaryTreeNodeADT element, int value) {
        if(!element.isElementInitialized()){
            return false;
        }else if(element.getElement() == value){
            return true;
        } else if(element.getElement() >= value){
            return doesTreeContainValue(element.getLeftChild(), value);
        } else{
            return doesTreeContainValue(element.getRightChild(), value);
        }
    }
}
