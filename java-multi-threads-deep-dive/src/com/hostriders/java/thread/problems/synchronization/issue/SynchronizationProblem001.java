package com.hostriders.java.thread.problems.synchronization.issue;

public class SynchronizationProblem001 {

    private static int counter1 = 0;
    private  static int counter2 = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(()->{
            for (int i =0; i < 1000; i++){
                increment1();
            }

        });
        Thread two = new Thread(()->{
            for (int i =0; i < 1000; i++){
                increment2();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        long startTime = System.currentTimeMillis();
        one.start();
        two.start();

        one.join();
        two.join();
        long endTime = System.currentTimeMillis();
        System.out.println("Toal Time Taken: "+ (endTime-startTime));
        System.out.println(counter1 +"::::"+counter2);
        System.out.println("Completed!!!");

    }

    public synchronized static void increment1(){
        counter1++;
        System.out.println("<><>::"+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
       System.out.println("<><>::"+Thread.currentThread().getName() +"::::"+"Slept for 1000ms");
    }

    public synchronized static void increment2(){
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
