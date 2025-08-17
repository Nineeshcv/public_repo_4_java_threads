package com.hostriders.java.thread.interview.quiz.set001;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Question02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->{
            System.out.println("Hello from " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }) ;
        thread.setName("New Thread");
        System.out.println(thread.getState());

        thread.start();
        System.out.println(thread.getState());

        Thread.sleep(1000);
        System.out.println(thread.getState());

        Thread.sleep(1000);
        System.out.println(thread.getState());

        Thread thread1 = new Thread(() ->{
            System.out.println("Hello from " + Thread.currentThread().getName());
            sharedMethod2();
        });


        Thread thread2 = new Thread(() ->{
            System.out.println("Hello from " + Thread.currentThread().getName());
            sharedMethod2();
        });
        thread1.start();
        thread2.start();
        Thread.sleep(4000);
        System.out.println("Thread1 state is "+thread1.getState());
        System.out.println("Thread2 state is "+thread2.getState());

    }

    public synchronized  static void sharedMethod(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized  static void sharedMethod2(){
        Lock lock =new ReentrantLock(true);
        try {
            lock.lock();
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
}
