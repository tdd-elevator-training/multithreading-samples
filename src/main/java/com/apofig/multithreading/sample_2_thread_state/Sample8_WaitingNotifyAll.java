package com.apofig.multithreading.sample_2_thread_state;

public class Sample8_WaitingNotifyAll {

    static Object monitor = new Object();

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            while (true) {
                System.out.println(id + ": Running<->Runnable");

                System.out.println(id + ": Waiting");

                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(id + ": Running");
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
                    System.out.println("Try to notify all...");

                    synchronized (monitor) {
                        monitor.notifyAll();
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
