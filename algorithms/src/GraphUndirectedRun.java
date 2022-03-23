import java.util.*;
import java.util.LinkedList;

// This class represents a directed graph using adjacency list
// representation
class GraphUndirected {
    int V;   // No. of vertices
    ArrayList<ArrayList<Integer>> adj_list; //Adjacency Lists

    // Constructor
    GraphUndirected(int v) {
        this.V = v;
        this.adj_list = new ArrayList<>(v);
        for (int i = 0; i < v; ++i)
            adj_list.add(new ArrayList<>());
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj_list.get(v).add(w);
        adj_list.get(w).add(v);
    }

    ArrayList<ArrayList<Integer>> getAdjList() {
        return this.adj_list;
    }

    // prints BFS traversal from a given source s
    void BFS(int source) {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean[] visited = new boolean[V];

        // Create a queue for BFS
        Queue<Integer> queue = new ArrayDeque<>();

        // Mark the current node as visited and enqueue it
        visited[source] = true;
        queue.add(source);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            int vertex = queue.remove();
            System.out.print(vertex + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj_list.get(vertex).listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    void DFS(int source, boolean[] visited) {
        if(visited[source]) return;

        System.out.print(source + " ");
        visited[source] = true;
        Iterator<Integer> itr = adj_list.get(source).listIterator();
        while(itr.hasNext()) {
            int current = itr.next();
            if(!visited[current]) {
                DFS(current, visited);
            }
        }
    }

    boolean isCyclicDFS(int source, boolean[] visited) {
        if(visited[source]) return true;

        visited[source] = true;
        Iterator<Integer> itr = adj_list.get(source).listIterator();
        while(itr.hasNext()) {
            int current = itr.next();
            if(visited[current]) {
                return true;
            }
            else {
                isCyclicDFS(current, visited);
            }
        }
        return false;
    }

    boolean isCyclic() {
        boolean[] visited = new boolean[this.V];
        for(int i=0; i<this.V; i++) {
            Iterator<Integer> itr = adj_list.get(i).listIterator();
            while(itr.hasNext()) {
                boolean[] DFSVisited = new boolean[this.V];
                DFSVisited[i] = true;
                int current = itr.next();
                boolean flag = isCyclicDFS(current, DFSVisited);
                if(flag) {
                    return true;
                }
            }
        }
        return false;
    }
}


public class GraphUndirectedRun {
    public static void main(String[] args)
    {
        GraphUndirected g = new GraphUndirected(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");
        int source = 3;
        boolean[] visited = new boolean[4];
        g.DFS(source, visited);
    }
}
