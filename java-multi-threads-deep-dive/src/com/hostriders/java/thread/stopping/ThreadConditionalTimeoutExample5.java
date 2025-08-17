package com.hostriders.java.thread.stopping;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Also can use Future.get(10, TimeUinit.MILLISECONDS);
 */
public class ThreadConditionalTimeoutExample5 {
    public static AtomicBoolean running = new AtomicBoolean(true);
    Consumer<String> consumer = s -> System.out.println(s);

    Consumer<String> consumer2 = s -> {
        try {
            Thread.sleep(1000);
            System.out.println(s.toUpperCase());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    };

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(()->{
            System.out.println("Thread is running");
            while(running.get() == true){
                System.out.println("Thread is running :"+Thread.currentThread().getName());
            }
        }, 1000, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        stop();
        System.out.println("Stopping the thread");
        scheduler.shutdown();

    }

    public static void stop(){
        running.set(false);
    }

}
