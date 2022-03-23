package Concurrency;

import java.util.concurrent.*;

class FactorialService implements Callable<Long> {
    int num;

    FactorialService(int n) {
        this.num = n;
    }

    public Long call() {
        return factorial();
    }

    public Long factorial() {
        long result = 1;
        try {
            while(num > 0) {
                result *= num;
                num--;
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

public class CallableDemo {
    // Future.get() method throws 2 exceptions as listed below
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.println("Factorial Service called for 10!");
        Future<Long> result10 = executor.submit(new FactorialService(10));

        System.out.println("Factorial Service called for 10!");
        Future<Long> result20 = executor.submit(new FactorialService(20));

        Long factorial10 = result10.get();
        System.out.println("10! = " + factorial10);

        Long factorial20 = result20.get();
        System.out.println("20! = " + factorial20);

        executor.shutdown();
    }

}
