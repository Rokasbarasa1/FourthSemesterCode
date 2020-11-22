package BinarySearchTreeADT;

import BinarySearchTreeNodeADT.BinarySearchTreeNode;
import BinarySearchTreeNodeADT.IBinarySearchTreeNode;
import BinaryTreeADT.BinaryTree;
import BinaryTreeNodeADT.IBinaryTreeNode;

import java.util.ArrayList;


public class BinarySearchTree extends BinaryTree implements IBinarySearchTree {

    public BinarySearchTree(IBinarySearchTreeNode value) {
        super(value);
    }

    @Override
    public void addElement(IBinarySearchTreeNode value) {
        if(element != null){
            IBinarySearchTreeNode parent = getParentForAdd((IBinarySearchTreeNode)getRoot(), value.getElement());
            IBinarySearchTreeNode newNode = new BinarySearchTreeNode(value.getElement());
            newNode.setParent(parent);
            if(parent.getElement() <= value.getElement()){
                parent.addRightChild(newNode);
            } else {
                parent.addLeftChild(newNode);
            }
        }else {
            element = value;
        }
    }

    private IBinarySearchTreeNode getParentForAdd(IBinarySearchTreeNode element, int value) {
        if(element.getElement() <= value){
            if(element.getRightChild() != null){
                return getParentForAdd((IBinarySearchTreeNode)element.getRightChild(), value);
            }else{
                return element;
            }
        } else {
            if(element.getLeftChild() != null){
                return getParentForAdd((IBinarySearchTreeNode)element.getLeftChild(), value);
            }else{
                return element;
            }
        }
    }


    @Override
    public void removeElement(int value) {
        removeElement((IBinarySearchTreeNode) element, value);
    }

    private void removeElement(IBinarySearchTreeNode root, int value){
        IBinarySearchTreeNode nodeToRemove = findNodeOccurance(root, value);
        if(nodeToRemove != null){
            if(nodeToRemove.getLeftChild() == null && nodeToRemove.getRightChild() == null){
                if(element.equals(nodeToRemove)){
                    element = null;
                } else {
                    if(nodeToRemove.getParent().getElement() <= value){
                        nodeToRemove.getParent().addRightChild(null);
                    }else {
                        nodeToRemove.getParent().addLeftChild(null);
                    }
                }
            }
            else if(nodeToRemove.getLeftChild() != null && nodeToRemove.getRightChild() == null){
                if(element.equals(nodeToRemove))
                    element = nodeToRemove.getLeftChild();
                else if(nodeToRemove.getParent().getElement() <= value){
                    nodeToRemove.getParent().addRightChild(nodeToRemove.getLeftChild());
                    ((IBinarySearchTreeNode) nodeToRemove.getLeftChild()).setParent(nodeToRemove.getParent());
                }else {
                    nodeToRemove.getParent().addLeftChild(nodeToRemove.getLeftChild());
                    ((IBinarySearchTreeNode) nodeToRemove.getLeftChild()).setParent(nodeToRemove.getParent());
                }
            }
            else if(nodeToRemove.getLeftChild() == null && nodeToRemove.getRightChild() != null) {
                if(element.equals(nodeToRemove))
                    element = nodeToRemove.getRightChild();
                else if(nodeToRemove.getParent().getElement() <= value){
                    nodeToRemove.getParent().addRightChild(nodeToRemove.getRightChild());
                    ((IBinarySearchTreeNode) nodeToRemove.getRightChild()).setParent(nodeToRemove.getParent());
                }else {
                    nodeToRemove.getParent().addLeftChild(nodeToRemove.getRightChild());
                    ((IBinarySearchTreeNode) nodeToRemove.getRightChild()).setParent(nodeToRemove.getParent());
                }
            }
            else if(nodeToRemove.getLeftChild() != null && nodeToRemove.getRightChild() != null){
                ArrayList<Integer> inOrder = inOrder();
                int inOrderSuccessorIndex = -1;
                for (int i = 0; i < inOrder.size(); i++) {
                    if(inOrder.get(i) == value){
                        inOrderSuccessorIndex = i +1;
                        break;
                    }
                }

                IBinarySearchTreeNode successor = findNodeOccurance((IBinarySearchTreeNode) nodeToRemove.getRightChild(), inOrder.get(inOrderSuccessorIndex));
                removeElement((IBinarySearchTreeNode)nodeToRemove.getRightChild(), successor.getElement());


                successor.addLeftChild(nodeToRemove.getLeftChild());
                ((IBinarySearchTreeNode) nodeToRemove.getLeftChild()).setParent(successor);
                if(nodeToRemove.getRightChild() != null){
                    successor.addRightChild(nodeToRemove.getRightChild());
                    ((IBinarySearchTreeNode) nodeToRemove.getRightChild()).setParent(successor);
                }


                IBinarySearchTreeNode parent = nodeToRemove.getParent();
                if(!nodeToRemove.equals(element)){
                    if(successor.getElement() >= parent.getElement()){
                        parent.addRightChild(successor);
                        successor.setParent(parent);
                    }else {
                        parent.addLeftChild(successor);
                        successor.setParent(parent);
                    }
                }
            }
        }
    }

    private IBinarySearchTreeNode findNodeOccurance(IBinarySearchTreeNode element, int value) {
        if(element.getElement() == value){
            return element;
        } else if(element.getElement() > value && element.getLeftChild() != null){
            return findNodeOccurance((IBinarySearchTreeNode)element.getLeftChild(), value);
        } else if(element.getElement() <= value && element.getRightChild() != null){
            return findNodeOccurance((IBinarySearchTreeNode)element.getRightChild(), value);
        }else {
            return null;
        }
    }

    @Override
    public void removeAllOccurrences(int value) {
        while (contains(value)){
            removeElement(value);
        }
    }

    @Override
    public void removeMin() {
        IBinarySearchTreeNode node = findMinimumValue(getRoot());
        if(node.equals(element)){
            element = node.getRightChild();
        }else if(node.getRightChild() != null) {
            node.getParent().addLeftChild((IBinarySearchTreeNode)node.getRightChild());
            ((IBinarySearchTreeNode) node.getLeftChild()).setParent(node.getParent());
        }else {
            node.getParent().addLeftChild(null);
        }
    }

    @Override
    public void removeMax() {
        IBinarySearchTreeNode node = findMaximumValue(getRoot());
        if(node.equals(element)){
            element = node.getLeftChild();
        }else if(node.getLeftChild() != null){
            node.getParent().addRightChild(node.getLeftChild());
            ((IBinarySearchTreeNode) node.getRightChild()).setParent(node.getParent());
        } else {
            node.getParent().addRightChild(null);
        }
    }

    @Override
    public int findMin() {
        return findMinimumValue(getRoot()).getElement();
    }

    private IBinarySearchTreeNode findMinimumValue(IBinaryTreeNode element){
        if(element.getLeftChild() != null){
            return findMinimumValue(element.getLeftChild());
        }else {
            return (IBinarySearchTreeNode)element;
        }
    }

    @Override
    public int findMax() {
        return findMaximumValue(getRoot()).getElement();
    }

    private IBinarySearchTreeNode findMaximumValue(IBinaryTreeNode element){
        if(element.getRightChild() != null){
            return findMaximumValue(element.getRightChild());
        }else {
            return (IBinarySearchTreeNode)element;
        }
    }

    @Override
    public boolean contains(int value) {
        return doesTreeContainValue(element,value);
    }

    private Boolean doesTreeContainValue(IBinaryTreeNode element, int value) {
        if(element.getElement() == value){
            return true;
        } else if(element.getElement() > value && element.getLeftChild() != null){
            return doesTreeContainValue(element.getLeftChild(), value);
        } else if(element.getElement() <= value && element.getRightChild() != null){
            return doesTreeContainValue(element.getRightChild(), value);
        }else {
            return false;
        }
    }

}
