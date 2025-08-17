package com.hostriders.java.thread.semaphore.tasksAtTime;

import java.util.concurrent.*;

public class BlockingQueueSemaphoreExample {
    private static final int MAX_CONCURRENT_TASKS = 30;
    private static final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_TASKS);
    private static final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(50); // More than 30, as workers pick from queue

        // Producer: Adding tasks to queue
        for (int i = 1; i <= 100; i++) { // Assume 100 tasks
            final int taskId = i;
            taskQueue.add(() -> processTask(taskId));
        }

        // Consumer: Continuously processing tasks with Semaphore control
        for (int i = 0; i < 50; i++) { // More threads than semaphore permits
            executor.submit(() -> {
                while (true) {
                    try {
                        Runnable task = taskQueue.take(); // Get the next task
                        semaphore.acquire(); // Control concurrency
                        executor.submit(() -> {
                            try {
                                task.run();
                            } finally {
                                semaphore.release(); // Release permit after execution
                            }
                        });
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break; // Exit loop if interrupted
                    }
                }
            });
        }

        executor.shutdown();
    }

    private static void processTask(int taskId) {
        System.out.println("Processing Task " + taskId + " by " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulate task execution
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
