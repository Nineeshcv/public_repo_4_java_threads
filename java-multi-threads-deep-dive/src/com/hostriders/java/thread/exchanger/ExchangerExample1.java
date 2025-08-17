package com.hostriders.java.thread.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample1 {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread threadA = new Thread(() -> {
            String objectA = "Object A";
            System.out.println("Thread A: Exchanging " + objectA);
            String objectB = null;
            try {
                objectB = exchanger.exchange(objectA);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread A: Received " + objectB);
        });

        Thread threadB = new Thread(() -> {
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