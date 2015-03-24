package com.apofig.multithreading.sample_4_synchronized;

public class Sample2_DeadLock {

    public static void main(String[] args) throws InterruptedException {
        final Object object1 = new Object();
        final Object object2 = new Object();

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object1) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (object2) {
                        System.out.println("Done1!");
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object2) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (object1) {
                        System.out.println("Done2!");
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
