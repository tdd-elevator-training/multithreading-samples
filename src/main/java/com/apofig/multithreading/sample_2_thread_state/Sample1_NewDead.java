package com.apofig.multithreading.sample_2_thread_state;

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
                    System.out.println("Running<->Runnable");


                    someLogic(1000000);
                }
                System.out.println("Almost Dead");
            }
        });

        System.out.println("New");

        thread.start();

        System.out.println("Runnable");

        thread.join();

        System.out.println("Dead");
    }

}
