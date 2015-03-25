package com.apofig.multithreading.sample_4_synchronized;

// thanks https://richardbarabe.wordpress.com/2014/02/21/java-deadlock-livelock-and-lock-starvation-examples/
public class Sample2_DeadLock_BankAccount {

    public static class BankAccount {
        double balance;
        int id;

        public BankAccount(int id, double balance) {
            this.id = id;
            this.balance = balance;
        }

        public void withdraw(double amount) {
            // Wait to simulate io like database access ...
            try {
                Thread.sleep(10l);
            } catch (InterruptedException e) {
            }
            balance -= amount;
        }

        public void deposit(double amount) {
            // Wait to simulate io like database access ...
            try {
                Thread.sleep(10l);
            } catch (InterruptedException e) {
            }
            balance += amount;
        }

        public static void transfer(BankAccount from, BankAccount to, double amount) {
            synchronized (from) {
                from.withdraw(amount);
                synchronized (to) {
                    to.deposit(amount);
                }
            }
        }
    }

    public static void main(String[] args) {
        final BankAccount fooAccount = new BankAccount(1, 100d);
        final BankAccount barAccount = new BankAccount(2, 100d);

        new Thread() {
            public void run() {
                BankAccount.transfer(fooAccount, barAccount, 10d);
                System.out.printf("Foo: %s, Bar: %s\n",
                        fooAccount.balance, barAccount.balance);
            }
        }.start();

        new Thread() {
            public void run() {
                BankAccount.transfer(barAccount, fooAccount, 10d);
                System.out.printf("Foo: %s, Bar: %s\n",
                      fooAccount.balance, barAccount.balance);
            }
        }.start();

    }
}
