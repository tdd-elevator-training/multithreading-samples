package com.apofig.multithreading.sample_4_synchronized;

public class Sample1_DataRace {

    //    private static Object monitor = new Object();
    private static int count = 0; // shared state

    static class MyRunnable implements Runnable {
        public void run() {
//            synchronized (monitor) {
            print("зашли: " + count);

            int y = count;

            print("прочитали: " + y);

            count = y + 1;

            print("просуммировали: " + count);
//            }
        }
    }

    private static void print(String message) {
        System.out.println(Thread.currentThread().getId() + ": " + message);
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable target = new MyRunnable();
        Thread thread1 = new Thread(target);
        Thread thread2 = new Thread(target);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        print("итого: " + count);
    }

}
