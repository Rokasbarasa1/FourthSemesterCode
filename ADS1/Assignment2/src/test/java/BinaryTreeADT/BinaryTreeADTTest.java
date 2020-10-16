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
        void testEmpty(){
            assertTrue(tree.isEmpty());
        }

        @Test
        void throwsExceptionWhenGetRoot(){
            assertThrows(NullPointerException.class, tree::getRoot);
        }


    }

}