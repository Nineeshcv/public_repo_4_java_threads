package com.hostriders.java.thread.threadpool.custom;

import java.util.concurrent.*;

public class CustomThreadPoolExample01 {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                10L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                r -> {
                    Thread t = new Thread(r);
                    t.setName("MyThread-" + t.getId());
                    t.setUncaughtExceptionHandler((thread1,Exception)->{
                        System.out.println("Exception Handleed for thread "+thread1.getName());
                        System.out.println("Handled Exception is "+Exception.getMessage());
                    });
                    return t;
                },
                new ThreadPoolExecutor.AbortPolicy()
        );

        for( int i = 0; i < 7; i++) {
            executor.execute( ()->{
                //int a = 10/0;
                System.out.println("Task is running in thread " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
        if( executor.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
            executor.shutdownNow();
            System.out.println("Completed the main Program!!");
        }

    }
}
