package com.hostriders.java.thread.phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserExample2 {
    public static void main(String[] args) {
        // Create an ExecutorService with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Create a Phaser with 5 parties and 3 phases
        Phaser phaser = new Phaser(5);

        // Submit 5 tasks to the ExecutorService
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println("Task " + Thread.currentThread().getName() + " started");

                // Phase 1: Initialize data
                System.out.println("Task " + Thread.currentThread().getName() + " initializing data");
                phaser.arriveAndAwaitAdvance();

                // Phase 2: Process data
                System.out.println("Task " + Thread.currentThread().getName() + " processing data");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                phaser.arriveAndAwaitAdvance();

                // Phase 3: Finalize data
                System.out.println("Task " + Thread.currentThread().getName() + " finalizing data");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                phaser.arriveAndAwaitAdvance();

                System.out.println("Task " + Thread.currentThread().getName() + " finished");
            });
        }

        // Shutdown the ExecutorService
        executor.shutdown();
    }
}