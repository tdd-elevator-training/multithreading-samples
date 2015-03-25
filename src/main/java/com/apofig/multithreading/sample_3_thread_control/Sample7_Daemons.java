package com.apofig.multithreading.sample_3_thread_control;

public class Sample7_Daemons {

    static class MyRunnable implements Runnable {

        private String message;
        private int count;

        public MyRunnable(String message, int count) {
            this.message = message;
            this.count = count;
        }

        @Override
        public void run() {
            while (--count > 0) {
                System.out.println(message);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Exit");
        }
    }

    ;

    public static void main(String[] args) throws InterruptedException {
        Thread daemon1 = new Thread(new MyRunnable("Daemon1", 100));
        Thread daemon2 = new Thread(new MyRunnable("Daemon2", 100));

        daemon1.setDaemon(true);
        daemon2.setDaemon(true);

        Thread thread1 = new Thread(new MyRunnable("Thread1", 3));
        Thread thread2 = new Thread(new MyRunnable("Thread2", 5));

        thread1.start();
        thread2.start();

        daemon1.start();
        daemon2.start();

        System.out.println("Exit main");
    }

}
