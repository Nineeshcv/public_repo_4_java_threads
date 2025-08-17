package com.hostriders.java.thread.java.util.cocurrent.lock.reentrant.withcondition;

public class ProducerConsumer {
    private final SharedResource sharedResource = new SharedResource();

    public void producer() throws InterruptedException {
        sharedResource.signal();
    }

    public void consumer() throws InterruptedException {
        sharedResource.await();
        System.out.println("Consumer received signal");
    }


    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread producerThread = new Thread(() -> {
            try {
                producerConsumer.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumerThread = new Thread(()->{
            try {
                producerConsumer.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}