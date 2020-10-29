package BinarySearchTreeADT;

import BinarySearchTreeNodeADT.BinarySearchTreeNode;
import BinarySearchTreeNodeADT.IBinarySearchTreeNode;

import Print.BinaryTreePrint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeADTTest {
    IBinarySearchTree tree;
    ArrayList<Integer> correctInOrder;
    ArrayList<Integer> correctPreOrder;
    ArrayList<Integer> correctPostOrder;
    ArrayList<Integer> correctLevelOrder;
    IBinarySearchTreeNode root;

    BinaryTreePrint binaryTreePrint;


    @Test
    void treeInstantiation(){
        tree = new BinarySearchTree(new BinarySearchTreeNode(1));
    }

    @Nested
    class whenNew{
        @BeforeEach
        void instantiateTree(){
            root = new BinarySearchTreeNode(1);
            tree = new BinarySearchTree(root);
        }

        @Test
        void testEmptyOnEmptyTree(){
            assertTrue(tree.isEmpty());
        }

        @Test
        void getRoot(){
            assertEquals(tree.getRoot(), root);
        }
        @Test
        void sizeEmpty(){
            assertEquals(tree.size(), 1);
        }

        @Test
        void containsNonExistent(){
            assertEquals(tree.contains(4), false);
        }

        @Test
        void containsEmpty(){
            assertEquals(tree.contains(1), true);
        }

        @Test
        void heightEmpty(){
            assertEquals(tree.height(), 0);
        }

        @Test
        void maxEmpty(){
            assertEquals(tree.findMax(), 1);
        }

        @Test
        void minEmpty(){
            assertEquals(tree.findMin(), 1);
        }

        @Test
        void removeMaxEmpty(){
            tree.removeMax();
            assertEquals(tree.getRoot(), null);
        }

        @Test
        void removeMinEmpty(){
            tree.removeMin();
            assertEquals(tree.getRoot(), null);
        }

        @Test
        void addElementAfterRemovingRoot(){
            tree.removeMin();
            tree.addElement(root);
            assertEquals(tree.getRoot(), root);
        }
    }

    @Nested
    class whenElementsAdded{
        @BeforeEach
        void instantiateTree(){
            binaryTreePrint = new BinaryTreePrint();
            root = new BinarySearchTreeNode(1);
            tree = new BinarySearchTree(root);
            tree.addElement(new BinarySearchTreeNode(7));
            tree.addElement(new BinarySearchTreeNode(9));
            tree.addElement(new BinarySearchTreeNode(8));
            tree.addElement(new BinarySearchTreeNode(6));
            tree.addElement(new BinarySearchTreeNode(10));
            tree.addElement(new BinarySearchTreeNode(12));
            tree.addElement(new BinarySearchTreeNode(7));

            binaryTreePrint.root = tree.getRoot();

            correctInOrder = new ArrayList<>();
            correctInOrder.add(1);
            correctInOrder.add(6);
            correctInOrder.add(7);
            correctInOrder.add(7);
            correctInOrder.add(8);
            correctInOrder.add(9);
            correctInOrder.add(10);
            correctInOrder.add(12);

            correctPreOrder = new ArrayList<>();
            correctPreOrder.add(1);
            correctPreOrder.add(7);
            correctPreOrder.add(6);
            correctPreOrder.add(9);
            correctPreOrder.add(8);
            correctPreOrder.add(7);
            correctPreOrder.add(10);
            correctPreOrder.add(12);

            correctPostOrder = new ArrayList<>();
            correctPostOrder.add(6);
            correctPostOrder.add(7);
            correctPostOrder.add(8);
            correctPostOrder.add(12);
            correctPostOrder.add(10);
            correctPostOrder.add(9);
            correctPostOrder.add(7);
            correctPostOrder.add(1);

            correctLevelOrder = new ArrayList<>();
            correctLevelOrder.add(1);
            correctLevelOrder.add(7);
            correctLevelOrder.add(6);
            correctLevelOrder.add(9);
            correctLevelOrder.add(8);
            correctLevelOrder.add(10);
            correctLevelOrder.add(7);
            correctLevelOrder.add(12);
        }

        @Test
        void isEmptyOnTreeWithMoreElementsFalse(){
            assertFalse(tree.isEmpty());
        }

        @Test
        void sizeAdded(){
            assertEquals(tree.size(), 8);
        }

        @Test
        void containsNonExistent(){
            assertEquals(tree.contains(4), false);
            assertEquals(tree.contains(-20), false);
            assertEquals(tree.contains(5), false);
            assertEquals(tree.contains(99), false);
            assertEquals(tree.contains(70), false);
            assertEquals(tree.contains(-8), false);

        }

        @Test
        void containsExisting(){
            assertEquals(tree.contains(1), true);
            assertEquals(tree.contains(7), true);
            assertEquals(tree.contains(8), true);
            assertEquals(tree.contains(6), true);
            assertEquals(tree.contains(9), true);
            assertEquals(tree.contains(10), true);
            assertEquals(tree.contains(12), true);
        }

        @Test
        void heightAdded(){
            assertEquals(tree.height(), 4);
        }

        @Test
        void inOrderAdded(){
            ArrayList<Integer> result = tree.inOrder();
            for (int i = 0; i < result.size(); i++) {
                if(result.get(i) !=correctInOrder.get(i)) {
                    fail();
                }
            }
        }

        @Test
        void preOrderAdded(){
            ArrayList<Integer> result = tree.preOrder();
            for (int i = 0; i < result.size(); i++) {
                if(result.get(i) != correctPreOrder.get(i)) {
                    fail();
                }
            }
        }

        @Test
        void postOrderAdded(){
            ArrayList<Integer> result = tree.postOrder();
            for (int i = 0; i < result.size(); i++) {
                if(result.get(i) != correctPostOrder.get(i)) {
                    fail();
                }
            }
        }

        @Test
        void levelOrderAdded(){
            ArrayList<Integer> result = tree.levelOrder();
            for (int i = 0; i < result.size(); i++) {
                if(result.get(i) != correctLevelOrder.get(i)) {
                    fail();
                }
            }
        }

        @Test
        void maxEmpty(){
            assertEquals(tree.findMax(), 12);
        }

        @Test
        void minEmpty(){
            assertEquals(tree.findMin(), 1);
        }

        @Test
        void removeMax(){
            tree.removeMax();
            assertEquals(tree.contains(12), false);
            binaryTreePrint.printTree();
        }

        @Test
        void removeMin(){
            tree.removeMin();
            assertEquals(tree.contains(1), false);
            binaryTreePrint.printTree();
        }

        @Test
        void removeLeaf(){
            tree.removeElement(6);
            binaryTreePrint.printTree();
        }

        @Test
        void removeWithOneChild(){
            tree.removeElement(8);
            binaryTreePrint.printTree();
        }

        @Test
        void removeWithOneChildAndNoParent(){

            tree.removeElement(1);
            binaryTreePrint.printTree();
        }

        @Test
        void removeWithTwoChildren(){
            tree.removeElement(9);
            binaryTreePrint.printTree();
        }

        @Test
        void removeAllOfValue(){
            tree.removeAllOccurrences(7);
            binaryTreePrint.printTree();
        }
    }
}