package com.hostriders.java.thread.stopping;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadStop {



    public static void main(String[] args) {
        AtomicBoolean result = new AtomicBoolean(false);


        Thread myThread = new Thread(() -> {
            while (!result.get()) {
                System.out.println("Thread is running : " + Thread.currentThread().getName());
            }
        });

        //myThread.stop();

        myThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        result.set(true);
        System.out.println("Stopping the thread");
    }
}
