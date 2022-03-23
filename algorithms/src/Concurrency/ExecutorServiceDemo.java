package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class ThreadExecutor implements Runnable {
    public void run() {
        try {
            long duration = (long)(Math.random() * 20);
            System.out.println("Running Task!");
            System.out.println("Going to sleep for " + duration + " seconds");
            TimeUnit.SECONDS.sleep(duration);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class ExecutorServiceDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            executor.submit(new ThreadExecutor());
            System.out.println("Shutting Down executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            if(!executor.isTerminated()) {
                System.out.println("Forcefully shutting down the executor");
                executor.shutdownNow();
                System.out.println("shutdown finished now completely");
            }
        }
    }
}