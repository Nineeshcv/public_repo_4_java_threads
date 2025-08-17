package com.hostriders.java.thread.stopping;

public class ThreadStoppigUsingVolatileExample4 {
    public static volatile boolean running = true;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while(running == true){
                System.out.println("Thread is running :"+Thread.currentThread().getName());
            }
        });
        Thread t2 = new Thread(()->{
            while(running == true){
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

        running = false;
        System.out.println("Stopping the thread");

    }
}
