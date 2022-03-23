package Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;


class PrinterUsingCondition {
    private int max = 20;
    private int counter = 1;

    private final Lock lock = new ReentrantLock();
    private final Condition evenLock = lock.newCondition();
    private final Condition oddLock = lock.newCondition();


    public void evenPrinter() {
        lock.lock();
        while(counter <= max) {
            while(counter%2 == 1) {
                try {
                    evenLock.await();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            System.out.println(counter);
            counter++;
            oddLock.signalAll();
        }
        lock.unlock();
    }

    public void oddPrinter() {
        lock.lock();
        while(counter < max) {
            while(counter%2 == 0) {
                try {
                    oddLock.await();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            System.out.println(counter);
            counter++;
            evenLock.signalAll();
        }
        lock.unlock();
    }
}

public class PrinterUsingConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        PrinterUsingCondition pc = new PrinterUsingCondition();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                pc.evenPrinter();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                pc.oddPrinter();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
