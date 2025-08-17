package com.hostriders.java.thread.executor.exservice001;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class NewChachedThreadPoolExecutorExample1 {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 10; i++) {
                service.execute(new Task1(i));
            }

        }
    }
}
