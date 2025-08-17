package com.hostriders.java.thread.problems.visibilitySync;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedCounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        SharedCounter sharedCounter = new SharedCounter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedCounter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedCounter.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final counter value: " + sharedCounter.getCounter());
    }
}