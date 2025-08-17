package com.hostriders.java.thread.asynch.completablefuture.set01;

import java.util.concurrent.CompletableFuture;

public class FindLengthOfString {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()-> "Hello World")
                .thenApply(s->s.length())
                .thenAccept(System.out::println);

        CompletableFuture<String> result =CompletableFuture.supplyAsync(()-> "Hello World")
                .thenApply(String :: toUpperCase)
                .thenApplyAsync(s->s.length() + "-" + s);

        result.thenAccept(System.out::println).join();
    }
}
