package com.hostriders.java.thread.countdownLatch2;
import java.sql.SQLOutput;
import java.util.concurrent.CountDownLatch;

public class WorkerRunnable implements Runnable{
    private final CountDownLatch countDown;
    private int i;

    public WorkerRunnable(CountDownLatch countDown, int i) {
        this.countDown = countDown;
        this.i = i;
    }

    @Override
    public void run() {
        doWork();
        countDown.countDown();
    }


    private void doWork(){
        System.out.println("Doing some dummy work!!!!");
    }
}
