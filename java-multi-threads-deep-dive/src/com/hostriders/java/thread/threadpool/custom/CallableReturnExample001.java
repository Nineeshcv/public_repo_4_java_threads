package com.hostriders.java.thread.threadpool.custom;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableReturnExample001 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

       /* executorService.execute(()->{ // Runnable void run()
            return "Hello";
        });*/
        Future<String> future = executorService.submit(()->{ // Callable  T call()
            return "Hello";
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        Future<String> future2 = executorService.submit(()->{ // Runnable  void run()
            System.out.println("Thread is running");
        }, "Hello - From Future2");

        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
