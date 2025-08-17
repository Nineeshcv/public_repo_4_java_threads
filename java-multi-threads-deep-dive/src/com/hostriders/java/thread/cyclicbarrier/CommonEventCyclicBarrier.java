package com.hostriders.java.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
public class CommonEventCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4, ()->{
            System.out.println("Calling the common event");
            callCommonEvent();
        });

        Thread thread1 = new Thread(()->{
            System.out.println("Thread1: is starting");
            try {
                barrier.await();
            } catch (InterruptedException  | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

        });
        Thread thread2 = new Thread(()->{
            System.out.println("Thread2: is starting");
            try {
                barrier.await();
            } catch (InterruptedException  | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(()->{
            System.out.println("Thread3: is starting");
            try {
                barrier.await();
            } catch (InterruptedException  | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread4 = new Thread(()->{
            System.out.println("Thread4: is starting");
            try {
                barrier.await();
            } catch (InterruptedException  | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


    }

    public static void callCommonEvent() {
        System.out.println("commonEvent()");
    }
}
