package com.hostriders.java.thread.java.util.cocurrent.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTryLockTest3 {
    public static void main(String[] args) {
        Counter2 counter = new Counter2();
        Thread t1 = new Thread(() -> {
            counter.increment();
        });

        Thread t2 = new Thread(() -> {
            counter.increment();
        });
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter.getCounter());
    }
}

class Counter2 {
    private int counter = 0;
    private final Lock lock = new ReentrantLock(true);

    public void increment() {

        if (lock.tryLock()) { // if it is locked by other thread return false
            try {
                counter++;
                System.out.println("Acquired lock by thread: " + Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Failed to acquire lock by thread: " + Thread.currentThread().getName());
        }

    }

    public int getCounter() {
        return counter;
    }
}
