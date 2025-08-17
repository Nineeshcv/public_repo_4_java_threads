package com.hostriders.java.thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreFairExample {
    public static void main(String[] args) {
        // fairness policy in semaphore: When fairness is set true, the semaphore follows the "first in first out" order.
        // This means that the longest waiting thread will get the permit first. This is useful when you want to ensure
        // that no thread is starved of the resource. However, this comes at the cost of reduced performance.
        // When fairness is set false, the semaphore follows no particular order and the permit is given to the thread
        // that is most likely to be ready to run. This is more efficient but can lead to thread starvation.
        Semaphore semaphore = new Semaphore(3, true); // allow up to 3 threads to access the resource with fairness

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println("Thread " + Thread.currentThread().getName() + " is waiting to access the resource");
                    semaphore.acquire(); // acquire a permit
                    System.out.println("Thread " + Thread.currentThread().getName() + " has acquired a permit and is accessing the resource");
                    TimeUnit.SECONDS.sleep(2); // simulate some work
                    System.out.println("Thread " + Thread.currentThread().getName() + " has finished accessing the resource and is releasing the permit");
                    semaphore.release(); // release the permit
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}