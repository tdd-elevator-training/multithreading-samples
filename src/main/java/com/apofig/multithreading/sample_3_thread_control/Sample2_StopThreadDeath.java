package com.apofig.multithreading.sample_3_thread_control;

public class Sample2_StopThreadDeath {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        };
        thread.start();

        Thread.sleep(1000);
        thread.stop();
    }

}
