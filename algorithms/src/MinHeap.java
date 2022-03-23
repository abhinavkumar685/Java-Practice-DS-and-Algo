import java.util.*;

public class MinHeap {
    ArrayList<Integer> heap;

    MinHeap() {
        heap = new ArrayList<>();
    }

    public int peek() {
        if(size() == 0) {
            System.out.println("Heap Underflow");
            return -1;
        }
        return heap.get(0);
    }

    public void add(int val) {
        heap.add(val);
        upheapify(size() - 1);
    }

    private void upheapify(int index) {
        if(index == 0) return;
        int parentIndex = (index - 1) / 2;
        if(heap.get(index) < heap.get(parentIndex)) {
            swap(index, parentIndex);
            upheapify(parentIndex);
        }
    }

    private void swap(int i, int j) {
        int ith = heap.get(i);
        int jth = heap.get(j);
        heap.set(i, jth);
        heap.set(j, ith);
    }

    public int remove() {
        if(size() == 0) {
            System.out.println("MinHeap Underflow");
            return -1;
        }
        swap(0, size() - 1);
        int val = heap.get(size()-1);
        heap.remove(size() - 1);
        downheapify(0);
        return val;
    }

    public void downheapify(int parentIndex) {
        // This is also algorithm to convert maxHeap to minHeap
        int minIndex = parentIndex;

        int child1 = (2 * parentIndex) + 1;
        if(child1 < size() && heap.get(child1) < heap.get(minIndex)) {
            minIndex = child1;
        }

        int child2 = (2 * parentIndex) + 2;
        if(child2 < size() && heap.get(child2) < heap.get(minIndex)) {
            minIndex = child2;
        }

        if(minIndex != parentIndex) {
            swap(minIndex, parentIndex);
            downheapify(minIndex);
        }
    }

    public  int size() {
        return heap.size();
    }

    public void show() {
        System.out.println("Print: " + heap);
    }

    public static void main(String[] args) {
        // https://www.youtube.com/watch?v=RIjxT24gUv4
        MinHeap heap = new MinHeap();
        heap.add(10);
        heap.add(1);
        heap.add(3);
        heap.show();
        System.out.println("Peek: " + heap.peek());
        System.out.println("Removing: " + heap.remove());
        heap.show();
        System.out.println("Peek: " + heap.peek());
    }
}

