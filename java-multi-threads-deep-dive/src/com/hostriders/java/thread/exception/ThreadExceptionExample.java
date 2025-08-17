package com.hostriders.java.thread.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class ThreadExceptionExample {
    public static void main(String[] args)  {
        // Create a thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()  {

                // Simulate an exception
                //throw new RuntimeException("Something went wrong");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Callable<String> callable = () -> {
            int i = 10;
            if (10 == i) {
                throw new RuntimeException("Something went wrong");
            }
            Thread.sleep(1000);
            return "hello";
        };

        Future<String> result = null;
        try {
            result = Executors.newSingleThreadExecutor().submit(callable);
            System.out.println(result.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Set the UncaughtExceptionHandler
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Caught exception in thread: " + t.getName());
                System.out.println("Exception: " + e.getMessage());
            }
        });

        // Start the thread
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" Main Class -- completed....");
    }
}