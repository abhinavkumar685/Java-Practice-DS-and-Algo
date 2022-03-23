package Concurrency;

import java.lang.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockManager implements Runnable {
    private final Lock lock = new ReentrantLock();

    public void print() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "  Time Taken "  + " 5 seconds.");
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.printf(
                    "%s printed the document successfully.\n", Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public void run() {
        print();
    }
}

class PrintDemo {
    private final Lock queueLock = new ReentrantLock();

    public void print() {
        queueLock.lock();
        // Always use lock in try and finally block
        try {
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName()
                    + "  Time Taken " + (duration / 1000) + " seconds.");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.printf(
                    "%s printed the document successfully.\n", Thread.currentThread().getName());
            queueLock.unlock();
        }
    }
}

class ThreadDemo extends Thread {
    PrintDemo  printDemo;

    ThreadDemo(String name,  PrintDemo printDemo) {
        super(name);
        this.printDemo = printDemo;
    }

    @Override
    public void run() {
        System.out.printf(
                "%s starts printing a document\n", Thread.currentThread().getName());
        printDemo.print();
    }
}


public class LockDemo {
    public static void main(String[] args) {
        LockManager LD = new LockManager();

        Thread t1 = new Thread(LD, "Thread - 1 ");
        Thread t2 = new Thread(LD, "Thread - 2 ");
        Thread t3 = new Thread(LD, "Thread - 3 ");
        Thread t4 = new Thread(LD, "Thread - 4 ");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}