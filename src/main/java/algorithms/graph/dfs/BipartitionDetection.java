package algorithms.graph.dfs;

import algorithms.graph.representation.AdjSet;
import algorithms.graph.representation.Graph;

import java.util.Arrays;

public class BipartitionDetection {

    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        Arrays.fill(colors, -1);

        // DFS 多个连通分量
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }
    public boolean isBipartite() {
        return isBipartite;
    }
    public static void main(String[] args) {
        Graph g = new AdjSet("src/g.txt");
        BipartitionDetection bd = new BipartitionDetection(g);
        System.out.println(bd.isBipartite());
        Graph g3 = new AdjSet("src/g3.txt");
        BipartitionDetection bd3 = new BipartitionDetection(g3);
        System.out.println(bd3.isBipartite());

    }

}
