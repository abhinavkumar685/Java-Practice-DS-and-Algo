import java.util.*;

class Graph {
    int vertex;
    ArrayList<ArrayList<Integer>> adj_list;

    Graph(int ver) {
        this.vertex = ver;
        this.adj_list = new ArrayList<ArrayList<Integer>>(ver);
        for(int i=0; i<ver; i++) {
            adj_list.add(new ArrayList<>());
        }
    }

    void addEdge(int source, int dest) {
        adj_list.get(source).add(dest);
    }

    boolean isCyclic(boolean[] visited, int current) {
        if(visited[current]) return true;
        visited[current] = true;
        boolean Flag = false;
        for(int adj_row=0; adj_row < adj_list.get(current).size(); adj_row++) {
            Flag = isCyclic(visited, adj_list.get(current).get(adj_row));
            if(Flag) return true;
        }
        return false;
    }

    boolean isCyclicUtil() {
        boolean[] visited = new boolean[this.vertex];
        boolean Flag = false;
        for(int source=0; source<vertex; source++){
            visited[source] = true;
            for(int column=0; column<adj_list.get(source).size(); column++) {
                Flag = isCyclic(visited, adj_list.get(source).get(column));
                if(Flag) return true;
            }
            visited[source] = false;
        }
        return false;
    }
}


public class Main{
    // Driver method to
    public static void main(String[] args)
    {
        int vertex = 4;
        Graph g = new Graph(vertex);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");


        if(g.isCyclicUtil()) {
            System.out.println("Cycle found");
        }
        else {
            System.out.println("NO Cycle found");
        }
    }
}