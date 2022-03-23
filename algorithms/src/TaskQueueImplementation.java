import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

class Producer implements Runnable {
    private final List<Integer> taskQueue;
    int maxLimit;

    Producer(List<Integer> myQueue, int maxLimit) {
        this.taskQueue = myQueue;
        this.maxLimit = maxLimit;
    }

    public void run() {
        int counter = 0;
        while (counter <= 20) {
            try {
                produce(counter++);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void produce(int count) throws InterruptedException {
        synchronized (taskQueue) {
            while(taskQueue.size() == maxLimit) {
                System.out.println("Queue is full " + Thread.currentThread().getName() +
                        " is waiting , size: " + taskQueue.size());
                taskQueue.wait(20000);
            }
            taskQueue.add(count);
            System.out.println("Produced: " + count);
            taskQueue.notifyAll();
        }
    }
}

class Consumer implements Runnable {
    private final List<Integer> taskQueue;

    Consumer(List<Integer> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                System.out.println("Queue is empty " + Thread.currentThread().getName() +
                        " is waiting , size: " + taskQueue.size());
                taskQueue.wait(20000);
            }
            int i = (Integer) taskQueue.remove(0);
            System.out.println("Consumed: " + i);
            taskQueue.notifyAll();
        }
    }
}

public class TaskQueueImplementation{
    public static void main(String[] args) {
        List<Integer> taskQueue = new ArrayList<>(20);
        Thread t1 = new Thread(new Producer(taskQueue, 20), "Producer");
        Thread t2 = new Thread(new Consumer(taskQueue), "Consumer");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}