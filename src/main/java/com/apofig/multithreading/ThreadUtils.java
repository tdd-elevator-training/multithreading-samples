package com.apofig.multithreading;

public class ThreadUtils {

    public static void print(String message) {
        long id = Thread.currentThread().getId();
        System.out.println(id + ": " + message);
    }
}
