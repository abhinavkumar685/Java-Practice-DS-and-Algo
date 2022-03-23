import java.lang.Thread;

class PrintNumberUsingThread {
    int count = 1;
    int MAX = 4;

    public void printOdd() {
        synchronized (this) {
            while (count < MAX) {
                System.out.println("Checking odd loop");

                while (count%2 == 0) {
                    try {
                        System.out.println("Odd waiting : " + count);
                        this.wait();
                        System.out.println("Notified odd :" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Odd Thread :" + count);
                count++;
                notify();
            }
        }
    }

    public void printEven() {
        synchronized (this) {
            while (count < MAX) {
                System.out.println("Checking even loop");

                while (count%2 == 1) {
                    try {
                        System.out.println("Even waiting: " + count);
                        this.wait();
                        System.out.println("Notified even:" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Even thread :" + count);
                count++;
                notify();

            }
        }
    }

    public static void main(String[] args) {

        PrintNumberUsingThread oep = new PrintNumberUsingThread();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                oep.printEven();

            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                oep.printOdd();

            }
        });

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
