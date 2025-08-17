package com.hostriders.java.thread.shared.resources;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResourceTest2 {

    public static void main(String[] args) {
        SharedResource2 resource = new SharedResource2(100);
        Thread t1 = new Thread(() -> {

            for (int i=0 ;i < 5; i++) {
                resource.increment();
            }

           /* try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
           // System.out.println(Thread.currentThread().getName() + " getCount()::"+resource.getCount());

        });

        Thread t2 = new Thread(() -> {
            for (int i=0 ;i < 5; i++) {
                resource.increment();
            }
           /* try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            //System.out.println(Thread.currentThread().getName() + "getCount()::"+resource.getCount());
        });

        t1.start();
        t2.start();

        try {
            //Thread.sleep(2000);
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " getCount()::"+resource.getCount());
    }
}

class SharedResource2 {
    private final AtomicInteger value;
   // int count = 0;
    public SharedResource2(int count) {
        this.value = new AtomicInteger(count);
    }

    public  void increment() {
        //count++;
        value.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + "::"+value.toString());
    }

    public  synchronized AtomicInteger getCount() {
        return value
                ;
    }
}
