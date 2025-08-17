package com.hostriders.java.thread.interview.quiz.set001;

public class Question27 {
    public static void main(String [] args) throws InterruptedException {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + " is doing work"));

        thread.setName("thread1");

        thread.start();
        Thread.sleep(5000);

        System.out.println(thread.getName() + " is now in the " + thread.getState() + " state");
    }
}
