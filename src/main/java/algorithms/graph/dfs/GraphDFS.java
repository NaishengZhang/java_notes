package algorithms.graph.dfs;

import algorithms.graph.representation.AdjSet;
import algorithms.graph.representation.Graph;

import java.util.ArrayList;

public class GraphDFS {

    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        // DFS 多个连通分量
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {

        visited[v] = true;
        pre.add(v);
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> preOrder() {
        return pre;
    }

    public Iterable<Integer> postOrder() {
        return post;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("src/g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.preOrder());
        System.out.println(graphDFS.postOrder());
    }

}
