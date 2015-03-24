package com.apofig.multithreading.sample_3_thread_control;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sample3_SetPriority {

    private static void someLogic(int b) {
        double a = 13.0;
        for (int i = 0; i < b; i++) {
            a = Math.cos(Math.sqrt(a + Math.sin(a)));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Я круче!");

                    someLogic(1000000);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Нет я!");

                    someLogic(1000000);
                }
            }
        });

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
    }

}
