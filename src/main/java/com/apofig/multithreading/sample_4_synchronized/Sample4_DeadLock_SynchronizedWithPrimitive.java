package com.apofig.multithreading.sample_4_synchronized;

import static com.apofig.multithreading.ThreadUtils.print;

public class Sample4_DeadLock_SynchronizedWithPrimitive {

    //    private static String monitor = "12";
    private static Integer monitor = 12;
//    private static Boolean monitor = true;

    private static int count = 0; // shared state

    static class MyRunnable implements Runnable {
        public void run() {
            int c = 5;
            while (--c >= 0) {
                synchronized (monitor) {
                    print("зашли: " + count);

                    int y = count;

                    print("прочитали: " + y);

                    count = y + 1;

                    print("просуммировали: " + count);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable target = new MyRunnable();
        Thread thread1 = new Thread(target);
        Thread thread2 = new Thread(target);

        new Thread(new Runnable() { // захватываем монитор примитива
            @Override
            public void run() {
//                synchronized ("12") {
                synchronized (Integer.valueOf(12)) {
//                synchronized (Boolean.TRUE) {
                    try {
                        Thread.currentThread().join(); // и зависаем
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        print("итого: " + count);
    }

}
