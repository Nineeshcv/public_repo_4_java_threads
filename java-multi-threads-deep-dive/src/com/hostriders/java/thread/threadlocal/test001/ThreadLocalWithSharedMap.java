package com.hostriders.java.thread.threadlocal.test001;

import java.util.concurrent.ConcurrentHashMap;
    
    public class ThreadLocalWithSharedMap {
    
        private static volatile  ConcurrentHashMap<Long, String> sharedMap = new ConcurrentHashMap<>();
        private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    
        public static void main(String[] args) {
            Thread thread1 = new Thread(() -> {
                threadLocal.set("Value in Thread 1");
                sharedMap.put(Thread.currentThread().getId(), threadLocal.get());
                System.out.println("Thread 1: " + threadLocal.get());
            });
    
            Thread thread2 = new Thread(() -> {
                // Access the value from Thread 1 using the shared map
                String valueFromThread1 = sharedMap.get(Thread.currentThread().getId());
                System.out.println("Thread 2: " + valueFromThread1);
            });
    
            thread1.start();
            try {
                thread1.join(); // Ensure Thread 1 completes before Thread 2 starts
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread2.start();
        }
    }