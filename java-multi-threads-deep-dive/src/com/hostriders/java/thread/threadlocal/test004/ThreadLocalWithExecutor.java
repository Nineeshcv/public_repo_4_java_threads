package com.hostriders.java.thread.threadlocal.test004;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalWithExecutor {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    threadLocal.set("task-specific-value -" + Thread.currentThread().getName());
                    System.out.println("Thread " + Thread.currentThread().getName() + " has value: " + threadLocal.get());
                    // task execution
                } finally {
                    threadLocal.remove();
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(task);
        }

        executor.shutdown();
    }
}