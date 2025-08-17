package com.hostriders.java.thread.asynch.completablefuture.set02;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
public class TestCompletableFuture001 {
    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(()-> "Hello World")
                .thenApplyAsync(s->s.length())
                .thenAcceptAsync(System.out::println);

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()-> "Hello")
                .thenApply(String :: toUpperCase);

        CompletableFuture<String> world = CompletableFuture.supplyAsync(()-> "World")
                .thenApply(String :: toUpperCase);

        CompletableFuture<String> result = hello.thenCombine(world,
                (h, w) -> h + " " + w);

        result.thenAccept(System.out::println);


        Instant start = Instant.now();
       /* String resultString = printHello() + " " + printWorld();
        System.out.println(resultString);*/
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(
                TestCompletableFuture001::printHello);

        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(
                TestCompletableFuture001::printWorld
        );

        CompletableFuture<String> resultFuture = helloFuture.thenCombine(worldFuture,
                (h, w) -> h + " " + w);

        resultFuture.thenAccept(s->{
            System.out.println(s);
            System.out.println(Thread.currentThread().getName());
        }).join();

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Total time: " + duration.toMillis());


    }



    public static  String printHello() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello";
    }
    public static  String printWorld()  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "World";
    }
}
