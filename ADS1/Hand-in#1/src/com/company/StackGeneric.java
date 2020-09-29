package com.company;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class StackGeneric<T> {
    private List<T> list;

    public StackGeneric() {
        this.list = new ArrayList<>(0);
    }

    public T pop(){
        T object;
        try {
            object = list.get(0);
            list.remove(0);
        }catch (IndexOutOfBoundsException | EmptyStackException e){
            throw new EmptyStackException();
        }
        return object;
    }

    public void push(T object){
        list.add(0, object);
    }

    //Implemented for fun, not acctualy used
    public T peek(){
        T object;
        try {
            object = list.get(0);
        }catch (IndexOutOfBoundsException | EmptyStackException e){
            throw new EmptyStackException();
        }
        return object;
    }
    //Implemented for fun, not acctualy used
    public boolean empty(){
        return list.isEmpty();
    }
    //Implemented for fun, not acctualy used
    public int search(Object e){
        if(list.contains(e)){
            for (int i = 0; i < list.size(); i++) {
                if(list.get(0) == e){
                    return i;
                }
            }
            return -1;
        }else {
            return -1;
        }
    }
}
