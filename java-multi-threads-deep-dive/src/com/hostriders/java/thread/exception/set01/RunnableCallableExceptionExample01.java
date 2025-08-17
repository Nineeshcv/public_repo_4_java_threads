package com.hostriders.java.thread.exception.set01;

import java.util.concurrent.Callable;

public class RunnableCallableExceptionExample01 {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hello");
        });
        thread.start();

        thread.interrupt();

        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "hello";
        };





    }
}
