package com.apofig.multithreading.sample_2_thread_state;

import static com.apofig.multithreading.ThreadUtils.print;

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

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                        print("ready = true");
                        print("Try to notify all...");
                        monitor.notifyAll();
                        print("After notify ");
                    }

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread releaser = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (monitor) {
                        ready = false;
                        print("ready = false");
                    }

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
