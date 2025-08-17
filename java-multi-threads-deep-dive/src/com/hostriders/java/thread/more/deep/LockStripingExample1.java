package com.hostriders.java.thread.more.deep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStripingExample1 {
    private final Lock[] locks = new ReentrantLock[10];
    
    public LockStripingExample1() {
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new ReentrantLock();
        }
    }
    
    public void update(int index, int value) {
        locks[index].lock();
        try {
            // Update the value at the given index
            // ...
        } finally {
            locks[index].unlock();
        }
    }
    
    public int getValue(int index) {
        locks[index].lock();
        try {
            // Return the value at the given index
            // ...
        } finally {
            locks[index].unlock();
        }
        return 0;
    }
}