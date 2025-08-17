package com.hostriders.java.thread.java.util.cocurrent.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
public class ReentrantLockTryLockTest4 {
    public static void main(String[] args) {
        Counter3 counter = new Counter3();
        Thread t1 = new Thread(() -> {
            counter.increment();
        });

        Thread t2 = new Thread(() -> {
            counter.increment();
        });
        t1.start();

        try {
            Thread.sleep(1000);
            t2.start();
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter.getCounter());
    }
}
class Counter3 {

    private int counter = 0;
    private final Lock lock = new ReentrantLock(false);

    public void increment() {
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                counter ++;
                System.out.println("Acquired lock by thread: " + Thread.currentThread().getName());
                lock.unlock();
            }else {
                System.out.println("Failed to acquire lock by thread: " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int  getCounter(){
        return counter;
    }
}
