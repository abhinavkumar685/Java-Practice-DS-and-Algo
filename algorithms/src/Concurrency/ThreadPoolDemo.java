package Concurrency;

import java.util.Calendar;
import java.util.concurrent.*;

class Task1 implements Runnable {
    public void run() {
        try {
            long duration = (long)(Math.random() * 20);
            System.out.println("Running Task!");
            System.out.println("Going to sleep for " + duration + " seconds");
            TimeUnit.SECONDS.sleep(duration);
            System.out.println("Task Completed!");
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class fixedThreadPoolDemo {
    // When using executor or pool, need to explicitly shutdown the executor
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;
        executor.submit(new Task1());
        executor.submit(new Task1());
        executor.shutdown();
    }
}

class cachedThreadPoolDemo {
    // When using executor or pool, need to explicitly shutdown the executor
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new Task1());
        executor.submit(new Task1());
        executor.shutdown();
    }
}


public class ThreadPoolDemo {
    public static void main(String[] args) {

    }
}
