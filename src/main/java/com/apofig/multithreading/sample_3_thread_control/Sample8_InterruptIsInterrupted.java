package com.apofig.multithreading.sample_3_thread_control;

public class Sample8_InterruptIsInterrupted {

    static class MyRunnable implements Runnable {

        private String message;

        public MyRunnable(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                System.out.println(message);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Exit");
        }
    }

    ;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyRunnable("Thread1"));
        Thread thread2 = new Thread(new MyRunnable("Thread2"));

        thread1.start();
        thread2.start();

        Thread.sleep(3000);

        thread1.interrupt();
        thread2.interrupt();

        System.out.println("Exit main");
    }

}
