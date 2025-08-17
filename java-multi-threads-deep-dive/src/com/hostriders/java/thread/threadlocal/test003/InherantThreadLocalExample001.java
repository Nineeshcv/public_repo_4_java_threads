package com.hostriders.java.thread.threadlocal.test003;

public class InherantThreadLocalExample001 {

    public static void main(String[] args) {
        ThreadLocal<String> parentThreadLocal = new ThreadLocal<>();
        ThreadLocal<String> childThreadLocal = new InheritableThreadLocal<>();



        Thread parentThread = new Thread(() -> {
            parentThreadLocal.set("Parent Thread Local Value");
            childThreadLocal.set("Child Thread Local Value");

            System.out.println("From Parent: Parent Thread : " + parentThreadLocal.get());
            System.out.println("From Parent: Child Thread : " + childThreadLocal.get());
            Thread childThread = new Thread(() -> {
                System.out.println(" From Child : Child Thread : " + childThreadLocal.get());
                System.out.println(" From Child :Parent Thread : " + parentThreadLocal.get());
            });
            childThread.start();
            parentThreadLocal.remove();
            childThreadLocal.remove();

            System.out.println("After removing : From Parent: Parent Thread : " + parentThreadLocal.get());
            System.out.println("After removing : From Parent: Child Thread : " + childThreadLocal.get());
        });
        parentThread.start();
    }
}
