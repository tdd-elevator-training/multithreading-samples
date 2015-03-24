package com.apofig.multithreading.sample_1_run_thread;

public class Sample0_MainMethod {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Начали!");

        Thread.currentThread().join();

        System.out.println("закончили!");
    }

}
