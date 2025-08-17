package com.hostriders.java.thread.asynch.completablefuture.exception;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureException01 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
            return "Hello";
        },service);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
            throw new RuntimeException("Exception is thrown");
        },service);

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(()->{
            return "Completed CompletableFuture!";
        });

        String result = future1
                .thenCombine(future2,(s1,s2)->s1+s2)
                .handle((s,e)->{
                    System.out.println("My Exception is "+e.getMessage());
                    return " -- ";
                })
              //  .exceptionally((e)->e.getMessage())
                .thenCombine(future3,(s1,s2)->s1+s2)
                .thenApply(s->s.toUpperCase())
                .join();

        System.out.println(result);
        //CompletableFuture<String> future3 = future1.thenApplyAsync(s->s.toUpperCase(),service);
        //CompletableFuture<String> future4 = future2.thenApplyAsync(s->s.toUpperCase(),service);

       // future3.thenAcceptAsync(System.out::println,service);
      //  future4.thenAcceptAsync(System.out::println,service);

    }
}
