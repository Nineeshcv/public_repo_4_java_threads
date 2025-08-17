package com.hostriders.java.thread.asynch.completablefuture.exception;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureException02 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
            throw new RuntimeException("Exception is thrown- from hello");
        },service);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
            throw new RuntimeException("Exception is thrown- from world");
        });

        String result = future1
                .exceptionally(e->{
                    System.out.println("Exception is "+e.getMessage());
                    return " -- ";
                })
                .thenCombine(future2, (s1,s2)->s1+s2)
                .exceptionally(e->e.getMessage())
                .thenCompose(s->CompletableFuture.supplyAsync(()->s.toUpperCase(),service))
                .join();

        System.out.println(result);
    }
}
