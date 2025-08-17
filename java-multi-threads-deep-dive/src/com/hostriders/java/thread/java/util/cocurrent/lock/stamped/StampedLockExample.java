package com.hostriders.java.thread.java.util.cocurrent.lock.stamped;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    private final StampedLock lock = new StampedLock();
    private volatile int sharedData = 0;

    public void readData() {
        long stamp = lock.tryReadLock();
        if (stamp != 0) {
            try {
                System.out.println("Reading data: " + sharedData);
            } finally {
                lock.tryUnlockRead();
                // lock.unlockRead(stamp);
            }
        } else {
            System.out.println("Failed to acquire read lock");
        }
    }

    public void writeData(int newData) {
        long stamp = lock.writeLock();
        try {
            sharedData = newData;
            System.out.println("Writing data: " + sharedData);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public void tryOptimisticRead() {
        long stamp = lock.tryOptimisticRead();
        if (stamp != 0) {
            try {
                System.out.println("Optimistic read: " + sharedData);
            } finally {
                lock.tryUnlockRead();
                //lock.unlockRead(stamp);
            }
        } else {
            System.out.println("Failed to acquire optimistic read lock");
        }
    }

    public static void main(String[] args) {
        StampedLockExample example = new StampedLockExample();
        Thread readerThread = new Thread(() -> example.readData());
        Thread writerThread = new Thread(() -> example.writeData(10));
        Thread optimisticReaderThread = new Thread(() -> example.tryOptimisticRead());

        readerThread.start();
        writerThread.start();
        optimisticReaderThread.start();
    }
}