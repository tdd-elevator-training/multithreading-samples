package com.apofig.multithreading;

import java.util.Random;

public class ThreadUtils {

    public static void print(String message) {
        long id = Thread.currentThread().getId();
        System.out.println(id + ": " + message);
    }

    public static void sleepRandom(int mills) {
        sleep(new Random().nextInt(mills));
    }

    public static void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void someLogic(int b) {
        double a = 13.0;
        for (int i = 0; i < b; i++) {
            a = Math.cos(Math.sqrt(a + Math.sin(a)));
        }
    }
}
