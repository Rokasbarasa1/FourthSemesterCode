package BinaryTreeADT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeADTTest {
    IBinaryTreeADT tree;

    @Test
    void treeInstantiation(){
        tree = new BinaryTreeADT();
    }

    @Nested
    class whenNew{
        @BeforeEach
        void instantiateTree(){
            tree = new BinaryTreeADT();
        }

        @Test
        void testEmptyOnEmptyTree(){
            assertTrue(tree.isEmpty());
        }

        @Test
        void sizeEmpty(){
            assertEquals(tree.size(), 0);
        }

        @Test
        void containsEmpty(){
            assertEquals(tree.contains(4), false);
        }

        @Test
        void heightEmpty(){
            assertEquals(tree.height(), 0);
        }
    }

}