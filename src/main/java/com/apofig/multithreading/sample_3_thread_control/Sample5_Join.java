package com.apofig.multithreading.sample_3_thread_control;

import static com.apofig.multithreading.ThreadUtils.print;

public class Sample5_Join {

    static class MyRunnable implements Runnable {

        private int count;

        public MyRunnable(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            while (--count > 0) {
                print(String.valueOf(count));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread2 = new Thread(new MyRunnable(20));
        Thread thread1 = new Thread(new MyRunnable(10));
        thread1.setDaemon(true);

        thread1.start();
        thread2.start();

        thread2.join();
        print("Thread 2 finished");

        thread1.join();
        print("Thread 1 finished");
    }

}
