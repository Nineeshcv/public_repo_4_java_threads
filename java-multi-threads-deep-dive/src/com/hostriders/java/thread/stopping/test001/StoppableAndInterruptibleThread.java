package com.hostriders.java.thread.stopping.test001;

import java.util.concurrent.atomic.AtomicBoolean;

public class StoppableAndInterruptibleThread extends Thread {
    private AtomicBoolean running = new AtomicBoolean(true);

    public void stopThread() {
        running.set(false);
    }

    @Override
    public void run() {
        while (running.get() && !Thread.currentThread().isInterrupted()) {
            // do some work
            System.out.println("Thread is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread stopped");
    }

    public static void main(String[] args) {
        StoppableAndInterruptibleThread thread = new StoppableAndInterruptibleThread();
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // You can stop the thread using either of the following methods:
        // thread.stopThread();
        thread.interrupt();
    }
}