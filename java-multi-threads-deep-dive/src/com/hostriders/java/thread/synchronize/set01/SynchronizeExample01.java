package com.hostriders.java.thread.synchronize.set01;

public class SynchronizeExample01 {
    private static int counter1 = 0;
    private static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            for (int i = 0; i< 1000; i++){
                increment1();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 0; i< 1000; i++){
                increment2();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Counter1: " + counter1 + " Counter2: " + counter2);
    }

    public static void increment1(){
        synchronized (lock1){
            counter1++;
        }
    }

    public static void increment2(){
        synchronized (lock2){
            counter2++;
        }
    }
}
