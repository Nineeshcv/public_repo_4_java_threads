package com.hostriders.java.thread.threaddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadSet001 {

    public static void main(String[] args) {
        /*for (int i = 0; i < 100; i++) {
            System.out.println("Main Thread: "+i);
        }*/


        Thread t1 = new Thread(()->{
           /* for (int i = 0; i < 100; i++) {
                System.out.println("Child Thread: "+i);
                //try {
                    //Thread.sleep(100);
               *//* } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*//*
            }*/
        });
        System.out.println(t1.getState());
       // t1.setDaemon(true);

        //System.out.println("Main Thread End");

        Thread t2 = new Thread(()->{
            // print alphabet
            /*for (char c = 'a'; c <= 'z'; c++) {
                System.out.println("Main Thread: "+c);
            }*/
        });
        //t1.setDaemon(true);
        t1.start();
        System.out.println("After Start" +t1.getState()); // runnable
        t2.start();
        //System.out.println("Main Thread End");
        synchronized (t1) {
            System.out.println(Thread.currentThread().getState()); // blocked
            try {
                t1.wait();  ///  Wating for t1 to complete
                System.out.println(Thread.currentThread().getState());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        synchronized (t2) {
            System.out.println(Thread.currentThread().getState()); // blocked

                t1.notify();  ///  Wating for t1 to complete
                System.out.println(Thread.currentThread().getState());

        }
        System.out.println(Thread.currentThread().getState());


    }
}
