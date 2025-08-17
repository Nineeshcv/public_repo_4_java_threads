package com.hostriders.java.thread.exception;

public class ThreadDefaultExceptionHanldertest {
    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler((t, e) ->
                System.out.println("Error in thread " + t.getName() + ": " + e.getMessage()));


        Thread thread1 = new Thread(()->{throw new RuntimeException("Error in thread 1");});
        Thread thread2 = new Thread(()->{throw new RuntimeException("Error in thread 2");});
        Thread thread3 = new Thread(()->{throw new RuntimeException("Error in thread 3");});
        Thread thread4 = new Thread(()->{throw new RuntimeException("Error in thread 4");});

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


    }
}
