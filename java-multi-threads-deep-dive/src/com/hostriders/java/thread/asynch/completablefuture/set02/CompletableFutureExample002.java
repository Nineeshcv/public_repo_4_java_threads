package com.hostriders.java.thread.asynch.completablefuture.set02;

public class CompletableFutureExample002 {
    public static void main(String[] args) {
        try{
            throw new ArithmeticException();
        }catch (ArithmeticException e){
            System.out.println("Exception is handled");
        }
    }
}
