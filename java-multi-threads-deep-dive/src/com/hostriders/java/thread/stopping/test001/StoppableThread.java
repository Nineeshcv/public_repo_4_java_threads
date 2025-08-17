package com.hostriders.java.thread.stopping.test001;

import java.util.concurrent.atomic.AtomicBoolean;

public class StoppableThread extends Thread {
    private AtomicBoolean running = new AtomicBoolean(true);

    public void stopThread() {
        running.set(false);
    }

    @Override
    public void run() {
        while (running.get()) {
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
        StoppableThread thread = new StoppableThread();
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        thread.stopThread();
    }
}