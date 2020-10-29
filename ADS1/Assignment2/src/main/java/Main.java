import BinaryTreeADT.BinaryTreeADT;
import BinaryTreeADT.IBinaryTreeADT;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        IBinaryTreeADT binaryTree = new BinaryTreeADT(8);


        binaryTree.getRoot().addLeftChild(7);
        binaryTree.getRoot().getLeftChild().addRightChild(8);
        binaryTree.getRoot().getLeftChild().addLeftChild(6);
        binaryTree.getRoot().addRightChild(9);
        binaryTree.getRoot().getRightChild().addRightChild(10);
        binaryTree.getRoot().getRightChild().getRightChild().addRightChild(12);
        System.out.println("Contains 8 " + binaryTree.contains(8));
        System.out.println("Contains 6 " + binaryTree.contains(6));
        System.out.println("Contains 7 " + binaryTree.contains(7));
        System.out.println("Contains 8 " + binaryTree.contains(8));
        System.out.println("Contains 11 " + binaryTree.contains(11));
        System.out.println("Contains 10 " + binaryTree.contains(10));
        System.out.println("Root " + binaryTree.getRoot().getElement());
        System.out.println("Height " + binaryTree.height());
        System.out.println("size " + binaryTree.size());
        System.out.println("Empty " + binaryTree.isEmpty());
        System.out.println("Height ");

        System.out.println("InOrder ");
        ArrayList<Integer> listInOrder = binaryTree.inOrder();
        for (int i = 0; i < listInOrder.size(); i++) {
            System.out.println(listInOrder.get(i));
        }

        System.out.println("PreOrder ");
        ArrayList<Integer> listPreOrder = binaryTree.preOrder();
        for (int i = 0; i < listPreOrder.size(); i++) {
            System.out.println(listPreOrder.get(i));
        }

        System.out.println("PostOrder ");
        ArrayList<Integer> listPostOrder = binaryTree.postOrder();
        for (int i = 0; i < listPostOrder.size(); i++) {
            System.out.println(listPostOrder.get(i));
        }

        System.out.println("LevelOrder ");
        ArrayList<Integer> listLevelOrder = binaryTree.levelOrder();
        for (int i = 0; i < listLevelOrder.size(); i++) {
            System.out.println(listLevelOrder.get(i));
        }
    }
}
