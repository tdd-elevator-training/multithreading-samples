package com.apofig.multithreading.sample_1_run_thread;

import static com.apofig.multithreading.ThreadUtils.print;

public class Sample0_MainMethod {

    public static void main(String[] args) throws InterruptedException {
        print("Начали!");

        Thread.currentThread().join();

        print("закончили!");
    }

}
