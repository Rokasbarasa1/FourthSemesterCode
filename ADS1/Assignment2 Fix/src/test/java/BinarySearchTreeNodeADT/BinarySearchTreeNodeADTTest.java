package BinarySearchTreeNodeADT;

import BinaryTreeNodeADT.BinaryTreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeNodeADTTest {
    IBinarySearchTreeNode node;

    @Test
    void treeInstantiation(){
        node = new BinarySearchTreeNode(11);
    }

    @Nested
    class whenNew{
        @BeforeEach
        void instantiateTree(){
            node = new BinarySearchTreeNode(11);
        }

        @Test
        void getElement(){
            assertEquals(node.getElement(), 11);
        }

        @Test
        void getLeftChildEmpty(){
            assertNull(node.getLeftChild());
        }

        @Test
        void getRightChildEmpty(){
            assertNull(node.getRightChild());
        }

        @Test
        void getParentEmpty(){
            assertNull(node.getParent());
        }

        @Test
        void addLeftChild(){
            node.addLeftChild(new BinaryTreeNode(41));
        }

        @Test
        void addRightChild(){
            node.addLeftChild(new BinaryTreeNode(20));
        }

        @Test
        void addParent(){
            node.setParent(new BinarySearchTreeNode(52));
        }

        @Test
        void setElement(){
            node.setElement(0);
            node.setElement(-1);
            node.setElement(-2);
            node.setElement(-54884);
            node.setElement(-2147483647);
            node.setElement(-2147483648);
            node.setElement(1);
            node.setElement(2);
            node.setElement(96641);
            node.setElement(2147483646);
            node.setElement(2147483647);
        }
    }

    @Nested
    class whenElementsAdded{
        @BeforeEach
        void instantiateTree(){
            node = new BinarySearchTreeNode(11);
            node.addLeftChild(new BinaryTreeNode(10));
            node.addRightChild(new BinaryTreeNode(28));
            node.setParent(new BinarySearchTreeNode(52));
        }

        @Test
        void getLeftChild(){
            assertTrue(node.getLeftChild().getElement() == 10);
        }

        @Test
        void getRightChild(){
            assertEquals(node.getRightChild().getElement(), 28);
        }

        @Test
        void getParent(){
            assertEquals(node.getParent().getElement(), 52);
        }


    }
}