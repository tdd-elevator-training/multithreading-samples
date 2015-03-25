package com.apofig.multithreading.sample_1_run_thread;

import java.util.Random;
import java.util.concurrent.*;

// thanks http://blog.teamlazerbeez.com/2009/04/29/java-completionservice/
public class Sample6_CompletionService {

    static class MyCallable implements Callable {

        @Override
        public Object call() {
            long id = Thread.currentThread().getId();
            int count = new Random().nextInt(10);
            while (--count > 0) {
                System.out.println(id + ": Я тут!");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return id;
        }
    }

    public static void main(String[] args) throws Exception {
        Callable task1 = new MyCallable();
        Callable task2 = new MyCallable();

        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService completion = new ExecutorCompletionService(executor);

        completion.submit(task1);
        completion.submit(task2);

        int remainingFutures = 2;
        while (remainingFutures > 0) {
            Future completed = completion.take();

            try {
                System.out.println("Finished: " + completed.get());
                remainingFutures--;
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

}
