package com.hostriders.java.thread.shared.resources.example003;

import java.util.concurrent.atomic.AtomicReference;

public class SharedResource {
    private final AtomicReference<String> resource = new AtomicReference<>("Initial value");

    public void updateResource(String newValue) {
        resource.set(newValue);
    }

    public String getResource() {
        return resource.get();
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread thread1 = new Thread(() -> {
            resource.updateResource("New value from thread 1");
        });
        Thread thread2 = new Thread(() -> {
            resource.updateResource("New value from thread 2");
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Final resource value: " + resource.getResource());
    }
}