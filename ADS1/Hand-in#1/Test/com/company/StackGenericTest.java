package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackGenericTest {
    StackGeneric<Object> stack;
    @Test
    void stackInstantiated(){
        new StackGeneric();
    }

    @Nested
    class newStack{

        @BeforeEach
        void createNewStack(){
            stack = new StackGeneric<>();
        }

        @Test
        void isEmpty(){
            assertTrue(stack.empty());
        }

        @Test
        void throwsExceptionWhenPopped(){
            assertThrows(EmptyStackException.class, stack::pop);
        }

        @Test
        void throwsExceptionWhenPeek(){
            assertThrows(EmptyStackException.class, stack::peek);
        }

        @Nested
        class AfterPushing{
            String element = "element";

            @BeforeEach
            void pushElement(){
                stack.push(element);
            }

            @Test
            void isNotEmpty(){
                assertTrue(!stack.empty());
            }

            @Test
            void returnsElementWhenPopped(){
                assertTrue(stack.pop().equals("element"));
                assertTrue(stack.empty());
            }

            @Test
            void returnElementPeek(){
                assertTrue(stack.peek().equals("element"));
                assertFalse(stack.empty());
            }

            @Test
            void searchElementThatExists(){
                assertTrue(stack.search("element") == 0);
            }

            @Test
            void searchElementThatDoesNotExists(){
                assertTrue(stack.search("element2") == -1);
            }
        }
    }
}