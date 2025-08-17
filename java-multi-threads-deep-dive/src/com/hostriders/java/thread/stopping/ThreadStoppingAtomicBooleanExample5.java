package com.hostriders.java.thread.stopping;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadStoppingAtomicBooleanExample5 {

    public static AtomicBoolean running = new AtomicBoolean(true);
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while(running.get() == true){
                System.out.println("Thread is running :"+Thread.currentThread().getName());
            }
        });
        Thread t2 = new Thread(()->{
            while(running.get() == true){
                System.out.println("Thread is running :"+Thread.currentThread().getName());
            }
        });

        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        stop();
        System.out.println("Stopping the thread");

    }

    public static void stop(){
        running.set(false);
    }
}
