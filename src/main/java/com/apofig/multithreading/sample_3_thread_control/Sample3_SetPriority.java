package com.apofig.multithreading.sample_3_thread_control;

import static com.apofig.multithreading.ThreadUtils.print;
import static com.apofig.multithreading.ThreadUtils.someLogic;

public class Sample3_SetPriority {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    print("Я круче!");

                    someLogic(1000000);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    print("Нет я!");

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
