package com.apofig.multithreading.sample_2_thread_state;

import static com.apofig.multithreading.ThreadUtils.print;
import static com.apofig.multithreading.ThreadUtils.someLogic;

public class Sample1_NewDead {

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
