package com.hostriders.java.thread.more.deep;

public class SegmentLockingExample {
    private static final int NUM_SEGMENTS = 10;
    private static final int SEGMENT_SIZE = 10;
    private static final Object[] locks = new Object[NUM_SEGMENTS];

    static {
        for (int i = 0; i < NUM_SEGMENTS; i++) {
            locks[i] = new Object();
        }
    }

    public static void updateElement(int index, int value) {
        int segmentIndex = index / SEGMENT_SIZE;
        synchronized (locks[segmentIndex]) {
            // Update the element in the segment"
            System.out.println("test");
        }
    }
}
