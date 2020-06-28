
package algorithms.graph.dfs;

import algorithms.graph.representation.AdjSet;
import algorithms.graph.representation.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class SingleSourcePath {

    private Graph G;
    private boolean[] visited;
    private int source;
    private int[] pre;

    public SingleSourcePath(Graph G, int source) {
        this.G = G;
        this.source = source;
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        // DFS source所在的连通分量
        dfs(source, source);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnectedTo(int target){
        return visited[target];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(t)) return res;
        int cur = t;
        while(cur != source){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);

        Collections.reverse(res);
        return res;
    }
    public static void main(String[] args) {
        Graph g = new AdjSet("src/g.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
        System.out.println("0 -> 5 : " + sspath.path(5));
    }

}
