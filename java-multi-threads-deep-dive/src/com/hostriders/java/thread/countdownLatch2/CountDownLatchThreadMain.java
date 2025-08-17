package com.hostriders.java.thread.countdownLatch2;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchThreadMain {
    public static void main(String[] args) {
        int threadCount = 4;
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new WorkerRunnable(latch, i));

            /*executorService.execute(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " completed");*/
            //});
        }
        try {
            latch.await(); /// Locking the main thread until all threads are completed
            System.out.println("All threads completed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
