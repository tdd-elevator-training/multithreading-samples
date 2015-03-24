package com.apofig.multithreading.sample_2_thread_state;

public class Sample2_RunnableRunning {

    private static void someLogic(int b) {
        double a = 13.0;
        for (int i = 0; i < b; i++) {
            a = Math.cos(Math.sqrt(a + Math.sin(a)));
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.print("+");
                    someLogic(1000000);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("");
                    someLogic(10000000);
                }
            }
        });

        thread1.start();
        thread2.start();
    }

}
