package com.apofig.multithreading.sample_1_run_thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.apofig.multithreading.ThreadUtils.print;
import static com.apofig.multithreading.ThreadUtils.sleepRandom;

public class Sample4_ToUseExecutorWithCallable {

    static class IAmTheBest implements Callable {

        private int count;
        private String message;

        public IAmTheBest(String message) {
            this.count = new Random().nextInt(20);
            this.message = message;
        }

        @Override
        public Object call() throws Exception {
            for (int i = 0; i < count; i++) {
                print(message);

               sleepRandom(2000);
            }
            return count;
        }
    }

    public static void main(String[] args) throws Exception {
        Callable task1 = new IAmTheBest("Я круче!");
        Callable task2 = new IAmTheBest("Нет Я!");

        ExecutorService executor = Executors.newCachedThreadPool();  // столько сколько надо с последующим reuse
        Future future1 = executor.submit(task1);
        Future future2 = executor.submit(task2);

        print("Я круче! vs Нет Я!\n" +
                future1.get() + ":" + future2.get());

        executor.shutdown();
    }

}
