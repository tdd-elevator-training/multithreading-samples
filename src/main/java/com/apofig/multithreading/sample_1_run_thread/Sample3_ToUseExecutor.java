package com.apofig.multithreading.sample_1_run_thread;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Sample3_ToUseExecutor {

    static class IAmTheBest implements Runnable {

        private String message;

        public IAmTheBest(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            while (true) {
                System.out.println(id + ": " + message);

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
        Runnable task3 = new IAmTheBest("А вот и фиг!");

//        Executor executor = Executors.newCachedThreadPool();  // столько сколько надо с последующим reuse
//        Executor executor = Executors.newSingleThreadExecutor(); // только один Thread для всех тасков
        Executor executor = Executors.newFixedThreadPool(2); // заданное число Thread, все что не помещается в очередь
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);

        System.out.println("Пока!!");
    }

}
