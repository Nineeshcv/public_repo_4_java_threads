package com.hostriders.java.thread.threadlocal.test001;

public class ThreadLocalExample001 {

    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();


        Thread userThread1 = new Thread(()->{
            threadLocal.set("User Thread 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread Local : " +Thread.currentThread().getName()+": "+ threadLocal.get());
            threadLocal.remove();
        });
        Thread userThread2 = new Thread(()->{

            threadLocal.set("User Thread 2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread Local : "+Thread.currentThread().getName()+": " + threadLocal.get());
            threadLocal.remove(); // Important
            System.out.println("Thread Local : "+Thread.currentThread().getName()+": " + threadLocal.get());
        });
        userThread1.setName("User-1");
        userThread2.setName("User-2");
        userThread1.start();
        userThread2.start();


    }
}
