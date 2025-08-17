package com.hostriders.java.thread.threadgroup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadGroupWithExecutorService {
    public static void main(String[] args) {
        // Create a new thread group
        ThreadGroup threadGroup = new ThreadGroup("MyThreadGroup");

        // Create a thread factory that creates threads in the thread group
        /*ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                return new Thread(threadGroup, r);
            }
        };*/
        ThreadFactory threadFactory = (r)->{
            Thread thread = new Thread(threadGroup,()->{
                System.out.println("Task is running in thread " + Thread.currentThread().getName());
                System.out.println("Thread group " + threadGroup.getName() + " : " + threadGroup.activeCount());
            });
            return thread;
        };

        // Create an executor service with the thread factory
        ExecutorService executor = Executors.newFixedThreadPool(5, threadFactory);

        // Submit tasks to the executor
        for (int i = 0; i < 10; i++) {
            executor.submit(new Runnable() {
                public void run() {

                    System.out.println("Task is running in thread " + Thread.currentThread().getName() );
                }
            });
        }

        // Shut down the executor
        executor.shutdown();
    }
}