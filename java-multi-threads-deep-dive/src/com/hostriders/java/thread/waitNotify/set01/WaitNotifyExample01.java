package com.hostriders.java.thread.waitNotify.set01;

import java.util.LinkedList;
import java.util.stream.Stream;

public class WaitNotifyExample01 {
    private static final Object LOCK = new Object();
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            one();
        });

        Thread thread2 = new Thread(() -> {
            two();
        });

        thread1.start();
        thread2.start();
    }
    public static void one() {
        synchronized (LOCK) {
            System.out.println("Inside the one():::");
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public static void two() {
        synchronized (LOCK) {
            System.out.println("Inside the two():::");
            LOCK.notify();
            System.out.println("Sending notifications to other therads");
        }
    }
}
