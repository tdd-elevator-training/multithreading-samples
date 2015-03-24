package com.apofig.multithreading.sample_3_thread_control;

public class Sample1_StartOrRun {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 10;
                while (--count > 0) {
                    System.out.println("Thread1");

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 10;
                while (--count > 0) {
                    System.out.println("Thread2");

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        System.out.println("Running 1");

//        thread1.run();
        thread1.start();

        System.out.println("Running 2");

//        thread2.run();
        thread2.start();

        System.out.println("Finish");
    }

}
