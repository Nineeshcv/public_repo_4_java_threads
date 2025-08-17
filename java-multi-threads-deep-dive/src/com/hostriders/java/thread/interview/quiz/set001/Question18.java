package com.hostriders.java.thread.interview.quiz.set001;

public class Question18 {
    public static void main(String [] args) throws InterruptedException {
        Thread thread = new Thread(() -> System.out.println("Hello from " +
                Thread.currentThread().getName()));
        thread.setName("New Thread");
        thread.run();

        Thread.sleep(2000);
    }
}
