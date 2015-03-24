package com.apofig.multithreading.sample_3_thread_control;

public class Sample5_Join {

    static class MyRunnable implements Runnable {

        private int count;

        public MyRunnable(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            while (--count > 0) {
                System.out.println(Thread.currentThread().getName() + ": " + count);
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
        System.out.println(Thread.currentThread().getName() + ": Thread 2 finished");

        thread1.join();
        System.out.println(Thread.currentThread().getName() + ": Thread 1 finished");
    }

}
