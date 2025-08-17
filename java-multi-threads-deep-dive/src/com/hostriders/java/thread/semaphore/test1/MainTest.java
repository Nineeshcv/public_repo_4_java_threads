package com.hostriders.java.thread.semaphore.test1;

import java.util.concurrent.Semaphore;

class CarParkingSlot {
    private boolean isAvailable;

    public CarParkingSlot() {
        this.isAvailable = true;
    }

    public synchronized void park() {
        while (!isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        isAvailable = false;
        notifyAll();
    }

    public synchronized void leave() {
        isAvailable = true;
        notifyAll();
    }

    public synchronized boolean isAvailable() {
        return isAvailable;
    }
}

 class Car {
    private String name;
    private CarParkingSlot parkingSlot;
    private Semaphore semaphore;

    public Car(String name, CarParkingSlot parkingSlot, Semaphore semaphore) {
        this.name = name;
        this.parkingSlot = parkingSlot;
        this.semaphore = semaphore;
    }

    public void park() {
        try {
            semaphore.acquire();
            if (parkingSlot.isAvailable()) {
                parkingSlot.park();
                System.out.println(name + " has parked");
            } else {
                System.out.println(name + " cannot park, parking slot is not available");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    public void leave() {
        try {
            semaphore.acquire();
            parkingSlot.leave();
            System.out.println(name + " has left");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }
}

public class MainTest {
    public static void main(String[] args) {
        CarParkingSlot parkingSlot = new CarParkingSlot();
        Semaphore semaphore = new Semaphore(1);

        Car car1 = new Car("Car 1", parkingSlot, semaphore);
        Car car2 = new Car("Car 2", parkingSlot, semaphore);
        Car car3 = new Car("Car 3", parkingSlot, semaphore);

        car1.park();
        car2.park();
        car3.park();

        car1.leave();
        car2.leave();
        car3.leave();
      /*  for (int i=0 ;i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Car car = new Car("Car " + finalI, parkingSlot, semaphore);
                    car.park();
                    car.leave();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }*/
    }
}