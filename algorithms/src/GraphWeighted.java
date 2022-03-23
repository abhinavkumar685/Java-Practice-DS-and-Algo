import java.util.*;
import java.util.LinkedList;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class WeightedDirectedGraph {
    int V;
    List<List<Edge>> adjList = new ArrayList<>();

    WeightedDirectedGraph(int v) {
        this.V = v;
        this.adjList = new ArrayList<>(v);
        for (int i = 0; i < v; ++i)
            adjList.add(new ArrayList<>());
    }

    void addEdge(int src, int dest, int weight) {
        this.adjList.get(src).add(new Edge(src, dest, weight));
    }
}

class WeightedUndirectedGraph {
    int V;
    List<List<Edge>> adjList = new ArrayList<>();

    WeightedUndirectedGraph(int v) {
        this.V = v;
        this.adjList = new ArrayList<>(v);
        for (int i = 0; i < v; ++i)
            adjList.add(new ArrayList<>());
    }

    void addEdge(int src, int dest, int weight) {
        this.adjList.get(src).add(new Edge(src, dest, weight));
        this.adjList.get(dest).add(new Edge(dest, src, weight));
    }
}

class BFSPair {
    int vertex;
    String path;
    int level;

    BFSPair(int v) {
        this.vertex = v;
    }

    BFSPair(int v, String path) {
        this.vertex = v;
        this.path = path;
    }

    BFSPair(int v, int level) {
        this.vertex = v;
        this.level = level;
    }

    BFSPair(int v, String path, int level) {
        this.vertex = v;
        this.path = path;
        this.level = level;
    }
}

class WeightPair implements Comparable<WeightPair> {
    int vertex;
    String path;
    int weight;
    WeightPair(int v, String path, int weight) {
        this.vertex = v;
        this.path = path;
        this.weight = weight;
    }

    public int compareTo(WeightPair o) {
        return this.weight - o.weight;
    }
}

class PrimAlgoPair implements Comparable<PrimAlgoPair> {
    int nextVertex;
    int currentVertex;
    int weight;
    PrimAlgoPair(int nextVertex, int currentVertex, int weight) {
        this.currentVertex = currentVertex;
        this.nextVertex = nextVertex;
        this.weight = weight;
    }

    public int compareTo(PrimAlgoPair o) {
        return this.weight - o.weight;
    }
}

public class GraphWeighted {
    public static boolean hasPath(List<List<Edge>> adjList, int src, int dest, boolean[] visited) {
        // https://www.youtube.com/watch?v=mlnnJd9k7oE
        if(src == dest) {
            return true;
        }
        visited[src] = true;
        for(Edge edge : adjList.get(src)) {
            if(!visited[edge.dest]) {
                boolean isReachable = hasPath(adjList, edge.dest, dest, visited);
                if(isReachable) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printPaths(List<List<Edge>> adjList, int src, int dest, boolean[] visited,
                                  List<List<Integer>> result, List<Integer> current) {
        // https://www.youtube.com/watch?v=DrQ-eTN2v3A
        if(src == dest) {
            current.add(src);
            result.add(new ArrayList<Integer>(current));
            current.remove(current.size()-1);
            return;
        }
        visited[src] = true;
        for(Edge edge : adjList.get(src)) {
            if(!visited[edge.dest]) {
                current.add(src);
                printPaths(adjList, edge.dest, dest, visited, result, current);
                current.remove(current.size()-1);
            }
        }
        visited[src] = false;
    }

    public static void connectedComponentsRecursive(List<List<Edge>> adjList, int src,  boolean[] visited,
                                                    List<Integer> current) {
        if(visited[src]) {
            return;
        }
        visited[src] = true;
        current.add(src);
        for(Edge edge : adjList.get(src)) {
            if(!visited[edge.dest]) {
                connectedComponentsRecursive(adjList, edge.dest, visited, current);
            }
        }
    }

    static List<List<Integer>> getConnectedComponents(List<List<Edge>> adjList) {
        // https://www.youtube.com/watch?v=8UBwFE8H4Mc
        boolean[] visited = new boolean[adjList.size()];
        List<List<Integer>> result = new ArrayList<>();
        for(int src=0; src<adjList.size(); src++) {
            if(!visited[src]) {
                List<Integer> current = new ArrayList<>();
                connectedComponentsRecursive(adjList, src, visited, current);
                result.add(new ArrayList<Integer>(current));
            }
        }
        return result;
    }

    static boolean isGraphConnected(List<List<Edge>> adjList) {
        List<List<Integer>> result = getConnectedComponents(adjList);
        return result.size() == 1;
    }

    public static void numIslandsDFS(char[][] grid, int row, int col, boolean[][] visited) {
        if(row<0 || row>=grid.length || col<0 || col>=grid[row].length
                || grid[row][col] == '0' || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        numIslandsDFS(grid, row-1, col, visited);
        numIslandsDFS(grid, row, col+1, visited);
        numIslandsDFS(grid, row+1, col, visited);
        numIslandsDFS(grid, row, col-1, visited);
    }

    public static int numIslands(char[][] grid) {
        // https://leetcode.com/problems/number-of-islands
        // https://www.youtube.com/watch?v=ErPZFxugYkI
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    numIslandsDFS(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    public static void hamiltonianPathAndCycle(List<List<Edge>> adjList, int start, int src, Set<Integer> visited,
                                               List<Integer> current, List<List<Integer>> hamiltonianPath,
                                               List<List<Integer>> hamiltonianCycle) {
        // "visited" HashSet will always have 1 less entry.
        if(visited.size() == adjList.size()-1){
            current.add(src);
            boolean closingEdgeFound = false;
            for(Edge edge : adjList.get(src)) {
                if(edge.dest == start){
                    closingEdgeFound = true;
                    break;
                }
            }
            if(closingEdgeFound) {
                hamiltonianCycle.add(new ArrayList<Integer>(current));
            }
            else {
                hamiltonianPath.add(new ArrayList<Integer>(current));
            }
            current.remove(current.size()-1);
            return;
        }

        visited.add(src);
        for(Edge edge : adjList.get(src)) {
            if(!visited.contains(edge.dest)) {
                current.add(src);
                hamiltonianPathAndCycle(adjList, start, edge.dest, visited, current,
                                        hamiltonianPath, hamiltonianCycle);
                current.remove(current.size()-1);
            }
        }
        visited.remove(src);
    }

    public static void getHamiltonianPathAndCycle(List<List<Edge>> adjList, int start) {
        // https://www.youtube.com/watch?v=nUgp0RG57wQ
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> hamiltonianPath = new ArrayList<>();
        List<List<Integer>> hamiltonianCycle = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        hamiltonianPathAndCycle(adjList, start, start, visited, current, hamiltonianPath, hamiltonianCycle);
        System.out.println("Hamiltonian Path: " + hamiltonianPath);
        System.out.println("Hamiltonian Cycle: " + hamiltonianCycle);
    }

    public static void knightTourDFS(int[][] chess, int row, int col, int moveNumber) {
        // https://www.youtube.com/watch?v=SP880DBRJ_8
        // Input: knightTourDFS(chess, 0, 0, 1);
        if(row<0 || row>=chess.length || col<0 || col>=chess[0].length || chess[row][col]>0) {
            return;
        }
        if(moveNumber == chess.length * chess[0].length) {
            chess[row][col] = moveNumber;
            System.out.println(Arrays.deepToString(chess));
            chess[row][col] = 0;
            return;
        }
        chess[row][col] = moveNumber;
        knightTourDFS(chess, row-2, col+1, moveNumber+1);
        knightTourDFS(chess, row-1, col+2, moveNumber+1);
        knightTourDFS(chess, row+1, col+2, moveNumber+1);
        knightTourDFS(chess, row+2, col+1, moveNumber+1);

        knightTourDFS(chess, row+2, col-1, moveNumber+1);
        knightTourDFS(chess, row+1, col-2, moveNumber+1);
        knightTourDFS(chess, row-1, col-2, moveNumber+1);
        knightTourDFS(chess, row-2, col-1, moveNumber+1);
        chess[row][col] = 0;
    }

    public static void BFS(List<List<Edge>> adjList, int src) {
        // https://www.youtube.com/watch?v=GHnC5qHexsk
        // Steps for BFS: Remove, Mark visited, Work, Add non-visited vertex to queue
        Queue<BFSPair> queue = new LinkedList<>();
        queue.add(new BFSPair(src, src + " "));
        boolean[] visited = new boolean[adjList.size()];

        while(!queue.isEmpty()) {
            BFSPair current = queue.remove();
            if(visited[current.vertex]) {
                continue;
            }
            visited[current.vertex] = true;
            System.out.println("Path till vertex " + current.vertex + " is: " + current.path);
            for(Edge edge : adjList.get(current.vertex)) {
                if(!visited[edge.dest]) {
                    queue.add(new BFSPair(edge.dest, current.path + edge.dest + " "));
                }
            }
        }
    }

    public static void iterativeDFS(List<List<Edge>> adjList, int src) {
        // https://www.youtube.com/watch?v=iUtmQ66IC_0
        boolean[] visited = new boolean[adjList.size()];
        Stack<BFSPair> stack = new Stack<>();
        stack.push(new BFSPair(src, src + " "));
        while(!stack.isEmpty()) {
            BFSPair current = stack.pop();
            if(visited[current.vertex]) {
                continue;
            }
            visited[current.vertex] = true;
            System.out.println("Path till vertex " + current.vertex + " is: " + current.path);
            for(Edge edge : adjList.get(current.vertex)) {
                if(!visited[edge.dest]) {
                    stack.push(new BFSPair(edge.dest, current.path + edge.dest + " "));
                }
            }
        }
    }

    public static boolean _isCyclicBFS(List<List<Edge>> adjList, int src, boolean[] visited) {
        Queue<BFSPair> queue = new LinkedList<>();
        queue.add(new BFSPair(src, src + " "));

        while(!queue.isEmpty()) {
            BFSPair current = queue.remove();
            if(visited[current.vertex]) {
                return true;
            }
            visited[current.vertex] = true;
            for(Edge edge : adjList.get(current.vertex)) {
                if(!visited[edge.dest]) {
                    queue.add(new BFSPair(edge.dest, current.path + edge.dest + " "));
                }
            }
        }
        return false;
    }

    public static void isCyclicBFS(List<List<Edge>> adjList) {
        // https://www.youtube.com/watch?v=qbQq-k75bcY
        boolean[] visited = new boolean[adjList.size()];
        for(int v=0; v<adjList.size(); v++) {
            if(!visited[v]) {
                if(_isCyclicBFS(adjList, v, visited)) {
                    System.out.println("true");
                    return;
                }
            }
        }
        System.out.println("false");
    }

    public static boolean isCyclicUndirectedDFS(List<List<Edge>> adjList, int src, int parent, boolean[] visited) {
        visited[src] = true;
        for(Edge edge: adjList.get(src)) {
            if(!visited[edge.dest]) {
                if(isCyclicUndirectedDFS(adjList, edge.dest, src, visited)) {
                    return true;
                }
            }
            else if(visited[edge.dest] && edge.dest != parent) {
                return true;
            }
        }
        return false;
    }

    public static void isCyclicUndirected(List<List<Edge>> adjList) {
        boolean[] visited = new boolean[adjList.size()];
        for(int src=0; src<adjList.size(); src++) {
            if(!visited[src]) {
                if(isCyclicUndirectedDFS(adjList, src, -1, visited)) {
                    System.out.println("true");
                    return;
                }
            }
        }
        System.out.println("false");
    }
    public boolean isCyclicDirectedDFS(ArrayList<ArrayList<Edge>> adjList, int src, boolean[] visited,
                                       boolean[] currentPath) {
        visited[src]= true;
        currentPath[src]= true;
        for(Edge edge : adjList.get(src)){
            if(!visited[edge.dest]){
                if(isCyclicDirectedDFS(adjList, edge.dest, visited, currentPath)){
                    return true;
                }
            }else if(visited[edge.dest] && currentPath[edge.dest]){
                return true;
            }
        }
        currentPath[src]= false;
        return false;
    }

    public boolean isCyclicDirected(ArrayList<ArrayList<Edge>> adjList) {
        // https://www.youtube.com/watch?v=ZS-UPTd73cM
        boolean[] visited = new boolean[adjList.size()];
        boolean[] currentPath = new boolean[adjList.size()];
        for (int src = 0; src < adjList.size(); src++) {
            if (!visited[src]) {
                if (isCyclicDirectedDFS(adjList, src, visited, currentPath)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void dijkstrasAlgorithm(List<List<Edge>> adjList, int src) {
        // https://www.youtube.com/watch?v=sD0lLYlGCJE
        boolean[] visited = new boolean[adjList.size()];
        PriorityQueue<WeightPair> queue = new PriorityQueue<>();
        queue.add(new WeightPair(src, src + " ", 0));

        while(!queue.isEmpty()) {
            WeightPair current =  queue.remove();
            if(visited[current.vertex]) {
                continue;
            }
            visited[current.vertex] = true;
            System.out.println("Path till vertex " + current.vertex + " is: " + current.path +
                               ", weight: " + current.weight);
            for(Edge edge : adjList.get(current.vertex)) {
                if(!visited[edge.dest]) {
                    queue.add(new WeightPair(edge.dest, current.path + edge.dest + " ",
                                             current.weight + edge.weight));
                }
            }
        }
    }

    public static void primsAlgorithm(List<List<Edge>> adjList, int src) {
        // https://www.youtube.com/watch?v=Vw-sktU1zmc
        // Input: primsAlgorithm(graph.adjList, 0);
        boolean[] visited = new boolean[adjList.size()];
        PriorityQueue<PrimAlgoPair> queue = new PriorityQueue<>();
        queue.add(new PrimAlgoPair(src, -1, 0));

        while(!queue.isEmpty()) {
            PrimAlgoPair current =  queue.remove();
            if(visited[current.nextVertex]) {
                continue;
            }
            visited[current.nextVertex] = true;
            if(current.currentVertex != -1) {
                System.out.println("From vertex " + current.currentVertex + " to " + current.nextVertex
                        + ", weight is: " + current.weight);
            }
            for(Edge edge : adjList.get(current.nextVertex)) {
                if(!visited[edge.dest]) {
                    queue.add(new PrimAlgoPair(edge.dest, current.nextVertex, edge.weight));
                }
            }
        }
    }

    public static void topologicalSortDFS(List<List<Edge>> adjList, int src, boolean[] visited,
                                          Stack<Integer> stack) {
        visited[src] = true;
        for(Edge edge : adjList.get(src)) {
            if(!visited[edge.dest]) {
                topologicalSortDFS(adjList, edge.dest, visited, stack);
            }
        }
        stack.push(src);
    }

    public static void topologicalSortDirectedGraph(List<List<Edge>> adjList) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adjList.size()];
        for(int src=0; src<adjList.size(); src++) {
            if(!visited[src]) {
                topologicalSortDFS(adjList, src, visited, stack);
            }
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        int vertex = 7;
        WeightedUndirectedGraph graph = new WeightedUndirectedGraph(vertex);
        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 10);
        graph.addEdge(2, 3, 10);
        graph.addEdge(0, 3, 40);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 3);
        graph.addEdge(5, 6, 3);
        graph.addEdge(4, 6, 8);
        boolean[] visited = new boolean[vertex];
        System.out.println(hasPath(graph.adjList, 0, 6, visited));
    }
}
