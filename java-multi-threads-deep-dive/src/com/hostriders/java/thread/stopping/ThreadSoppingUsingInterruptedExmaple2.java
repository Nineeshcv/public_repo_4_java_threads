package com.hostriders.java.thread.stopping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSoppingUsingInterruptedExmaple2 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(()->   {
            System.out.println("Starting thread");
            while( !Thread.currentThread().isInterrupted()){
                System.out.println("Thread is running");
            }
            System.out.println("Thread finished");
            return 1;
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Stopping the thread");
        threadPool.shutdownNow(); // Internally using thread.interrupt()
    }


}
