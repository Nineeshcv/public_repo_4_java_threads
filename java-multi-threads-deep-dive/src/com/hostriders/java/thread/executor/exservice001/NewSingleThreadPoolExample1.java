package com.hostriders.java.thread.executor.exservice001;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewSingleThreadPoolExample1 {
    public static void main(String[] args) {
        //Executor executor = Executors.newFixedThreadPool(4);
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            for (int i=0; i< 5; i++) {
                service.execute(new Task1(i));
                //service.execute(new Task1(i));
            }
        }

    }
}
class Task1 implements Runnable{

    private final int taskId;
    public Task1(int id) {
        this.taskId = id;
    }
    @Override
    public void run() {
        System.out.println("Task with ID: " + taskId + "  executed by:: " + Thread.currentThread().getName());
    }
}
