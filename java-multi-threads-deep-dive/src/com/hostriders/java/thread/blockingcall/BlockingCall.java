package com.hostriders.java.thread.blockingcall;
import java.util.concurrent.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class BlockingCall {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


       /* ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int j = 0; j < 1000; j++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "::" + test());
            });
        }
        executorService.shutdown();*/


        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()-> test());
        String result = completableFuture.get();
        System.out.println("My result is " + result);
        for( int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + "::" + i);
        }


    }

    public static  String test() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "test";
    }
}
