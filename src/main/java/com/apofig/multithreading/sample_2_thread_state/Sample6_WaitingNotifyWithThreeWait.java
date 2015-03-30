package com.apofig.multithreading.sample_2_thread_state;

import static com.apofig.multithreading.ThreadUtils.print;
import static com.apofig.multithreading.ThreadUtils.sleep;

public class Sample6_WaitingNotifyWithThreeWait {

    static Object monitor = new Object();
    static boolean ready = false;

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {

                synchronized (monitor) {
                    try {
                        while (!ready) {
                            monitor.wait();
                            print("Wakeup");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ready = false;
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
                        print("Notify...");
                        ready = true;
                        monitor.notify();
                    }

                    sleep(1000);
                }
            }
        });

        main1.start();
        main2.start();
        main3.start();
        notifier.start();
    }

}
