package com.hostriders.java.thread.shared.resources;

public class SharedResourceTest1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            SharedResource resource = new SharedResource(100);
            for (int i=0 ;i < 5; i++) {
                resource.increment();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "::"+resource.getCount());
        });

        Thread t2 = new Thread(() -> {
            SharedResource resource = new SharedResource(10);
            for (int i=0 ;i < 5; i++) {
                resource.increment();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "::"+resource.getCount());
        });

        t1.start();
        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SharedResource {
    private int count = 0;
    public SharedResource(int count) {
        this.count = count;
    }

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
