package com.hostriders.java.thread.callable;

import java.util.ArrayList;
import java.util.List;

public class PrintOddEvenList {
    public static void main(String[] args) {

        List<List<Integer>> listoflist = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);

        listoflist.add(new ArrayList(list)); /// Defensive copy

        list.add(3);

        List<List<Integer>> listoflist2 = new ArrayList<>();
        listoflist2.add(list); // reference
        list.add(5);




        System.out.println(listoflist);
        System.out.println();
        System.out.println(listoflist2);






        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

        Thread t1 = new Thread(() ->
                System.out.println("Odd numbers: " + numbers.stream()
                        .filter(i -> i % 2 != 0).toList()));
        Thread t2 = new Thread(() ->
                System.out.println("Even numbers: " + numbers.stream()
                        .filter(i -> i % 2 == 0).toList()));
        t1.start();
        t2.start();

    }
}
