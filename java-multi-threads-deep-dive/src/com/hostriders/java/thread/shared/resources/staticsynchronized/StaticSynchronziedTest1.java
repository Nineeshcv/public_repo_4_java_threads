package com.hostriders.java.thread.shared.resources.staticsynchronized;

public class StaticSynchronziedTest1 {
    public static void main(String[] args) {
       /* SharedResourceStatic resource = new SharedResourceStatic();
        Thread t1 = new Thread(() -> {

            for (int i=0 ;i < 5; i++) {
                resource.increment();
            }            System.out.println(Thread.currentThread().getName() + "::"+resource.getCount());
        });

        Thread t2 = new Thread(() -> {
            for (int i=0 ;i < 5; i++) {
                resource.increment();
            }            System.out.println(Thread.currentThread().getName() + "::"+resource.getCount());
        });

        t1.start();
        t2.start();*/

        /*Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                SharedResourceStatic.increment();
            }
            System.out.println(Thread.currentThread().getName() + "::" + SharedResourceStatic.getCount());
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                SharedResourceStatic.increment();
            }
            System.out.println(Thread.currentThread().getName() + "::" + SharedResourceStatic.getCount());
        });

        t1.start();
        t2.start();*/

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++){
                SharedResourceStatic resourceStatic = new SharedResourceStatic();
                resourceStatic.increment();

            }
            System.out.println( Thread.currentThread().getName() + "::" + SharedResourceStatic.getCount());
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++){
                SharedResourceStatic resourceStatic = new SharedResourceStatic();
                resourceStatic.increment();
            }
            System.out.println( Thread.currentThread().getName() + "::" + SharedResourceStatic.getCount());
        });

        t1.start();
        t2.start();


    }
}

class SharedResourceStatic {
    private static int count = 0;

    public static synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + "::" + count);
    }

    public static synchronized int getCount() {
        return count;
    }
}
