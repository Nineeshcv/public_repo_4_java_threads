package com.hostriders.java.thread.exception.set01;

public class SetExceptionHandlerInThread {
    public static void main(String[] args) throws InterruptedException {

        //try{
            Thread thread = new Thread(()->{
                try {
                    int i = 10 / 0;
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    try {
                        throw new InterruptedException("Interrupted"); // Who is going to handle this exception
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (ArithmeticException e) {
                    System.out.println("Exception handled - in Thread"+
                            Thread.currentThread().getName());
                    throw new RuntimeException(e);
                }
                System.out.println("hello");
            });
           thread.start();
           thread.setUncaughtExceptionHandler((thread1,Exception)->{
               System.out.println("Exception Handleed for thread "+thread1.getName());
               System.out.println("Exception is "+Exception.getMessage());
           });
        /*}catch (RuntimeException e){
            System.out.println("Exception handled ---> main");
        }*/

        /*try {
            test1();
        }catch (RuntimeException e){
            System.out.println("Exception handled ---> main");
        }*/
        Thread.sleep(1000);
        System.out.println(" This is  hello world -- Completed");
    }

    public static void test1(){
        try{
            int i = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Exception handled");
            throw new RuntimeException(e);
        }
    }
}
