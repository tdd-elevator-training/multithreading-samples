package com.apofig.multithreading.sample_4_synchronized;

import static com.apofig.multithreading.ThreadUtils.print;

// thanks http://samolisov.blogspot.com/2011/04/threadlocal.html
public class Sample3_ThreadLocal {

    private static ThreadLocal<String> shared = new ThreadLocal<String>();

    static class MyRunnable implements Runnable {

        private String string;

        public MyRunnable(String string) {
            this.string = string;
        }

        public void run() {
            while (true) {
                if (shared.get() == null) {
                    shared.set(string);
                    string = null;
                }

                print(shared.get());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyRunnable("Первый!"));
        Thread thread2 = new Thread(new MyRunnable("Второй!"));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

}
