package com.example.gawebawebogame;

public class javaBasic {
    public static void main(String[] args) {
        double randomDouble = Math.random();
        System.out.println("randomDouble = " + randomDouble);
        int randomInt = (int) (randomDouble * 3) + 1;
        System.out.println(randomInt);
    }
}
