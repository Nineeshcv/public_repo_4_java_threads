package com.hostriders.java.thread.intervieCodes;

public class PrintEvenAndOddNumber {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for ( int i= 0; i < 10; i+=2) {
                    //EvenPrinter.printEven(i);
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for ( int i= 1; i < 10; i+=2) {
                   // OddPrinter.printOdd(i);
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
class OddPrinter{
     synchronized void printOdd(int num){
        System.out.println(num);
         try {
             this.wait();
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
     }
}
class EvenPrinter{

     synchronized void printEven(int num){
        System.out.println(num);
    }
}
