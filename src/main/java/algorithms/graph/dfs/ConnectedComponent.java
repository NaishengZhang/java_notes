package algorithms.graph.dfs;

import algorithms.graph.representation.AdjSet;
import algorithms.graph.representation.Graph;

import java.util.ArrayList;

public class ConnectedComponent {

    private Graph G;
    private int[] visited;
    private int cccount;

    public ConnectedComponent(Graph G) {

        this.G = G;
        visited = new int[G.V()];
        for (int i = 0; i < visited.length; i++)
            visited[i] = -1;

        // DFS 多个连通分量
        cccount = 0;
        for (int v = 0; v < G.V(); v++) {
            if(visited[v] == -1){
                dfs(v, cccount);
                cccount++;
            }
        }
    }

    private void dfs(int v, int ccid) {

        visited[v] = ccid;
        for (int w : G.adj(v))
            if (visited[w] == -1)
                dfs(w, ccid);
    }

    public int count() {
        for (int e : visited)
            System.out.print(e + " ");
        System.out.println();
        return cccount;
    }
    public boolean isConnected(int v, int w){
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){

        ArrayList<Integer>[] res = new ArrayList[cccount];
        for(int i = 0; i < cccount; i ++)
            res[i] = new ArrayList<>();

        for(int v = 0; v < G.V(); v ++)
            res[visited[v]].add(v);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("src/g.txt");
        ConnectedComponent cc = new ConnectedComponent(g);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
        for(int ccid = 0; ccid < comp.length; ccid ++){
            System.out.print(ccid + " : ");
            for(int w: comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
