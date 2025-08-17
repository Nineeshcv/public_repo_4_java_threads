package com.hostriders.java.thread.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerExample2 {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread threadA = new Thread(() -> {
            String objectA = "Object A";
            System.out.println("Thread A: Exchanging " + objectA);
            try {
                String objectB = exchanger.exchange(objectA, 5, TimeUnit.SECONDS);
                System.out.println("Thread A: Received " + objectB);
            } catch (InterruptedException | TimeoutException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(10000); // simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String objectB = "Object B";
            System.out.println("Thread B: Exchanging " + objectB);
            String objectA = null;
            try {
                objectA = exchanger.exchange(objectB);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread B: Received " + objectA);
        });

        threadA.start();
        threadB.start();
    }
}