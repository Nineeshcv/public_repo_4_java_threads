package com.hostriders.java.thread.java.util.cocurrent.lock.readwrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

public class ReadWriteLockExample {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private int sharedData = 0;

    public void readData() {
        readLock.lock();
        try {
            System.out.println("Reading data: " + sharedData);
        } finally {
            readLock.unlock();
        }
    }

    public void writeData(int newData) {
        writeLock.lock();
        try {
            sharedData = newData;
            System.out.println("Writing data: " + sharedData);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();
        Thread readerThread = new Thread(() -> example.readData());
        Thread writerThread = new Thread(() -> example.writeData(10));

        readerThread.start();
        writerThread.start();
    }
}