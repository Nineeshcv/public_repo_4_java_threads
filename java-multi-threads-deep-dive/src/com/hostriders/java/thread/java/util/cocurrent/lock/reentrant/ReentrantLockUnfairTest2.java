package com.hostriders.java.thread.java.util.cocurrent.lock.reentrant;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUnfairTest2 {
    public static void main(String[] args) {
        Counter1 counter = new Counter1();
        Thread t1 = new Thread(()->{
            counter.increment();
        });

        Thread t2 = new Thread(()->{
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
        System.out.println(counter.getCount());
    }
}

 class Counter1{
    private int count = 0;
    private final Lock lock = new ReentrantLock(false); // Fairness false, not keep order of the lock

    public void increment(){
        lock.lock();
        System.out.println("Lock acquired by thread: " + Thread.currentThread().getName());
        try{
            count++;
            System.out.println("inside the first lock");
            lock.lock();
            count++;
            System.out.println("inside the second lock");
            lock.unlock();
            System.out.println("Lock2 released by thread: " + Thread.currentThread().getName());
        }finally{
            lock.unlock();
            System.out.println("Lock1 released by thread: " + Thread.currentThread().getName());
        }
    }
    public int getCount(){
        return count;
    }
}