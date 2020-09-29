package com.company;

import java.util.Calendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        while (true){
            System.out.println("Enter number of fibionaci cycles: ");
            long number = key.nextLong();
            long milisecondsStart = Calendar.getInstance().getTimeInMillis();
            System.out.println(fibionaciIterative(number));
            long milisecondsEnd = Calendar.getInstance().getTimeInMillis();
            System.out.println((milisecondsEnd-milisecondsStart) + " miliseconds");
            long milisecondsStartRecursive = Calendar.getInstance().getTimeInMillis();
            System.out.println(fibionaciRecursive(number+2));
            long milisecondsEndRecursive = Calendar.getInstance().getTimeInMillis();
            System.out.println((milisecondsEndRecursive-milisecondsStartRecursive) + " miliseconds");
        }
    }

    public static long fibionaciIterative(long count){
        long current = 1;
        long last = 1;
        long temp;
        for (int i = 0; i < count; i++) {
            temp = current;
            current = current + last;
            last = temp;
        }
        return current;
    }

    public static long fibionaciRecursive(long count){

        if(count == 0 || count == 1){
            return count;
        }else {
            return fibionaciRecursive(count - 1) + fibionaciRecursive(count - 2);
        }
    }
}
