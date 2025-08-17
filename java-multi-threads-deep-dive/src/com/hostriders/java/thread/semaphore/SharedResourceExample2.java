package com.hostriders.java.thread.semaphore;

import java.util.concurrent.Semaphore;

public class SharedResourceExample2 {
    private static final int MAX_THREADS = 5;
    private static final Semaphore semaphore = new Semaphore(MAX_THREADS);
    private static final SharedResource sharedResource = new SharedResource();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(); // acquire a permit
                    System.out.println("Thread " + Thread.currentThread().getName() + " is accessing the shared resource");
                    sharedResource.process(); // perform some work on the shared resource
                    System.out.println("Thread " + Thread.currentThread().getName() + " has finished accessing the shared resource");
                    semaphore.release(); // release the permit
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    private static class SharedResource {
        public void process() {
            try {
                Thread.sleep(1000); // simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}