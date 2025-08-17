package com.hostriders.java.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserExample1 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " starting phase 1");
                phaser.arriveAndAwaitAdvance(); // Wait for all threads to reach this point

                System.out.println(Thread.currentThread().getName() + " starting phase 2");
                phaser.arriveAndAwaitAdvance(); // Wait for all threads to reach this point

                System.out.println(Thread.currentThread().getName() + " finished");
            }).start();
        }
    }
}