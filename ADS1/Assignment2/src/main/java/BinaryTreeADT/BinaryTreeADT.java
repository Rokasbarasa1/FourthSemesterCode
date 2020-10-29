package BinaryTreeADT;

import BinaryTreeNodeADT.BinaryTreeNodeADT;
import BinaryTreeNodeADT.IBinaryTreeNodeADT;
import java.util.ArrayList;

public class BinaryTreeADT implements IBinaryTreeADT{
    protected IBinaryTreeNodeADT element;

    public BinaryTreeADT(int value) {
        element = new BinaryTreeNodeADT(value);
    }

    public BinaryTreeADT() {
    }

    @Override
    public IBinaryTreeNodeADT getRoot() {
        return element;
    }

    @Override
    public Boolean isEmpty() {
        if(element.getLeftChild() != null && element.getRightChild() != null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        int size = findSizeOfTree(element);
        return size;
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

    @Override
    public boolean contains(int value) {
        return doesTreeContainValue(element,value);
    }

    private Boolean doesTreeContainValue(IBinaryTreeNodeADT element, int value) {
        if(element.getElement() == value){
            return true;
        } else if(element.getElement() >= value && element.getLeftChild() != null){
            return doesTreeContainValue(element.getLeftChild(), value);
        } else if(element.getElement() < value && element.getRightChild() != null){
            return doesTreeContainValue(element.getRightChild(), value);
        }else {
            return false;
        }
    }

    @Override
    public ArrayList<Integer> inOrder() {
        ArrayList<Integer> order = new ArrayList<>();
        traverseInOrder(element, order);
        return order;
    }

    private void traverseInOrder(IBinaryTreeNodeADT element, ArrayList<Integer> order) {
        if(element.getLeftChild() != null && element.getRightChild() != null){
            traverseInOrder(element.getLeftChild(), order);
            order.add(element.getElement());
            traverseInOrder(element.getRightChild(), order);
        } else if(element.getLeftChild() != null && element.getRightChild() == null){
            traverseInOrder(element.getLeftChild(), order);
            order.add(element.getElement());
        } else if(element.getLeftChild() == null && element.getRightChild() != null){
            order.add(element.getElement());
            traverseInOrder(element.getRightChild(), order);
        } else {
            order.add(element.getElement());
        }
    }

    @Override
    public ArrayList<Integer> preOrder() {
        ArrayList<Integer> order = new ArrayList<>();
        traversePreOrder(element, order);
        return order;
    }

    private void traversePreOrder(IBinaryTreeNodeADT element, ArrayList<Integer> order) {
        order.add(element.getElement());
        if(element.getLeftChild() != null){
            traversePreOrder(element.getLeftChild(), order);
        }
        if(element.getRightChild() != null){
            traversePreOrder(element.getRightChild(), order);
        }
    }

    @Override
    public ArrayList<Integer> postOrder() {
        ArrayList<Integer> order = new ArrayList<>();
        traversePostOrder(element, order);
        return order;
    }

    private void traversePostOrder(IBinaryTreeNodeADT element, ArrayList<Integer> order) {
        if(element.getLeftChild() != null){
            traversePostOrder(element.getLeftChild(), order);
        }
        if(element.getRightChild() != null){
            traversePostOrder(element.getRightChild(), order);
        }
        order.add(element.getElement());
    }

    @Override
    public ArrayList<Integer> levelOrder() {
        ArrayList<Integer> order = new ArrayList<>();
        boolean forward = true;
        int level = 0;
        while (forward){
            forward = traverseLevelOrder(element, order, level);
            if(forward){
                level++;
            }
        }
        return order;
    }

    private boolean traverseLevelOrder(IBinaryTreeNodeADT element, ArrayList<Integer> order, int drill) {
        if(drill == 0 && element.getLeftChild() != null || drill == 0 && element.getRightChild() != null){
            order.add(element.getElement());
            return true;
        }else if(drill == 0 && element.getLeftChild() == null && element.getRightChild() == null){
            order.add(element.getElement());
            return false;
        }else if(drill != 0 && element.getLeftChild() != null && element.getRightChild() != null){
            boolean left = traverseLevelOrder(element.getLeftChild(), order, drill-1);
            boolean right = traverseLevelOrder(element.getRightChild(), order, drill-1);
            return  left || right;
        } else if(drill != 0 && element.getLeftChild() != null && element.getRightChild() == null){
            return traverseLevelOrder(element.getLeftChild(), order, drill-1);
        } else if(drill != 0 && element.getLeftChild() == null && element.getRightChild() != null){
            return traverseLevelOrder(element.getRightChild(), order, drill-1);
        } else {
            return false;
        }
    }

    @Override
    public int height() {
        return getHeight(element, 0);
    }

    private int getHeight(IBinaryTreeNodeADT element, int height) {
        if(element.getLeftChild() != null && element.getRightChild() != null){
            int left = getHeight(element.getLeftChild(), height+1);
            int right = getHeight(element.getRightChild(), height+1);
            if(left > right){
                return left;
            } else if(left < right){
                return right;
            }else {
                return left;
            }
        } else if(element.getLeftChild() != null && element.getRightChild() == null){
            return getHeight(element.getLeftChild(), ++height);
        } else if(element.getLeftChild() == null && element.getRightChild() != null){
            return getHeight(element.getRightChild(), ++height);
        }else {
            return height;
        }
    }
}
