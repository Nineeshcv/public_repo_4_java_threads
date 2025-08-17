package com.hostriders.java.thread.synchronize.set01;

public class StaticSynchronizeIssue01 {
    public  static int counter = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0; i< 1000; i++){
                increment1();
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i< 1000; i++){
                increment2();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Counter value: " + counter);

    }
    public static synchronized  void increment1(){
        counter++;
    }
    public static synchronized  void increment2(){
        counter++;
    }

}
