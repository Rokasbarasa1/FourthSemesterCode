package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PostFixCalculatorTest {
    PostFixCalculator calculator;
    @Test
    void calculatorInstantiated(){
        new PostFixCalculator();
    }

    @Nested
    class whenNew{
        @BeforeEach
        void instantiateCalculator(){
            calculator = new PostFixCalculator();
        }

        @Test
        void simpleAddition(){
            ArrayList<String> equation = new ArrayList<>();
            equation.add("2");
            equation.add("2");
            equation.add("+");
            assertTrue(calculator.calculate(equation) == 4);
        }

        @Test
        void simpleSubstraction(){
            ArrayList<String> equation = new ArrayList<>();
            equation.add("2");
            equation.add("2");
            equation.add("-");
            assertTrue(calculator.calculate(equation) == 0);
        }

        @Test
        void simpleDivision(){
            ArrayList<String> equation = new ArrayList<>();
            equation.add("2");
            equation.add("2");
            equation.add("/");
            assertTrue(calculator.calculate(equation) == 1);
        }

        @Test
        void simpleMultiplication(){
            ArrayList<String> equation = new ArrayList<>();
            equation.add("2");
            equation.add("2");
            equation.add("*");
            assertTrue(calculator.calculate(equation) == 4);
        }

        @Test
        void notPostFix(){
            ArrayList<String> equation = new ArrayList<>();
            equation.add("2");
            equation.add("*");
            equation.add("2");
            assertTrue(calculator.calculate(equation) == -1);
        }

        @Test
        void noOperator(){
            ArrayList<String> equation = new ArrayList<>();
            equation.add("2");
            equation.add("2");
            assertTrue(calculator.calculate(equation) == -1);
        }
    }
}