package com.hostriders.java.thread.executor.exservice002;

import java.util.concurrent.*;

public class ExcecutorTest001 {
    public static void main(String[] args) {
        /*Executor executor  = Executors.newFixedThreadPool(4);

        for( int i = 0; i < 10; i++) {
            executor.execute( ()->{
                System.out.println("Task is running in thread " + Thread.currentThread().getName());
            });
        }
        //executor.shutdown();
       ((ExecutorService) executor).shutdown();*/ // Cannot directly cast Executor to ExecutorService

        ExecutorService executorService = Executors.newFixedThreadPool(4,
                new CustomThreadFactory());

        for( int i = 0; i < 4; i++) {
            executorService.execute( ()->{
                int a = 10/0;
                System.out.println("Task is running in thread " + Thread.currentThread().getName());
            });
        }
        executorService.shutdown();

        ExecutorService executorForCompletable = Executors.newFixedThreadPool(4,
                new CustomThreadFactory());
        CompletableFuture.supplyAsync(()->"Hello", executorForCompletable)
                //.handle((String s)->s.toUpperCase())
               // .exceptionally(Exception::getMessage)
               // .whenComplete((System.out::println))
                .thenAccept(System.out::println);

    }

    static class CustomThreadFactory implements ThreadFactory {

        private int count = 1;
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("MyThread-" + count++);
            //set priority
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.setUncaughtExceptionHandler((thread1,Exception)->{
                System.out.println("Exception Handleed for thread "+thread1.getName());
                System.out.println("Handled Exception is "+Exception.getMessage());
            });
            return thread;
        }
    }
}
