package Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


class ReadWriteLock {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public String message = "a";

    public void read() {
        if(lock.isWriteLocked()) {
            System.out.println("Write lock present..");
        }
        lock.readLock().lock();

        try {
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName()
                    + "  Time Taken " + (duration / 1000) + " seconds.");
            Thread.sleep(duration);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(Thread.currentThread().getName() +": "+ message );
            lock.readLock().unlock();
        }
    }

    public void write1() {
        lock.writeLock().lock();
        try {
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName()
                    + "  Time Taken " + (duration / 1000) + " seconds.");
            Thread.sleep(duration);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            message = message.concat("b");
            lock.writeLock().unlock();
        }
    }

    public void write2() {
        lock.writeLock().lock();
        try {
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName()
                    + "  Time Taken " + (duration / 1000) + " seconds.");
            Thread.sleep(duration);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            message = message.concat("c");
            lock.writeLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock rwLock = new ReadWriteLock();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                rwLock.write1();
            }
        }, "Write Thread-1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                rwLock.write2();
            }
        }, "Write Thread-2");
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                rwLock.read();
            }
        }, "Read Thread-1");

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
