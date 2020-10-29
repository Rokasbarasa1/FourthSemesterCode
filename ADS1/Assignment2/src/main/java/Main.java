import BinarySearchTreeADT.BinarySearchTree;
import BinarySearchTreeADT.IBinarySearchTree;
import BinaryTreeADT.BinaryTree;
import BinaryTreeADT.IBinaryTree;
import BinaryTreeNodeADT.BinaryTreeNode;
import BinarySearchTreeNodeADT.BinarySearchTreeNode;
import Print.BinaryTreePrint;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        IBinarySearchTree searchTree = new BinarySearchTree(new BinarySearchTreeNode(50));
        BinaryTreePrint binaryTreePrint = new BinaryTreePrint();
        binaryTreePrint.root = searchTree.getRoot();

        searchTree.addElement(new BinarySearchTreeNode(25));
        searchTree.addElement(new BinarySearchTreeNode(912));
        searchTree.addElement(new BinarySearchTreeNode(2));
        searchTree.addElement(new BinarySearchTreeNode(99));
        searchTree.addElement(new BinarySearchTreeNode(12));
        searchTree.addElement(new BinarySearchTreeNode(60));
        searchTree.addElement(new BinarySearchTreeNode(70));
        searchTree.addElement(new BinarySearchTreeNode(55));
        searchTree.addElement(new BinarySearchTreeNode(11));
        searchTree.addElement(new BinarySearchTreeNode(8));
        searchTree.addElement(new BinarySearchTreeNode(25));
        searchTree.addElement(new BinarySearchTreeNode(1));

        System.out.println("MAX " + searchTree.findMax());
        System.out.println("Min " + searchTree.findMin());

        searchTree.removeAllOccurrences(25);
        searchTree.removeElement(60);


        binaryTreePrint.printTree();

    }
}
