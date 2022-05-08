/**
 * https://mkyong.com/java/java-thread-mutex-and-semaphore-example/
 * Mutex is special case of semaphore where permits = 1
 */

package Concurrency;

import java.util.concurrent.Semaphore;

class SemaphoreTest implements Runnable {
    public static final Semaphore semaphore = new Semaphore(4);

//    public static final Semaphore semaphore = new Semaphore(1); --> For Mutex


    public void run() {
        try{
            String name = Thread.currentThread().getName();
            System.out.println(name + " : acquiring lock");
            System.out.println(name + " : available Semaphore permits now: " +
                    semaphore.availablePermits());
            semaphore.acquire();
            System.out.println(name + " : got the permit!");

            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println(name + " : is performing operation " + i
                            + ", available Semaphore permits : "
                            + semaphore.availablePermits());

                    // sleep 1 second
                    Thread.sleep(1000);
                }
            }
            finally {
                System.out.println(name + " : releasing lock...");
                semaphore.release();
                System.out.println(name + " : available Semaphore permits now: "
                        + semaphore.availablePermits());
            }
        }
        catch(InterruptedException ignored){}
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) {
        SemaphoreTest sd = new SemaphoreTest();
        System.out.println("Total available Semaphore permits : "
                + SemaphoreTest.semaphore.availablePermits());

        Thread t1 = new Thread(sd, "A");
        Thread t2 = new Thread(sd, "B");
        Thread t3 = new Thread(sd, "C");
        Thread t4 = new Thread(sd, "D");
        Thread t5 = new Thread(sd, "E");
        Thread t6 = new Thread(sd, "F");

        try {
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();

            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        }
        catch(InterruptedException ignored){}
    }
}
