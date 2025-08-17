package com.hostriders.java.thread.threadlocal.test004;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadLocalWithThreadFactory {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                threadLocal.set("initial-value");
                return thread;
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(5, threadFactory);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " has value: " + threadLocal.get());
                // task execution
            }
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(task);
        }

        executor.shutdown();
    }
}