package com.hostriders.java.thread.rerturnresult;

public class ThreadReturnResult01 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread(10,20);
        Thread thread = new Thread(myThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Result is "+myThread.getResult());
    }

    static  class MyThread implements  Runnable{
        private int firstNumber;
        private int secondNumber;
        private int result;

        public MyThread(int firstNumber, int secondNumber) {
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
        }
        @Override
        public void run() {
            System.out.println("----"+Thread.currentThread().getName());
            result = firstNumber + secondNumber;
        }

        public int getResult() {
            return result;
        }
    }
}
