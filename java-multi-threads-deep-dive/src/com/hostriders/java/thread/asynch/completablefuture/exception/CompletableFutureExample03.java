package com.hostriders.java.thread.asynch.completablefuture.exception;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample03 {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
            throw new RuntimeException("Exception is thrown- from hello");
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
            throw new RuntimeException("Exception is thrown- from world");
        });
        String result = future1
                .whenComplete((s,e)->System.out.println("Exception is "+e.getMessage()))
                .thenCombine(future2, (s1,s2)->s1+s2)
                .whenComplete((s,e)->System.out.println("Exception is "+e.getMessage()))
                .exceptionally(e->e.getMessage())
                .thenApply(s->s.toUpperCase())
                .thenCompose(s->CompletableFuture.supplyAsync(()->s + " Completed CompletableFuture!"))
                .join();
        System.out.println(result);
    }
}
