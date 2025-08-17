package com.hostriders.java.thread.problems.synchronization.fix;

public class SynchronizationProblem001Fix {
    private  static int counter1 = 0;
    private  static  int counter2 =0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(() ->{
            for (int i = 0; i< 1000; i++){
                try {
                    increment1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread two = new Thread(() ->{
            for (int i = 0; i< 1000; i++){
                increment2();
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println(counter1 +":::::"+ counter2);
        System.out.println("Completed Main Thread!!!");

    }

    private static void increment1() throws InterruptedException {
        synchronized (lock1){
            counter1++;
            System.out.println("<><>::"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("<><>::"+Thread.currentThread().getName() +"::::"+"Slept for 1000ms");
        }


    }

    private static  void increment2(){
        synchronized (lock2){
            counter2++;
            System.out.println("()()::"+Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("()()::"+Thread.currentThread().getName() +"::::"+"Slept for 2000ms");
        }

    }
}
