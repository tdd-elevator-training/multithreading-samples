package com.apofig.multithreading.sample_3_thread_control;

import static com.apofig.multithreading.ThreadUtils.print;

public class Sample1_StartOrRun {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 10;
                while (--count > 0) {
                    print("Thread1");

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
                    print("Thread2");

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        print("Running 1");

//        thread1.run();
        thread1.start();

        print("Running 2");

//        thread2.run();
        thread2.start();

        print("Finish");
    }

}
