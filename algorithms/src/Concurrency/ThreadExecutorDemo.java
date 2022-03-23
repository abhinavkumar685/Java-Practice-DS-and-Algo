package Concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


class Task implements Runnable {
    public void run() {
        try {
            long duration = (long)(Math.random() * 5);
            System.out.println("Running Task!");
            TimeUnit.SECONDS.sleep(duration);
            System.out.println("Task Completed");
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class ThreadExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new Task());
        ThreadPoolExecutor pool = (ThreadPoolExecutor)executor;
        pool.shutdown();

    }
}
