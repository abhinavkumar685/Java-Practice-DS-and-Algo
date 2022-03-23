package Concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

//class Writer extends RecursiveAction {
//    // RecursiveAction represents a task which does not return any value.
//    @Override
//    protected void compute() { }
//}

class SumArray extends RecursiveTask<Long> {
    int low;
    int high;
    int[] array;

    SumArray(int[] array, int low, int high) {
        this.array = array;
        this.low   = low;
        this.high  = high;
    }

    protected Long compute() {
        if(high - low <= 10) {
            long sum = 0;
            for(int i=low; i<high; i++) {
                sum += array[i];
            }
            return sum;
        }
        else {
            int mid = low + (high - low)/2;
            SumArray left = new SumArray(array, low, mid);
            SumArray right = new SumArray(array, mid, high);
            left.fork();
            long rightSum = right.compute();
            long leftSum = left.join();
            return rightSum + leftSum;
        }
    }
}
public class ForkJoinDemo {
    public static void main(final String[] args) {
        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(nThreads);

        int[] array = new int[1000];
        for(int i=0; i<array.length; i++) {
            array[i] = i;
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
        Long result = forkJoinPool.invoke(new SumArray(array, 0, array.length));
        System.out.println(result);
    }
}
