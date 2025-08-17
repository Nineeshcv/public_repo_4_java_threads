package com.hostriders.java.thread.problems.synchronization.producerConsumer;

import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerBlockingQueueSimple {
    public static void main(String[] args) {

        WorkerClass worker = new WorkerClass();
        Thread producer = new Thread(()->{

            worker.produce();
        });
        Thread producer2 = new Thread(()->{

            worker.produce();
        });
        Thread consumer = new Thread(()-> worker.consume());

        producer.start();
        producer2.start();
        consumer.start();

    }
}
class WorkerClass{
    BlockingQueue<Integer> blockingQueue= new LinkedBlockingQueue<>();

    public void produce(){
        for (int i = 0; i <= 50; i += 10) {
            try {
                System.out.println(String.format("Producer produced data %s and added to queue, Thread name: %s", i, Thread.currentThread().getName()));
                blockingQueue.put(i);
            } catch (InterruptedException ex) {
                System.out.println("Producer thread is interrupted.");
            }
        }
    }
    public void produce2(){
        for (int i = 0; i <= 50; i += 10) {
            try {
                System.out.println(String.format("Producer 2 produced data %s and added to queue.", i));
                blockingQueue.put(i);
            } catch (InterruptedException ex) {
                System.out.println("Producer 2 thread is interrupted.");
            }
        }
    }

    public void consume(){
        while (true) {
            try {
                System.out.println(String.format("Consumer consumed data %s from queue.", blockingQueue.take()));
            } catch (InterruptedException ex) {
                System.out.println("Consumer thread is interrupted.");
            }
        }
    }
}
