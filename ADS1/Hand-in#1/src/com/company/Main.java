package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        PostFixCalculator calculator = new PostFixCalculator();
        ArrayList<String> equation = new ArrayList<>();
        equation.add("4");
        equation.add("3");
        equation.add("2");
        equation.add("+");
        equation.add("*");
        equation.add("5");

        System.out.println(calculator.calculate(equation));
    }
}
