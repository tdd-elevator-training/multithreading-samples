package com.apofig.multithreading.sample_1_run_thread;

import java.util.Random;

import static com.apofig.multithreading.ThreadUtils.print;

public class Sample2_ImplementsRunnable {

    static class IAmTheBest implements Runnable {

        private String message;

        public IAmTheBest(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            while (true) {
                print(message);

                try {
                    Thread.sleep(new Random().nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable task1 = new IAmTheBest("Я круче!");
        Runnable task2 = new IAmTheBest("Нет Я!");

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
