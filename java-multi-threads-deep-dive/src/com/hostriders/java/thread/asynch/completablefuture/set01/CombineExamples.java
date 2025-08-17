package com.hostriders.java.thread.asynch.completablefuture.set01;

import java.util.concurrent.CompletableFuture;

public class CombineExamples {
    public static void main(String[] args) {
       /* CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(()-> "Hello");
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(()-> "World");

        helloFuture.thenCombineAsync(worldFuture, (h, w) -> h+ w)
                .thenApplyAsync(s->s.toUpperCase())
                .thenAcceptAsync(System.out::println);*/



        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()-> {
            //throw new RuntimeException("Exception is thrown- from hello");
            return "Hello";
        });
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()-> {
            throw new RuntimeException("Exception is thrown- from world");
           // return "World";
        });


        hello
                .handle((s, e) -> {
                  //  System.out.println("My Exception is " + e.getMessage());
                    if( e != null){
                        System.out.println("My Exception is " + e.getMessage());
                    }
                    return s;
                })
                .thenCombine(world, (h, w) -> h + w)
                .whenComplete((s, e) -> System.out.println("Exception is " + e.getMessage()))
                .exceptionally(e -> e.getMessage())
                .thenApply(s -> s.toUpperCase()) // map
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Completed CompletableFuture!"))
                .thenAccept(System.out::println); // consume



    }
}
