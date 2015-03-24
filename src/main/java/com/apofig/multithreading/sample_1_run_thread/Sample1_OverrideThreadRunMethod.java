package com.apofig.multithreading.sample_1_run_thread;

import java.util.Random;

public class Sample1_OverrideThreadRunMethod {

    static class MyThread extends Thread {

        private String message;

        public MyThread(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(message);

                try {
                    Thread.sleep(new Random().nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static void main(String[] args) {
        Thread thread1 = new MyThread("Я круче!");
        Thread thread2 = new MyThread("Нет Я!");

        thread1.start();
        thread2.start();
    }

}
