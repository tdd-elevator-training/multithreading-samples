package com.apofig.multithreading.sample_2_thread_state;

import java.util.Calendar;

public class Sample3_SleepingEndTimer {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Running<->Runnable");

                    try {
                        System.out.println("Before sleep");
                        long time = now();

                        Thread.sleep(1000);

                        System.out.println("After sleep: " + (now() - time) + "ms");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();

        thread.interrupt();
    }

    private static long now() {
        return Calendar.getInstance().getTimeInMillis();
    }

}
