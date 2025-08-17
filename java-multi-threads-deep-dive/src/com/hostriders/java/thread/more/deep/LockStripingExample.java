package com.hostriders.java.thread.more.deep;

public class LockStripingExample {
    private static final int NUM_STRIPES = 10;
    private static final int STRIPE_SIZE = 100;
    private static final Object[] locks = new Object[NUM_STRIPES];

    static {
        for (int i = 0; i < NUM_STRIPES; i++) {
            locks[i] = new Object();
        }
    }

    public static void updateEntry(int key, int value) {
        int stripeIndex = key % NUM_STRIPES;
        synchronized (locks[stripeIndex]) {
            // Update the entry in the stripe
        }
    }
}
