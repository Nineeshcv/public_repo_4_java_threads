package com.hostriders.java.thread.joinexample.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThreadJoinCompletableFutureExample {
    public static void main(String[] args) {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread1: is starting");
            try {
                Thread.sleep(1000);
                System.out.println("Thread1 executing");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Thread1- finished";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread2: is starting");
            try {
                Thread.sleep(2000);
                System.out.println("Thread2 executing");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Thread2- finished";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread3: is starting");
            try {
                Thread.sleep(3000);
                System.out.println("Thread3 executing");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Thread3- finished";
        });

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread4: is starting");
            try {
                Thread.sleep(4000);
                System.out.println("Thread4 executing");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Thread4- finished";
        });
        CompletableFuture.allOf(future1, future2, future3, future4)
                .join();

        System.out.println("Main- Thread completed!!");
    }
}
