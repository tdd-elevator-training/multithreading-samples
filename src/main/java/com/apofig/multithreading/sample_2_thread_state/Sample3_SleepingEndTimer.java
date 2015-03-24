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
                        long time = Calendar.getInstance().getTimeInMillis();

                        Thread.sleep(1000);

                        System.out.println("After sleep: " + (Calendar.getInstance().getTimeInMillis() - time) + "ms");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();

        thread.interrupt();
    }

}
