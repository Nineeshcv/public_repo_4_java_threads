package com.hostriders.java.thread.threadlocal.test002;

public class ThreadLocalWithUserTest001 {

    public static void main(String[] args) {
        ThreadLocal<User> userThreadLocal = new ThreadLocal<>(){
            @Override
            protected User initialValue(){
              return new User();
            }
        };

        Thread userThread1 = new Thread(()-> {
            User user = userThreadLocal.get();
            user.setName("User 1");
            user.setUserId("UID-1");
            System.out.println("Thread Local : " + Thread.currentThread().getName() +
                    ": " + user.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            userThreadLocal.remove(); // removing the user-1
        });

        Thread userThread2 = new Thread(()-> {
            User user = userThreadLocal.get();
            user.setName("User 2");
            user.setUserId("UID-2");
            System.out.println("Thread Local : " + Thread.currentThread().getName() +
                    ": " + user.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            userThreadLocal.remove(); // removing the user-2
        });

        userThread1.setName("User-1");
        userThread2.setName("User-2");
        userThread1.start();
        userThread2.start();

    }
}
