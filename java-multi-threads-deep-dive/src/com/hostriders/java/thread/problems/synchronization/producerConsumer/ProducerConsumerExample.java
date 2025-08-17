package com.hostriders.java.thread.problems.synchronization.producerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerExample {

    public static void main(String[] args) {
        // shared queue created using LinkedBlockingQueue
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>();
        // Producer thread creation
        Thread producer = new Thread(new Producer(blockingQueue));
        // Consumer thread creation
        Thread consumer = new Thread(new Consumer(blockingQueue));

        // Start Producer and Consumer thread
        producer.start();
        consumer.start();
    }
}

class Consumer implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        while (true) {
            try {
                System.out.println(String.format("Consumer consumed data %s from queue.", blockingQueue.take()));
            } catch (InterruptedException ex) {
                System.out.println("Consumer thread is interrupted.");
            }
        }
    }
}

class Producer implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        for (int i = 0; ; i += 10) {
            try {
                System.out.println(String.format("Producer produced data %s and added to queue.", i));
                blockingQueue.put(i);
            } catch (InterruptedException ex) {
                System.out.println("Producer thread is interrupted.");
            }
        }
    }
}