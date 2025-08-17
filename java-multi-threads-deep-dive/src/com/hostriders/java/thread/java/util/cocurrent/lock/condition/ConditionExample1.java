package com.hostriders.java.thread.java.util.cocurrent.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample1 {
    public static void main(String[] args) {
        ProducerConsuemr1 producerConsuemr = new ProducerConsuemr1();
        Thread producer = new Thread(producerConsuemr::producer);
        Thread consumer = new Thread(producerConsuemr::consumer);
        producer.start();
        consumer.start();
    }
}

class ProducerConsuemr1{
    private final Lock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();
    private boolean isReady = false;

    public void producer(){
        lock.lock();
        System.out.println("Producer: wating for condition to be ready");
        try{
            while(! isReady){
                condition.await();
            }
            System.out.println("Producer: condition is ready, Proceeding");
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }

    }
    public void consumer(){
        lock.lock();
        try {
            System.out.println("Consumer: setting condition to ready");
            isReady = true;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
