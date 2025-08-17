package com.hostriders.java.thread.scopedValue;

import java.lang.ScopedValue;

public class ScopedValueExample {
    public static void main(String[] args) {
        ScopedValue<String> scopedValue = ScopedValue.newInstance();

        ScopedValue.Carrier carrier1 = ScopedValue.where(scopedValue, "thread scope value");

        carrier1.run(()->{
            System.out.println("Thread scope: " + scopedValue.get()); // prints "thread scope value"
        });

        System.out.println("Main scope after thread: " + scopedValue.get()); // Throws the NoSuchElementException
    }


}