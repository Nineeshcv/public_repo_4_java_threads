package com.hostriders.java.thread.joinexample.executorservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ThreadJoinCallableExecutorExample {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(4)) {

            Callable<String> thread1 = () -> {
                System.out.println("Thread1: is starting");
                try {
                    Thread.sleep(1000);
                    System.out.println("Thread1 executing");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Thread1- finished";
            };

            Callable<String> thread2 = () -> {
                System.out.println("Thread2: is starting");
                try {
                    Thread.sleep(2000);
                    System.out.println("Thread2 executing");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Thread2- finished";
            };

            Callable<String> thread3 = () -> {
                System.out.println("Thread3: is starting");
                try {
                    Thread.sleep(3000);
                    System.out.println("Thread3 executing");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Thread3- finished";
            };

            Callable<String> thread4 = () -> {
                System.out.println("Thread4: is starting");
                try {
                    Thread.sleep(4000);
                    System.out.println("Thread4 executing");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Thread4- finished";
            };

            List<Callable<String>> callables = new ArrayList<>();
            callables.add(thread1);
            callables.add(thread2);
            callables.add(thread3);
            callables.add(thread4);


            try {
                List<Future<String>> futures = executor.invokeAll(callables);
                for (Future<String> f : futures) {
                    System.out.println(f.get());
                }
                System.out.println("Main - Thread Completed!!");
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }


            executor.shutdown();
        }
    }
}
