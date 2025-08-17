package com.hostriders.java.thread.semaphore;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreWithCompletableFuture {
    public static void main(String[] args) {
        // Create a semaphore with 5 permits
        Semaphore semaphore = new Semaphore(5);

        // Create an executor service with 10 threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Submit 20 tasks to the executor
        for (int i = 0; i < 20; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    // Acquire a permit from the semaphore
                    semaphore.acquire();

                    // Simulate some work
                    System.out.println("Task is running");
                    Thread.sleep(1000);

                    // Release the permit back to the semaphore
                    semaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, executor);
        }

        // Shut down the executor
        executor.shutdown();
    }

    public static void test(){
        Semaphore semaphore = new Semaphore(5);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        CompletableFuture.supplyAsync(() -> {
                    try {
                        // Acquire a permit from the semaphore
                        semaphore.acquire();

                        // Simulate some work
                        System.out.println("Task is running");
                        Thread.sleep(1000);

                        // Release the permit back to the semaphore
                        semaphore.release();

                        return "Task completed";
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return "Task failed";
                    }
                }, executor)
                .thenApply(result -> {
                    System.out.println(result);
                    return result;
                })
                .exceptionally(ex -> {
                    System.out.println("Task failed with exception: " + ex.getMessage());
                    return null;
                });
    }
}