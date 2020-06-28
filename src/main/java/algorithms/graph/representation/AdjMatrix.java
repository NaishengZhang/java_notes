package algorithms.graph.representation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjMatrix implements Graph {

    private int V; //图中有多少个顶点
    private int E; //图中有多少个边
    private int[][] adj;

    public AdjMatrix(String filename) {
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

            adj = new int[V][E];
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);

                int b = scanner.nextInt();
                validateVertex(b);
                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!"); //自环边
                }
                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!"); //平行边
                }

                adj[a][b] = 1;
                adj[b][a] = 1;

            }
        } catch (FileNotFoundException e) {

        }
    }

    public int degree(int v) {
        return adj(v).size();
    }

    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++)
                sb.append(String.format("%d ", adj[i][j]));
            sb.append('\n');
        }
        return sb.toString();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public static void main(String[] args) {

        AdjMatrix adjMatrix = new AdjMatrix("src/g.txt");
        System.out.print(adjMatrix);
    }
}
