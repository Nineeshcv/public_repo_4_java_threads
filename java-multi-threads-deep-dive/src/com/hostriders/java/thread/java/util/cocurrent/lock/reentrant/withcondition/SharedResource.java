package com.hostriders.java.thread.java.util.cocurrent.lock.reentrant.withcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean ready = false;

    public void await() throws InterruptedException {
        lock.lock();
        try {
            while (!ready) {
                System.out.println("Waiting for signal");
                condition.await();
            }
            System.out.println("Signal received");
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        lock.lock();
        try {
            ready = true;
            condition.signal();
            System.out.println("Signal sent");
        } finally {
            lock.unlock();
        }
    }
}