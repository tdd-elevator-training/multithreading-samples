package com.apofig.multithreading.sample_4_synchronized;

import static com.apofig.multithreading.ThreadUtils.print;
import static com.apofig.multithreading.ThreadUtils.sleep;

public class Sample2_DeadLock {

    public static void main(String[] args) throws InterruptedException {
        final Object object1 = new Object();
        final Object object2 = new Object();

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object1) {
                    sleep(100);

                    synchronized (object2) {
                        print("Done1!");
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object2) {
                    sleep(100);

                    synchronized (object1) {
                        print("Done2!");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

}
