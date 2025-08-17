package com.hostriders.java.thread.rerturnresult;

public class ThreadReturnResult02 {
    public static void main(String[] args) throws InterruptedException{
        MyThread myThread = new MyThread(10,20);
        Thread thread = new Thread(myThread);
        thread.start();
       /* try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("Result is "+myThread.getResult());
    }
    static  class MyThread extends Thread{
        private int firstNumber;
        private int secondNumber;
        private int result;

        private volatile boolean flag = false;

        public MyThread(int firstNumber, int secondNumber) {
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
        }
        public void run() {
            System.out.println("----"+Thread.currentThread().getName());
            result = firstNumber + secondNumber;

            flag = true;
            synchronized (this){
                this.notify();
            }
        }
        public int getResult() throws InterruptedException {
            synchronized (this){
                if(!flag){
                    System.out.println("----Waiting"+Thread.currentThread().getName());
                }
                this.wait();
            }
            return result;
        }
    }
}
