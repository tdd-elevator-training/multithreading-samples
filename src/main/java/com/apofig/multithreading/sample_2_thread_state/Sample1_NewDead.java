package com.apofig.multithreading.sample_2_thread_state;

import static com.apofig.multithreading.ThreadUtils.print;

public class Sample1_NewDead {

    private static void someLogic(int b) {
        double a = 13.0;
        for (int i = 0; i < b; i++) {
            a = Math.cos(Math.sqrt(a + Math.sin(a)));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 10;
                while (--count > 0) {
                    print("Running<->Runnable");


                    someLogic(1000000);
                }
                print("Almost Dead");
            }
        });

        print("New");

        thread.start();

        print("Runnable");

        thread.join();

        print("Dead");
    }

}
