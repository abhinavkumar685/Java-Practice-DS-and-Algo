import com.sun.javafx.collections.ListListenerHelper;

import java.util.*;

class GraphCreator {
    private final int vertices;
    ArrayList<ArrayList<Integer>> adj_list;
    GraphCreator(int v) {
        this.vertices = v;
        this.adj_list = new ArrayList<>(this.vertices);
        for(int i=0; i<vertices; i++) {
            adj_list.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int target) {
        adj_list.get(source).add(target);
        adj_list.get(target).add(source);
    }

    public void BFS(int source) {
        boolean[] visited = new boolean[this.vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        while (queue.size()!=0) {
            int vertex = queue.remove();
            System.out.println(vertex + " ");
            Iterator<Integer> itr = adj_list.get(vertex).listIterator();
            while (itr.hasNext()) {
                int n = itr.next();
                if(!visited[n]) {
                    queue.add(n);
                    visited[n] = true;
                }
            }
        }

    }

    public void DFS(int source, boolean[] visited) {
        if(visited[source]) {
            return;
        }

        visited[source] = true;
        System.out.println(source);
        Iterator<Integer> itr = adj_list.get(source).listIterator();
        while(itr.hasNext()) {
            int n = itr.next();
            if(!visited[n]) {
                DFS(n, visited);
            }
        }
    }

    public void DFSUtil(int source) {
        boolean[] visited = new boolean[this.vertices];
        DFS(source, visited);
    }

    public boolean isCyclic(boolean[] visited, int current) {
        if(visited[current]) return true;
        visited[current] = true;
        boolean Flag = false;
        for(int adj_row=0; adj_row<this.adj_list.get(current).size(); adj_row++) {
            Flag = isCyclic(visited, adj_row);
            if(Flag) return true;
        }
        return false;
    }

    public boolean CyclicUtil() {
        boolean[] visited = new boolean[this.vertices];
        boolean cycleFound = false;
        for(int source=0; source<this.vertices; source++) {
            visited[source] = true;
            for(int column=0; column<this.adj_list.get(source).size(); column++){
                cycleFound = isCyclic(visited, adj_list.get(source).get(column));
                if (cycleFound) return true;
            }
            visited[source] = false;
        }
        return false;
    }

}

class TestBTNode {
    int val;
    TestBTNode left, right;

    TestBTNode(int v) {
        this.val = v;
        this.left = null;
        this.right = null;
    }

    public int getnodeSum(TestBTNode node) {
        if(node == null) return 0;

        return node.val + getnodeSum(node.left) + getnodeSum(node.right);
    }

    public int isSumTree(TestBTNode root) {
        if(root == null) return 0;

        int leftSum = getnodeSum(root.left);
        int rightSum = getnodeSum(root.right);

        if((root.val == leftSum + rightSum) && isSumTree(root.left) == 0 && isSumTree(root.right) == 0) return 0;

        return -1;
    }
}
class ThreadA {
    public static void numPrinterRecursive(int num, String[] map) {
        if(num == 0) {
            return;
        }
        numPrinterRecursive(num/10, map);
        // Tail Recursion
        System.out.println(map[num%10]);
    }

    public static boolean checkSorted(int[] array, int index) {
        if(index == array.length) {
            return true;
        }

        if(array[index] >= array[index-1]){
            return checkSorted(array, index+1);

        }
        return false;
    }



    public static void main(String[] args) {
        String[] map = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int[] nums = {1,2,3};



    }
}