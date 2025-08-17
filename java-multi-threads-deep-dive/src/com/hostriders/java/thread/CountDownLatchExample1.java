package com.hostriders.java.thread;
import java.util.concurrent.CountDownLatch;
public class CountDownLatchExample1 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        Thread thread1 = new Thread(()->{
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            System.out.println("Thread1 Finished");
            latch.countDown();
        });

        Thread thread2 = new Thread(()->{
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            System.out.println("Thread2 Finished");
            latch.countDown();
        });

        Thread thread3 = new Thread(()->{
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            System.out.println("Thread3 Finished");
            latch.countDown();
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("All threads finished");
    }

}
