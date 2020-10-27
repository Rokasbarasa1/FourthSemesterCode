package BinaryTreeNodeADT;

import BinaryTreeADT.BinaryTreeADT;
import BinaryTreeADT.IBinaryTreeADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeNodeADTTest {
    IBinaryTreeNodeADT node;

    @Test
    void treeInstantiation(){
        node = new BinaryTreeNodeADT();
    }

    @Nested
    class whenNew{
        @BeforeEach
        void instantiateTree(){
            node = new BinaryTreeNodeADT();
        }

        @Test
        void getElementEmpty(){
            assertThrows(NullPointerException.class, node::getElement);
        }

        @Test
        void getLeftChildEmpty(){
            assertThrows(NullPointerException.class, node::getLeftChild);
        }

        @Test
        void getRightChildEmpty(){
            assertThrows(NullPointerException.class, node::getRightChild);
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

        @Test
        void setLeftChildWhenLargerThanParent(){
            node.setElement(8);
            try{
                node.addLeftChild(12);
                fail();
            }catch (IllegalArgumentException e){
            }
        }

        @Test
        void setLeftChildWhenSmallerOrEqualToParent(){
            node.setElement(8);
            node.addLeftChild(5);
            node.addLeftChild(8);
        }

        @Test
        void setLeftChildWhenNoParent(){
            try{
                node.addLeftChild(12);
                fail();
            }catch (IllegalArgumentException e){
            }
        }

        @Test
        void setRightChildWhenLargerThanParent(){
            node.setElement(8);
            node.addRightChild(12);
        }

        @Test
        void setRightChildWhenSmallerThanParent(){
            node.setElement(8);
            try{
                node.addRightChild(7);
                fail();
            }catch (IllegalArgumentException e){
            }
        }

        @Test
        void setRightChildWhenNoParent(){
            try{
                node.addRightChild(7);
                fail();
            }catch (IllegalArgumentException e){
            }
        }

        @Test
        void getElement(){
            node.setElement(27);
            assertEquals(node.getElement(), 27);
        }

        @Test
        void getLeftChild(){
            node.setElement(27);
            node.addLeftChild(10);
            assertTrue(node.getLeftChild().getElement() == 10);
        }

        @Test
        void getRightChild(){
            node.setElement(27);
            node.addRightChild(28);
            assertEquals(node.getRightChild().getElement(), 28);
        }
    }
}