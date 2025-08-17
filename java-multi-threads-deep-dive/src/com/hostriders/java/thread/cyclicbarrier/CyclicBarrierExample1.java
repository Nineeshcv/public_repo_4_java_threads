package com.hostriders.java.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample1 {
    public static void main(String[] args) {
        int numberOfThread =  3;

        CyclicBarrier barrier = new CyclicBarrier(numberOfThread);
        for (int i = 0; i < numberOfThread; i++) {
           Thread thread = new Thread(()->{
               System.out.println("Thread " + Thread.currentThread().getName() + " is starting");
               try {
                  barrier.await();
               } catch (InterruptedException |BrokenBarrierException exception) {
                   exception.printStackTrace();
               }
               System.out.println("Thread " + Thread.currentThread().getName() + " has crossed the barrier");
           });

           thread.start();

        }
        System.out.println("Main - Thread completed Successfully");
    }
}
