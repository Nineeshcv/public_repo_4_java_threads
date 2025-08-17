package com.hostriders.java.thread.semaphore;

import java.util.concurrent.Semaphore;

public class CarParkingSlot {
    private static final int MAX_SLOTS = 5; // maximum number of parking slots
    private static final Semaphore semaphore = new Semaphore(MAX_SLOTS); // semaphore to manage parking slots

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            // create 10 cars
            new Thread(() -> {
                try {
                    System.out.println("Car " + Thread.currentThread().getName() + " is looking for a parking slot");
                    semaphore.acquire(); // acquire a parking slot
                    System.out.println("Car " + Thread.currentThread().getName() + " has found a parking slot");
                    Thread.sleep(1000); // simulate parking time
                    System.out.println("Car " + Thread.currentThread().getName() + " is leaving the parking slot");
                    semaphore.release(); // release the parking slot
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}