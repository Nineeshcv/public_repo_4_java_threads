package com.hostriders.java.thread.stopping;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadSoppingUsingInterruptExample3 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        Future<?> future = threadPool.submit(()->   {
            System.out.println("Starting thread");
            while( !Thread.currentThread().isInterrupted())
                System.out.println("Thread is running");
            System.out.println("Thread finished");
            return 1;
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Stopping the thread");
        future.cancel(true);
       /* try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }*/
        threadPool.shutdown();
    }
}
