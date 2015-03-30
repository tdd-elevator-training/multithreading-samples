package com.apofig.multithreading.sample_2_thread_state;

import static com.apofig.multithreading.ThreadUtils.print;
import static com.apofig.multithreading.ThreadUtils.sleep;

public class Sample7_WaitingNotifyAll {

    static Object monitor = new Object();
    static boolean ready = false;

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (monitor) {
                    try {
                        while (!ready) {
                            print("Waiting... ");
                            monitor.wait();
                            print("Wakeup");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                print("Do my work");

                sleep(1000);
            }
        }
    }

    public static void main(String[] args) {
        Thread main1 = new Thread(new MyRunnable());
        Thread main2 = new Thread(new MyRunnable());
        Thread main3 = new Thread(new MyRunnable());

        Thread notifier = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (monitor) {
                        ready = true;
                        print("Ready = true. Try to notify all...");
                        monitor.notifyAll();
                        print("After notify ");
                    }

                    sleep(3000);
                }
            }
        });

        Thread releaser = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (monitor) {
                        ready = false;
                        print("Ready = false");
                    }

                    sleep(3000);
                }
            }
        });

        main1.start();
        main2.start();
        main3.start();
        notifier.start();
        releaser.start();
    }

}
