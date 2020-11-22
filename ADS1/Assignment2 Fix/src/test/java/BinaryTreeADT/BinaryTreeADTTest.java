package BinaryTreeADT;

import BinaryTreeNodeADT.BinaryTreeNode;
import BinaryTreeNodeADT.IBinaryTreeNode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeADTTest {
    IBinaryTree tree;
    ArrayList<Integer> correctInOrder;
    ArrayList<Integer> correctPreOrder;
    ArrayList<Integer> correctPostOrder;
    ArrayList<Integer> correctLevelOrder;
    IBinaryTreeNode root;

    @Test
    void treeInstantiation(){
        tree = new BinaryTree(new BinaryTreeNode(1));
    }

    @Nested
    class whenNew{
        @BeforeEach
        void instantiateTree(){
            root = new BinaryTreeNode(1);
            tree = new BinaryTree(root);
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
    }

    @Nested
    class whenElementsAdded{
        @BeforeEach
        void instantiateTree(){
            tree = new BinaryTree(new BinaryTreeNode(1));
            tree.getRoot().addLeftChild(new BinaryTreeNode(7));
            tree.getRoot().addRightChild(new BinaryTreeNode(9));
            tree.getRoot().getLeftChild().addRightChild(new BinaryTreeNode(8));
            tree.getRoot().getLeftChild().addLeftChild(new BinaryTreeNode(6));
            tree.getRoot().getRightChild().addRightChild(new BinaryTreeNode(10));
            tree.getRoot().getRightChild().getRightChild().addRightChild(new BinaryTreeNode(12));

            correctInOrder = new ArrayList<>();
            correctInOrder.add(6);
            correctInOrder.add(7);
            correctInOrder.add(8);
            correctInOrder.add(1);
            correctInOrder.add(9);
            correctInOrder.add(10);
            correctInOrder.add(12);

            correctPreOrder = new ArrayList<>();
            correctPreOrder.add(1);
            correctPreOrder.add(7);
            correctPreOrder.add(6);
            correctPreOrder.add(8);
            correctPreOrder.add(9);
            correctPreOrder.add(10);
            correctPreOrder.add(12);

            correctPostOrder = new ArrayList<>();
            correctPostOrder.add(6);
            correctPostOrder.add(8);
            correctPostOrder.add(7);
            correctPostOrder.add(12);
            correctPostOrder.add(10);
            correctPostOrder.add(9);
            correctPostOrder.add(1);

            correctLevelOrder = new ArrayList<>();
            correctLevelOrder.add(1);
            correctLevelOrder.add(7);
            correctLevelOrder.add(9);
            correctLevelOrder.add(6);
            correctLevelOrder.add(8);
            correctLevelOrder.add(10);
            correctLevelOrder.add(12);
        }

        @Test
        void isEmptyOnTreeWithMoreElementsFalse(){
            assertFalse(tree.isEmpty());
        }

        @Test
        void sizeAdded(){
            assertEquals(tree.size(), 7);
        }

        @Test
        void containsNonExistent(){
            assertEquals(tree.contains(4), false);
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
            assertEquals(tree.height(), 3);
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
    }
}