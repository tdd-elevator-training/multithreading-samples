package com.apofig.multithreading.sample_2_thread_state;

import static com.apofig.multithreading.ThreadUtils.print;
import static com.apofig.multithreading.ThreadUtils.sleep;

/**
 * Показать как ждет один другого
 * Заремерить while(true) в обоих потоках и показать, что в части случаев один поток зависает в ожидании уже готовых данных
 * Исправить ошибку добавлением if (!ready)
 * Сообщить о Spurious wakeups и взять в while (!ready)
 */
public class Sample5_WaitingNotify {
    static Object monitor = new Object();
//    static boolean ready = false;

    public static void main(String[] args) {

        final Thread main = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (monitor) {
                        try {
                            print("Waiting...");
//                            if (!ready) {
                            monitor.wait();
//                            }
                            print("Wakeup");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    print("----------Running----------");
                }
            }
        });

        Thread notifier = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    print("Try to notify...");

                    synchronized (monitor) {
//                        ready = true;
                        monitor.notify();
                        print("After notify");
                    }

                    sleep(1000);
                }
            }
        });

        main.start();
        notifier.start();
    }

}
