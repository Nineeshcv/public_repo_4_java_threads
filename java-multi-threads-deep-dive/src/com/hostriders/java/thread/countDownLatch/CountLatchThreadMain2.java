package com.hostriders.java.thread.countDownLatch;

import com.sun.tools.javac.Main;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountLatchThreadMain2 {
    private static final int THREAD_COUNT = 4;

    public static void main(String[] args) {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(THREAD_COUNT);

        Runnable worker = new Worker(start, done);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.execute(worker);
        }
        System.out.println("Main thread working!!");
        start.countDown();
        System.out.println("Main thread doing work!!");

        try {
            done.await();
            System.out.println("All threads completed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();
    }
}
class Worker implements Runnable{

    private static  final Random random = new Random();
    private CountDownLatch start;
    private CountDownLatch done;

    public Worker(CountDownLatch start, CountDownLatch done) {
        this.start = start;
        this.done = done;
    }

    @Override
    public void run() {
        try {
            doSomething("Entered run()");
            start.await();
            doSomething("Working!!");
            Thread.sleep(random.nextInt(1000));
            done.countDown();


        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    void doSomething(String text){
        System.out.println("[" + Thread.currentThread().getName() + "] " + text);
    }

}
