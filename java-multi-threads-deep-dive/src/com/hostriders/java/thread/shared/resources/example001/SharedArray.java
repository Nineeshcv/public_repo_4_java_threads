package com.hostriders.java.thread.shared.resources.example001;

public class SharedArray {
    private int[] array = new int[10];

    public synchronized void setArray(int index, int value) {
        array[index] = value;
    }

    public synchronized int getArray(int index) {
        return array[index];
    }

    public static void main(String[] args) {
        SharedArray array = new SharedArray();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                array.setArray(i, i * 2);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Array value at index " + i + ": " + array.getArray(i));
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}