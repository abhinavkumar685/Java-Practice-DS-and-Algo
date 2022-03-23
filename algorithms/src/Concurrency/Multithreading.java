package Concurrency;

import java.util.*;
import java.lang.*;

class MyRunnable implements Runnable {
    Thread thread1;
    private String threadName;
    private boolean suspend = false;

    public MyRunnable(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating Thread: " + threadName);
    }

    public void run() {
        System.out.println("Running thread: " + Thread.currentThread().getName() );
        try {
            for(int i=0; i<10; i++) {
                System.out.println(i);
                Thread.sleep(1000);
                synchronized(this) {
                    while(suspend) {
                        this.wait();
                    }
                }
            }
        }

        catch(InterruptedException e) {}
        System.out.println("Thread " +  Thread.currentThread().getName() + " exiting.");
    }

    public void start() {
        if(thread1 == null) {
            thread1 = new Thread (this);
            thread1.start();
        }
    }

    public synchronized void resume() {
        suspend = false;
        this.notify();
    }

    public synchronized void suspend() {
        suspend = true;
    }

}

public class Multithreading {
    public static void main(String[] args) {
        MyRunnable r1 = new MyRunnable("Thread1");
        MyRunnable r2 = new MyRunnable("Thread2");
        r1.start();
        r2.start();

        try {
            Thread.sleep(1000);
            r1.suspend();
            System.out.println("Suspending First Thread");
            Thread.sleep(1000);
            r1.resume();
            System.out.println("Resuming First Thread");

            r2.suspend();
            System.out.println("Suspending thread Two");
            Thread.sleep(1000);
            r2.resume();
            System.out.println("Resuming thread Two");
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        try {
            System.out.println("Waiting for threads to finish.");
            r1.thread1.join();
            r2.thread1.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
    }
}

class RunnableLambdaTest {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() { // anonymous class
                System.out.println("Runnable with Anonymous Class");
            }
        };
        Runnable r2 = () -> {   // lambda expression
            System.out.println("Runnable with Lambda Expression");
        };
        new Thread(r1).start();
        new Thread(r2).start();
    }
}