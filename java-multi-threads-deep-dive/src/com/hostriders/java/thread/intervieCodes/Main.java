package com.hostriders.java.thread.intervieCodes;

class Printer {
    private int number = 1;
    private final Object lock = new Object();

    public void printOdd() {
        synchronized (lock) {
            while (number <= 10) {
                if (number % 2 != 0) {
                    System.out.println("Odd Thread: " + number);
                    number++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            while (number <= 10) {
                if (number % 2 == 0) {
                    System.out.println("Even Thread: " + number);
                    number++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread oddThread = new Thread(() -> printer.printOdd());
        Thread evenThread = new Thread(() -> printer.printEven());

        oddThread.start();
        evenThread.start();
    }
}