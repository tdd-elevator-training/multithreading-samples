package com.apofig.multithreading.sample_4_synchronized;

import static com.apofig.multithreading.ThreadUtils.print;

public class Sample1_DataRaceVolatile {

    private static volatile int number = 0; // shared state

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    number++;
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (number % 2 == 0) {
                        print(String.valueOf(number));
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }

}
