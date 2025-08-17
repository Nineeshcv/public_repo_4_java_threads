package com.hostriders.java.thread.threadpool.custom;

import java.util.concurrent.*;

public class CompletionServiceExample01 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

       /* for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println("Task is running in thread "
                        + Thread.currentThread().getName());
            });
        }*/
       /* Future<String> result = executorService.submit(() -> {
            System.out.println("Task is running in thread - 0001 "
                    + Thread.currentThread().getName());
            return "Hello-001";
        });

        Future<String> resul2  =executorService.submit(() -> {
            System.out.println("Task is running in thread - 0002 "
                    + Thread.currentThread().getName());
            return "Hello-002";
        });

        Future<String> resul3  =executorService.submit(() -> {
            System.out.println("Task is running in thread - 0003 "
                    + Thread.currentThread().getName());
            return "Hello-003";
        });


        executorService.shutdown();

        try {
            System.out.println(result.get()); // block
            System.out.println(resul2.get());
            System.out.println(resul3.get());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println("========================");
        testCompletionService();
    }

    public static void testCompletionService(){

       ExecutorService executorService = Executors.newFixedThreadPool(4);
       CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
       completionService.submit(() -> {
            System.out.println("Task is running in thread - 0001 "
                    + Thread.currentThread().getName());
            return "Hello-001";
        });

        completionService.submit(() -> {
            System.out.println("Task is running in thread - 0002 "
                    + Thread.currentThread().getName());
            return "Hello-002";
        });

        completionService.submit(() -> {
            System.out.println("Task is running in thread - 0003 "
                    + Thread.currentThread().getName());
            return "Hello-003";
        });


        executorService.shutdown();

        try {
            for( int i = 0; i < 3; i++) {
                System.out.println( completionService.take().get());
            }
            //System.out.println( completionService.take().get());
          /* System.out.println(result.get()); // block
            System.out.println(resul2.get());
            System.out.println(resul3.get());*/
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
