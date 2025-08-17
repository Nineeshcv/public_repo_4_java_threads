package com.hostriders.java.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample1 {
    public static void main(String[] args) {
        // Create a semaphore with 5 permits
        Semaphore semaphore = new Semaphore(5);

        // Create an executor service with 10 threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Submit 20 tasks to the executor
        for (int i = 0; i < 20; i++) {
            executor.submit(() -> {
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
            });
        }

        // Shut down the executor
        executor.shutdown();
    }
}