package com.hostriders.java.thread.stopping;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Stopping a thread using interrupt
 * using ShutdownNow() and Shutdown() won't stop the thread
 * future.cancel(true)
 */
public class ThreadStopUsingInterruptExample1 {
    public static void main(String[] args) {
        //threadStopTest2();
        Thread thread1 = new Thread(()->{

            while(!Thread.currentThread().isInterrupted()){
                System.out.println("Thread is running!!");
            }
            System.out.println("Interrupted");
            return;
        });


        thread1.start();
        System.out.println("Interrupting  the tread");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread1.interrupt();
    }



    public static  void threadStopTest1(){
        // Using shoutdownNow() and shutdown()
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<?> future = threadPool.submit(()->   {
            System.out.println("Thread is running");
            Thread.sleep(3000);
            System.out.println("Thread finished");
            return 1;
        });
        try {
            System.out.println(future.get()); // Blocking call
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        threadPool.shutdownNow();
        System.out.println("Thread stopped");
    }
    public static void threadStopTest2(){
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<?> future = threadPool.submit(()->   {
            System.out.println("Thread is running");
            Thread.sleep(3000);
            System.out.println("Thread finished");
            return 1;
        });
        try {
            System.out.println(future.get()); // Blocking call
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        future.cancel(true);
        threadPool.shutdown();
        System.out.println("Thread stopped");
    }
}
