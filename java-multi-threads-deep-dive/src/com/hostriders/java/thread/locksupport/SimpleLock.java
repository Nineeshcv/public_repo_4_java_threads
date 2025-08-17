package com.hostriders.java.thread.locksupport;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class SimpleLock {

    private boolean locked = false;
    private Thread owner = null;

    public void lock() {
        if (locked) {
            LockSupport.park(this);
        } else {
            locked = true;
            owner = Thread.currentThread();
        }
    }

    public void unlock() {
        if (owner == Thread.currentThread()) {
            locked = false;
            LockSupport.unpark(owner);
        }
    }
}