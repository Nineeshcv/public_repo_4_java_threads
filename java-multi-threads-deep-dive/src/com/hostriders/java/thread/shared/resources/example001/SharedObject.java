package com.hostriders.java.thread.shared.resources.example001;

public class SharedObject {
    private String message = "Hello, World!";

    public synchronized void setMessage(String message) {
        this.message = message;
    }

    public synchronized String getMessage() {
        return message;
    }

    public static void main(String[] args) {
        SharedObject object = new SharedObject();
        Thread thread1 = new Thread(() -> {
            object.setMessage("Thread 1 message");
            System.out.println("Thread 1 message: " + object.getMessage());
        });
        Thread thread2 = new Thread(() -> {
            object.setMessage("Thread 2 message");
            System.out.println("Thread 2 message: " + object.getMessage());
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}