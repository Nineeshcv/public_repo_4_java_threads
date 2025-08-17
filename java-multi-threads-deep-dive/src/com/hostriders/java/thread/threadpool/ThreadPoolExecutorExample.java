package com.hostriders.java.thread.threadpool;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {
        // Core pool size: Number of threads to keep in the pool (even if idle)
        int corePoolSize = 2;

        // Maximum pool size: Maximum number of threads in the pool
        int maximumPoolSize = 4;

        // Keep-alive time: Time for which idle threads wait for new tasks before terminating
        long keepAliveTime = 10;

        // Time unit for keepAliveTime
        TimeUnit unit = TimeUnit.SECONDS;

        // Task queue: Queue to hold tasks before execution
        BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(2);

        // Thread factory: Optional, can be used to customize thread creation
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // Rejection handler: Handles tasks that cannot be executed
        RejectedExecutionHandler rejectionHandler = new ThreadPoolExecutor.AbortPolicy();

        // Create the ThreadPoolExecutor
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            unit,
            taskQueue,
            threadFactory,
            rejectionHandler
        );

        // Submit tasks to the thread pool
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
                try {
                    Thread.sleep(2000); // Simulate task processing time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Task " + taskId + " interrupted.");
                }
            });
        }

        // Gracefully shut down the executor
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
