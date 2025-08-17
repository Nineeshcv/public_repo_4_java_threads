package com.hostriders.java.thread.problems.synchronization.prodcon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ProducerConsumerExample1 {

    public static void main(String[] args) {

        Worker worker = new Worker(5, 0);
        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();

    }
}

class Worker {
    private int sequence = 0;
    private final Integer top;
    private final Integer bottom;
    private List<Integer> container;
    private final Object LOCK = new Object();

    public Worker(Integer top, Integer bottom) {
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        synchronized (LOCK) {
            while (true) {
                if (container.size() == top) {
                    System.out.println("Container is full, Waiting to item to be removed!!");
                    LOCK.wait();
                } else {
                    container.add(++sequence);
                    System.out.println("Item added to the container!! :::"+ sequence);
                    LOCK.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (LOCK) {
            while (true) {
                if (container.size() == bottom) {
                    System.out.println("Container is empty, Waiting to item to be added!!");
                    LOCK.wait();
                } else {
                    System.out.println("Item removed from the container!! ::"+ container.removeFirst());
                    LOCK.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}

