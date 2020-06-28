package algorithms.graph.dfs;

import algorithms.graph.representation.AdjSet;
import algorithms.graph.representation.Graph;

import java.util.ArrayList;

public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle;

    public CycleDetection(Graph G) {

        this.G = G;
        this.hasCycle = false;
        visited = new boolean[G.V()];
        // DFS 多个连通分量
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {

        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            } else if (w != parent) {
                hasCycle = true;
            }
        }
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("src/g.txt");
        CycleDetection cd = new CycleDetection(g);
        System.out.println(cd.isHasCycle());
    }

}
