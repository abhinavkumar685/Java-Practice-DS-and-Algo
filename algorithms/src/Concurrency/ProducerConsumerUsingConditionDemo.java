package Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;


class ProducerConsumerUsingCondition {
    private Object[] items = null;
    private int current;
    private int placeIndex = 0;
    private int removeIndex = 0;


    private final Lock lock;
    private final Condition isFull;
    private final Condition isEmpty;

    public ProducerConsumerUsingCondition(int capacity) {
        this.items = new Object[capacity];
        lock = new ReentrantLock();
        isEmpty = lock.newCondition();
        isFull = lock.newCondition();
    }

    public void addObject(Object item) {
        lock.lock();
        while(current >= items.length) {
            try {
                System.out.println("Queue is full, waiting for consumer to consume....");
                isFull.await();
            }
            catch(InterruptedException e) {}
        }

        System.out.println("Adding item in Queue: "+ item);
        items[placeIndex] = item;
        placeIndex = (placeIndex + 1) % items.length;
        ++current;
        isEmpty.signal();
        lock.unlock();
    }

    public Object removeObject() {
        Object item = null;
        lock.lock();
        while(current <= 0) {
            try {
                System.out.println("Queue is empty, waiting for producer to produce....");
                isEmpty.await();
            }
            catch(InterruptedException e) {}
        }

        item = items[removeIndex];
        System.out.println("Removed item from Queue: "+ item);
        removeIndex = (removeIndex + 1) % items.length;
        --current;
        isFull.signal();
        lock.unlock();
        return item;
    }

    public boolean isQueueEmpty() {
        return items.length == 0;
    }
}

class Producer {
    String[] numbers = {"1", "2", "3", "5", "6", "7", "8", "9", "10"};
    ProducerConsumerUsingCondition itemQueue;

    Producer(ProducerConsumerUsingCondition itemQueue) {
        this.itemQueue = itemQueue;
    }

    public void produce() {
        for(String number: numbers) {
            System.out.println("[Producer]: " + number);
            itemQueue.addObject(number);
        }
        itemQueue.addObject(null);
    }
}

class Consumer {
    ProducerConsumerUsingCondition itemQueue;

    Consumer(ProducerConsumerUsingCondition itemQueue) {
        this.itemQueue = itemQueue;
    }

    public void consume() {
        do {
            Object item = itemQueue.removeObject();
            System.out.println("[Consumer]: " + item);
            if(item == null) {
                return;
            }
        }
        while(!itemQueue.isQueueEmpty());
    }
}

public class ProducerConsumerUsingConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        int capacity = 5;
        ProducerConsumerUsingCondition pc = new ProducerConsumerUsingCondition(capacity);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                Producer p = new Producer(pc);
                p.produce();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                Consumer c = new Consumer(pc);
                c.consume();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
