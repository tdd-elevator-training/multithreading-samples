package com.apofig.multith.join;

public class ThreadGrouping {

    public static void main(String[] args) {

        ThreadGrouping group = new ThreadGrouping("name");

        Thread thread1 = new Thread(group, new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 print 1");
                someLogic();
                System.out.println("thread1 print 2");
                someLogic();
                System.out.println("thread1 print 3");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 print 1");
                someLogic();
                System.out.println("thread2 print 1");
                someLogic();
                System.out.println("thread3 print 1");
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void someLogic() {
        double a = 2.0;
        for (int i = 0; i < 5000000; i++) {
            a = Math.sin(a) + a;
        }
    }
}
