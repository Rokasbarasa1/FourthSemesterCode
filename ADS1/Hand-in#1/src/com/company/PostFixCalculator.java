package com.company;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class PostFixCalculator {
    private StackGeneric<Integer> stack;

    public PostFixCalculator(){
        this.stack = new StackGeneric<>();
    }

    public double calculate(ArrayList<String> equation){
        int num1, num2;
        for (int i = 0; i < equation.size(); i++) {
            if(equation.get(i) != "/" && equation.get(i) != "-" && equation.get(i) != "+" && equation.get(i) != "*"){
                stack.push(Integer.parseInt(equation.get(i)));
            }
            else {
                try{
                    num1 = stack.pop();
                    num2 = stack.pop();
                }catch (EmptyStackException e){
                    return -1;
                }
                switch (equation.get(i)){
                    case "/":
                        stack.push(num2 / num1);
                        break;
                    case "*":
                        stack.push(num2 * num1);
                        break;
                    case "+":
                        stack.push(num2 + num1);
                        break;
                    case "-":
                        stack.push(num2 - num1);
                        break;
                    default:
                }
            }
        }
        int number = stack.pop();
        if(stack.empty()){
            return number;
        }else {
            return -1;
        }
    }
}
