package com.apofig.multithreading.sample_3_thread_control;

import java.util.LinkedList;
import java.util.List;

public class Sample2_StopThread {

    static List<String> data = new LinkedList<>();

    // TODO закончить

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 20;
                while (--count > 0) {
                    data.add("s");
                    data.add("t");
                    data.add("r");
                    data.add("i");
                    data.add("n");
                    data.add("g");

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        System.out.println("Running 1");

        thread.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.stop();

        System.out.println(data.toString());

        System.out.println("Finish");
    }

}
