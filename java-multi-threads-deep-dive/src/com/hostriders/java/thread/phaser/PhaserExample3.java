package com.hostriders.java.thread.phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserExample3 {
    public static void main(String[] args) {
        // Create an ExecutorService with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Create a Phaser with 5 parties
        Phaser phaser = new Phaser(5);

        // Submit 5 tasks to the ExecutorService
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println("Task " + Thread.currentThread().getName() + " started");

                // Arrive at the Phaser
                phaser.arriveAndAwaitAdvance();

                // Simulate some work
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Task " + Thread.currentThread().getName() + " finished");
            });
        }

        // Shutdown the ExecutorService
        executor.shutdown();
    }
}