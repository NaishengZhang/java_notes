package algorithms.graph.representation;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class AdjSet {

    private int V; //图中有多少个顶点
    private int E; //图中有多少个边
    private TreeSet<Integer> adj[];

    public AdjSet(String filename) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }

            E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }
            // 申请内存空间
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);

                int b = scanner.nextInt();
                validateVertex(b);
                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!"); //自环边
                }
                if (adj[a].contains(b)) { // O(logv)
                    throw new IllegalArgumentException("Parallel Edges are Detected!"); //平行边
                }

                adj[a].add(b); // O(logv)
                adj[b].add(a);

            }
        } catch (FileNotFoundException e) {

        }
    }


    //用户不需要知道返回的到底是arraylist linkedlist还是treeset，只需要遍历结果就行 Iterable接口
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public static void main(String[] args) {
        AdjSet adjSet = new AdjSet("src/g.txt");
        System.out.print(adjSet);
    }
}
