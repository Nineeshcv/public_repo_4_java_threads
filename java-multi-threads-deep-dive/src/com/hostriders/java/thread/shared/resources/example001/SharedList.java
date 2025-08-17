package com.hostriders.java.thread.shared.resources.example001;

import java.util.ArrayList;
import java.util.List;

public class SharedList {
    private List<String> list = new ArrayList<>();

    public synchronized void addToList(String element) {
        list.add(element);
    }

    public synchronized List<String> getList() {
        return list;
    }

    public static void main(String[] args) {
        SharedList list = new SharedList();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.addToList("Element " + i);
            }
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("List size: " + list.getList().size());
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