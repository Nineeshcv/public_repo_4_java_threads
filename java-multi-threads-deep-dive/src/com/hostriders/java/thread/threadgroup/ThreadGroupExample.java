package com.hostriders.java.thread.threadgroup;

public class ThreadGroupExample {
    public static void main(String[] args) {
        // Create a new thread group
        ThreadGroup group = new ThreadGroup("MyThreadGroup");

        // Create two new threads in the thread group
        Thread thread1 = new Thread(group, new Runnable() {
            public void run() {
                System.out.println("Thread 1 is running");
            }
        });

        Thread thread2 = new Thread(group,()-> {

                System.out.println("Thread 2 is running");

        });

        // Start the threads
        thread1.start();
        thread2.start();

        // Enumerate the threads in the thread group
        Thread[] threads = new Thread[group.activeCount()];
        group.enumerate(threads);

        // Print the thread names
        for (Thread thread : threads) {
            System.out.println("Thread name: " + thread.getName() );
        }
    }
}