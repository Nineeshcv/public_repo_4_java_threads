package com.hostriders.java.thread.executor.exservice001;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolExecutorExample1 {
    public static void main(String[] args) {
        try(ScheduledExecutorService service = Executors.newScheduledThreadPool(2)) {
            service.scheduleAtFixedRate(new Task1(0), 1000, 2000, TimeUnit.MILLISECONDS);

            try {
                if (service.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
                    service.shutdownNow();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
