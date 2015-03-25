package com.apofig.multithreading.sample_2_thread_state;

public class Sample5_WaitingNotify {

    public static void main(String[] args) {
        final Object monitor = new Object();

        final Thread main = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Running<->Runnable");

                    System.out.println("Waiting");

                    synchronized (monitor) {
                        try {
                            monitor.wait();
                            System.out.println("After wait");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Running");
                }
            }
        });

        Thread alarmer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println();
                    System.out.println("Try to notify..");

                    synchronized (monitor) {
                        monitor.notify();
                        System.out.println("After notify");
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        main.start();
        alarmer.start();
    }

}
