package com.hostriders.java.thread.communication.waitNotify;


public class ThreadWaitNotifyExmaple1 {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread one = new Thread(() -> {
                one();
        });

        Thread two = new Thread(() -> {
            two();
        });
            one.start();
            two.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main Thread has been completed!!");
    }

    private static void one() {
        synchronized (LOCK) {
            System.out.println("Inside the one():::");
            try {
                LOCK.wait();
                System.out.println("Completing one()::");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void two() {
        synchronized (LOCK) {
            System.out.println("Inside the two():::");
            LOCK.notify();
            System.out.println("Sending notifications to other therads");

        }
    }
}
