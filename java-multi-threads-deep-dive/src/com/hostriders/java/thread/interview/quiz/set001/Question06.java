package com.hostriders.java.thread.interview.quiz.set001;

public class Question06 {
    public static void main(String [] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello from " + Thread.currentThread().getName());
            }
        });
        thread.setName("Background thread");
        thread.start();

        Thread.sleep(1000);
        System.out.println("Hello from " + Thread.currentThread().getName());
    }
}
