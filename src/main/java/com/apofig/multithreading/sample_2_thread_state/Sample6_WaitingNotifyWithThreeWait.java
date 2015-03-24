package com.apofig.multithreading.sample_2_thread_state;

public class Sample6_WaitingNotifyWithThreeWait {

    static Object monitor = new Object();

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getId() + ": Running<->Runnable");

                System.out.println(Thread.currentThread().getId() + ": Waiting");

                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getId() + ": Running");
            }
        }
    }

    public static void main(String[] args) {

        Thread main1 = new Thread(new MyRunnable());
        Thread main2 = new Thread(new MyRunnable());
        Thread main3 = new Thread(new MyRunnable());

        Thread interrupter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println();
                    System.out.println("Try to notify..");

                    synchronized (monitor) {
                        monitor.notify();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        main1.start();
        main2.start();
        main3.start();
        interrupter.start();
    }

}
