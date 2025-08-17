package com.hostriders.java.thread.executor.exservice001;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolExecutorExample1 {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 5; i++) {
                service.execute(new Task1(i));
            }
        }
    }
}
