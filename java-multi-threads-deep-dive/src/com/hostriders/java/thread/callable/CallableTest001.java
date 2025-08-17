package com.hostriders.java.thread.callable;

import java.util.concurrent.*;

public class CallableTest001 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = ()->{
            Thread.sleep(1000);
            return "hello thread";
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> resultFuture = executor.submit(callable);

        try {
            String result = resultFuture.get(); // blocking call
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        CompletableFuture completableFuture = CompletableFuture
                .supplyAsync(() -> "hello completableFuture");

        System.out.println(completableFuture.get());


    }
}
