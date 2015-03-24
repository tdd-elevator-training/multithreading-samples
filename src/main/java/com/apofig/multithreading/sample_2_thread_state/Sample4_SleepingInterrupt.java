package com.apofig.multithreading.sample_2_thread_state;

import java.util.Calendar;

public class Sample4_SleepingInterrupt {

    public static void main(String[] args) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println();
                    System.out.println("Running<->Runnable");

                    System.out.println("Sleeping");
                    long time = now();
                    try {

                        Thread.sleep(10000);

                    } catch (InterruptedException e) {
                        System.out.println("Sleep interrupted: " + (now() - time) + "ms");
                    }
                    System.out.println("Running");
                }
            }
        });

        Thread interrupter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Try to interrupt..");

                    thread.interrupt();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
        interrupter.start();
    }

    private static long now() {
        return Calendar.getInstance().getTimeInMillis();
    }

}
