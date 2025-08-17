package com.hostriders.java.thread.shared.resources.example001;

import java.util.HashMap;
import java.util.Map;

public class SharedMap {
    private Map<String, String> map = new HashMap<>();

    public synchronized void putToMap(String key, String value) {
        map.put(key, value);
    }

    public synchronized Map<String, String> getMap() {
        return map;
    }

    public static void main(String[] args) {
        SharedMap map = new SharedMap();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                map.putToMap("Key " + i, "Value " + i);
            }
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("Map size: " + map.getMap().size());
            for (Map.Entry<String, String> entry : map.getMap().entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
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