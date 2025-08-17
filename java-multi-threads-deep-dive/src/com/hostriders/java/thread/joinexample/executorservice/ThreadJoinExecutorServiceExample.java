package com.hostriders.java.thread.joinexample.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadJoinExecutorServiceExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable thread1 = () ->{
            System.out.println("Thread1: is starting");
            try {
                Thread.sleep(1000);
                System.out.println("Thread1 finished");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable thread2 = () ->{
            System.out.println("Thread2: is starting");
            try {
                Thread.sleep(2000);
                System.out.println("Thread2 finished");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable thread3 = () ->{
            System.out.println("Thread3: is starting");
            try {
                Thread.sleep(3000);
                System.out.println("Thread3 finished");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable thread4 = () ->{
            System.out.println("Thread4: is starting");
            try {
                Thread.sleep(4000);
                System.out.println("Thread4 finished");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        executor.submit(thread1);
        executor.submit(thread2);
        executor.submit(thread3);
        executor.submit(thread4);

        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("All threads completed");
    }
}
