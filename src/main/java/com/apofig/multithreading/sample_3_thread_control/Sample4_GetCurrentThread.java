package com.apofig.multithreading.sample_3_thread_control;

public class Sample4_GetCurrentThread {

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());

        Thread main = Thread.currentThread();
        System.out.println(main.getName());
        thread1.start();
        thread2.start();
    }

}
